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
        PreparedStatement ps = session.prepare("select * from Medicine where login = ?");
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
                med.setInstructions(row.getString("instructions"));
                med.setDose(row.getInt("dose"));
                med.setLastTaken(row.getDate("last_Taken"));
                allMeds.add(med); //add to linked list 
            }
            return allMeds;
        }
    }
    
    public boolean addNewMed(String username, String medicine_name, String instructions, int dose)
    {
        //used to get sign up date and format it into needed format
        Date date = new Date();
       
        //simple insert statement
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("insert into medicine (login, medicine_name, instructions, dose, last_taken) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, medicine_name, instructions, dose, date));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
