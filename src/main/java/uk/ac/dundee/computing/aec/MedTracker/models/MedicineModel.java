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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static uk.ac.dundee.computing.aec.MedTracker.lib.Convertors.getTimeUUID;
import uk.ac.dundee.computing.aec.MedTracker.stores.Medicine;
import org.joda.time.*;


/**
 *
 * @author steven
 */
public class MedicineModel {
    Cluster cluster;
    public MedicineModel(){
        
    }
    
    public java.util.LinkedList<Medicine> getAllUserMeds(String username){
        
        //sets up linked list of medicine beans
        java.util.LinkedList<Medicine> allMeds = new java.util.LinkedList<>();
        Session session = cluster.connect("MedTracker");
        //gets all meds in database
        PreparedStatement ps = session.prepare("select * from Medicine where login = ? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement.bind(username)); // executes statement
        session.close();
        
        //if no users
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
   
                allMeds.add(med); //add to linked list 
            }
            return allMeds;
        }
    }
    
    public boolean addNewMed(String username, String medicine_name, int dose, int doses_left, String instructions, int time_between)
    {
        //used to get sign up date and format it into needed format
        Date date = new Date();
        UUID id = getTimeUUID();
       
        //simple insert statement
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("insert into medicine (login, medicine_name, dose, doses_left, id, instructions, last_taken, time_between) Values(?,?,?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, medicine_name, dose, doses_left, id, instructions, date, time_between));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public void editMedicine(String username, String medicine_name, UUID id, String instructions, int dose, int time_between)
    {
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("update medicine set instructions = ?, dose = ?, time_between=? where id = ? AND login = ? AND medicine_name=?");
           
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(instructions, dose, time_between, id, username, medicine_name));  
    }
    
    public void takeDose(UUID id, String username, String medicine_name)
    {
        Date date = new Date();
        Session session = cluster.connect("MedTracker");
        
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
    
    public void deleteMed(UUID id){
        Session session = cluster.connect("MedTracker");
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("DELETE from medicine where id=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
       rs=session.execute(boundStatement.bind(id)); // executes statement
        
        session.close();
    }
    
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
