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
import java.text.SimpleDateFormat;
import java.util.Date;
import uk.ac.dundee.computing.aec.MedTracker.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.MedTracker.stores.UserProfile;



/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    //updated to take in more data apon login including date signed up
    public boolean RegisterUser(String username, String first_name, String last_name,String email, String Password)
    {
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        
        //used to get sign up date and format it into needed format
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy");
        String joindate = format.format(currentDate);
        joindate = joindate.replace(".", " ");
                
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        
        //simple insert statement
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, password, first_name, last_name, email, joindate) Values(?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, EncodedPassword, first_name, last_name, email, joindate));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    //updates uder profile row basic update query
    public void editUserProfile(String login, String first_name, String last_name, String email)
    {
        // was going to be used to change user password if i had more time
        /*AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }*/
        
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("update userprofiles set first_name = ?, last_name = ?, email = ? where login = ?");
           
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(first_name, last_name, email, login));
    }
    
    //used to get all user data from database into a bean 
    //was based on the IsValidUser method below
    //sends bean back to profile servlet
    public UserProfile getUserProfile(String username){
        //sets up needed objects and the cql statements to read from database
        UserProfile userProfile = new UserProfile();
        Session session = cluster.connect("MedTracker");
        //gets the row of data where the needed login is
        PreparedStatement ps = session.prepare("select * from userprofiles where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
        rs=session.execute(boundStatement.bind(username)); // executes statement
        
        session.close();
        
        // if user dosn't exist
        if (rs.isExhausted()) {
            System.out.println("User does not exist");
            return null;
        } else { //if it does fill profile bean
            for (Row row :rs) {
                userProfile.setUsername(row.getString("login"));
                userProfile.setEmail(row.getString("email"));
                userProfile.setFirstName(row.getString("first_name"));
                userProfile.setLastName(row.getString("last_name"));
                userProfile.setLastName(row.getString("last_name"));
                userProfile.setJoinDate(row.getString("joindate"));
            }
            return userProfile;
        }
        
    }
    
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("MedTracker");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
