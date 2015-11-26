/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import uk.ac.dundee.computing.aec.MedTracker.lib.AlertMail;
import static uk.ac.dundee.computing.aec.MedTracker.lib.Convertors.getTimeUUID;
import uk.ac.dundee.computing.aec.MedTracker.stores.Medicine;
import uk.ac.dundee.computing.aec.MedTracker.stores.UserProfile;

/**
 *
 * @author steven
 */
public class MedicineModel {
    Cluster cluster;
    public MedicineModel(){
        
    }
    
    //returns a linked list of mecidine objects from a username
    public java.util.LinkedList<Medicine> getAllUserMeds(String username){
        
        //sets up linked list of medicine beans
        java.util.LinkedList<Medicine> allMeds = new java.util.LinkedList<>();
        Session session = cluster.connect("MedTracker");
        //gets all meds in database for a user
        PreparedStatement ps = session.prepare("select * from Medicine where login = ? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement.bind(username)); // executes statement
        session.close();
        
        //if no meds
        if (rs.isExhausted()) {
            System.out.println("There are no Meds");
            return null;
        } else { //if it does fill user bean and add to linked list
            for (Row row : rs) {
                Medicine med = new Medicine();
                
                //sets data from database
                med.setID(row.getUUID("id"));
                med.setUsername(row.getString("login"));
                med.setMedicineName(row.getString("medicine_name"));
                med.setInstructions(row.getString("instructions"));
                med.setDose(row.getInt("dose"));
                med.setDoseLeft(row.getInt("doses_left"));
                med.setDosesPerPrescription(row.getInt("doses_per_prescription"));
                med.setTimeBetween(row.getInt("time_between"));
                med.setLastTaken(row.getDate("last_Taken"));
                
                //works out when the next dose is to be taken
                Calendar nd = Calendar.getInstance();
                nd.setTime(row.getDate("last_Taken"));
                nd.add(Calendar.HOUR_OF_DAY, row.getInt("time_between"));
                Date nextDose = nd.getTime();
                med.setNextDose(nextDose);
                
                //works out time in hours before next dose
                Date current = new Date();
                long duration = nextDose.getTime() - current.getTime();
                duration = TimeUnit.MILLISECONDS.toHours(duration);
                int hours = (int) duration;
                med.setTimeLeft(hours);
                
                if( (row.getInt("doses_left")<=5) && (row.getInt("email_sent")==0)){
                   sendMailAlert(med.getID(), med.getUsername(), med.getMedicineName());
                } 
                allMeds.add(med); //add to linked list 
            }
            return allMeds;
        }
    }
    
    public void sendMailAlert(UUID id, String username, String medicineName)
    {
        User us = new User();
        us.setCluster(cluster);
        UserProfile up = us.getUserProfile(username);
        
        Session session = cluster.connect("MedTracker");
        //gets all meds in database for a user
        PreparedStatement ps = session.prepare("update medicine set email_sent = 1 where id = ? AND login = ? AND medicine_name=?");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(id,username,medicineName)); // executes statement
        session.close();
        
        AlertMail mailSender = new AlertMail();
        mailSender.sendNoticeMail(up.getEmail(), up.getFirstName(), up.getLastName(), medicineName );
        
    }
    
    //adds a new medicine for a user
    public boolean addNewMed(String username, String medicine_name, int dose, int doses_left, String instructions, int time_between)
    {
        //used to get last taken date
        Date date = new Date();
        UUID id = getTimeUUID();
        int email_sent=0;
        //simple insert statement
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("insert into medicine (login, medicine_name, dose, doses_left, doses_per_prescription, id, instructions, last_taken, time_between, email_sent) Values(?,?,?,?,?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, medicine_name, dose, doses_left, doses_left, id, instructions, date, time_between,email_sent));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    //used to edit certain details ona  medicine
    public void editMedicine(String username, String medicine_name, UUID id, String instructions, int dose, int time_between)
    {
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("update medicine set instructions = ?, dose = ?, time_between=? where id = ? AND login = ? AND medicine_name=?");
            
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(instructions, dose, time_between, id, username, medicine_name));  
    }
    
    //used to take a dose
    public void takeDose(UUID id, String username, String medicine_name)
    {
        Date date = new Date();
        Session session = cluster.connect("MedTracker");
        
        /*used to get doses_left
          This could of been done better using the counter type in cassandra
          but i could not get it to work so had to make due with this method
        */
        PreparedStatement psDoses = session.prepare("SELECT doses_left FROM medicine where id=?");
        BoundStatement bsDoses = new BoundStatement(psDoses);
        ResultSet doseRS = session.execute(bsDoses.bind(id));
        int dosesLeft=0;
        
        for (Row row : doseRS) {
            dosesLeft = row.getInt("doses_left");
        }
        
        dosesLeft=dosesLeft-1;
       
        PreparedStatement ps = session.prepare("UPDATE medicine SET doses_left = ?, last_taken = ? where id = ? AND login = ? AND medicine_name = ?");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(dosesLeft,date,id,username, medicine_name)); 
    }
    
    //gets a select med
    public Medicine getUserMedicine(UUID id){
        //sets up needed objects and the cql statements to read from database
        Medicine med = new Medicine();
        Session session = cluster.connect("MedTracker");
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("select * from medicine where id=? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
        rs=session.execute(boundStatement.bind(id)); // executes statement
       
        session.close();
        
        // if user dosn't exist
        if (rs.isExhausted()) {
            System.out.println("ERROR");
            return null;
        } else { //if it does fill profile bean
            for (Row row :rs) {
                med.setUsername(row.getString("login"));
                med.setMedicineName(row.getString("medicine_name"));
                med.setDose(row.getInt("dose"));
                med.setDoseLeft(row.getInt("doses_left"));
                med.setID(row.getUUID("id"));
                med.setInstructions(row.getString("instructions"));
                med.setLastTaken(row.getDate("last_Taken"));
                med.setTimeBetween(row.getInt("time_between"));
            }
            return med;
        }
    }
    
    //deletes a med from the database
    public void deleteMed(UUID id){
        Session session = cluster.connect("MedTracker");
        
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("DELETE from medicine where id=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
       rs=session.execute(boundStatement.bind(id)); // executes statement
        
        session.close();
    }
    
    public void refillPrescription(UUID id, String username, String medicine_name){
        
        Session session = cluster.connect("MedTracker");
        PreparedStatement psDoses = session.prepare("SELECT doses_per_prescription FROM medicine where id=?");
        BoundStatement bsDoses = new BoundStatement(psDoses);
        ResultSet doseRS = session.execute(bsDoses.bind(id));
        int doses_per_perscription=0;
        
        for (Row row : doseRS) {
            doses_per_perscription = row.getInt("doses_per_prescription");
        }
        
        
       
        PreparedStatement ps = session.prepare("UPDATE medicine SET doses_left = ?, email_sent = 0 where id = ? AND login = ? AND medicine_name = ?");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(doses_per_perscription,id,username, medicine_name));
    }
    
    

    //used to work out the time difference between two dates in hours, works with magic
    public String getTimeDiff(Date dateOne, Date dateTwo) {  
        String diff = "";
        long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());        
        diff = String.format("%d", TimeUnit.MILLISECONDS.toHours(timeDiff), 
                             TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));        
        return diff;
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
