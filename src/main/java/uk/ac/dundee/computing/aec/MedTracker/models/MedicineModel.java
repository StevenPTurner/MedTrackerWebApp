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
import java.util.Date;
import java.util.UUID;
import static uk.ac.dundee.computing.aec.MedTracker.lib.Convertors.getTimeUUID;
import uk.ac.dundee.computing.aec.MedTracker.stores.Medicine;
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
                
                med.setUsername(row.getString("login"));
                med.setMedicineName(row.getString("medicine_name"));
                med.setDose(row.getInt("dose"));
                med.setDoseLeft(row.getInt("doses_left"));
                med.setID(row.getUUID("id"));
                med.setInstructions(row.getString("instructions"));
                med.setLastTaken(row.getDate("last_Taken"));
                med.setTimeBetween(row.getInt("time_between"));
                
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
    
    public Medicine getUserMedicine(UUID id){
        //sets up needed objects and the cql statements to read from database
        Medicine med = new Medicine();
        Session session = cluster.connect("MedTracker");
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("select * from medicine where id=?");
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
    
    public void deleteMed(UUID id)
    {
        Session session = cluster.connect("MedTracker");
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("DELETE from medicine where id=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
       rs=session.execute(boundStatement.bind(id)); // executes statement
        
        session.close();
    }
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
