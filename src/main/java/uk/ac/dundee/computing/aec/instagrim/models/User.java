/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

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
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.UserProfile;



/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    //updated to take in more data apon login including date signed up
    public boolean RegisterUser(String username, String country, String first_name, String last_name,String email, String Password)
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
        Session session = cluster.connect("instagrim_swturner");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, country, first_name, joindate, last_name, email, password) Values(?,?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, country, first_name, joindate, last_name, email, EncodedPassword));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    //updates uder profile row basic update query
    public void editUserProfile(String login, String first_name, String last_name, String country, String email)
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
        
        Session session = cluster.connect("instagrim_swturner");
        PreparedStatement ps = session.prepare("update userprofiles set first_name = ?, last_name = ?, country = ?, email = ? where login = ?");
           
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(first_name, last_name, country, email, login));
    }
    
    //used to get all user data from database into a bean 
    //was based on the IsValidUser method below
    //sends bean back to profile servlet
    public UserProfile getUserProfile(String username){
        //sets up needed objects and the cql statements to read from database
        UserProfile userProfile = new UserProfile();
        Session session = cluster.connect("instagrim_swturner");
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
                userProfile.setCountry(row.getString("country"));
                userProfile.setLastName(row.getString("last_name"));
                userProfile.setJoinDate(row.getString("joindate"));
            }
            return userProfile;
        }
        
    }
    
    //searches for user profile and returns true if exists
    public boolean searchForUser(String username)
    {
       //searches for the user using select statement
        Session session = cluster.connect("instagrim_swturner");
        PreparedStatement ps = session.prepare("select * from userprofiles where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement.bind(username)); // executes statement
        session.close();
        
        //return true if found false if not
        if (rs.isExhausted()) {
            System.out.println("User does not exist");
            return false;
        }else{ 
            return true;
        }
    }
    
    public java.util.LinkedList<UserProfile> getAllProfiles(){
        //sets up linked list of userProfile beans
        java.util.LinkedList<UserProfile> allProfiles = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim_swturner");
        //gets all profiles in database
        PreparedStatement ps = session.prepare("select * from userprofiles");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement); // executes statement
        session.close();
        
        //if no users
        if (rs.isExhausted()) {
            System.out.println("There are no users");
            return null;
        } else { //if it does fill user bean and add to linked list
            for (Row row : rs) {
                UserProfile profile = new UserProfile();
                profile.setUsername(row.getString("login"));
                profile.setFirstName(row.getString("first_name"));
                profile.setLastName(row.getString("last_name"));
                allProfiles.add(profile); //add to linked list 
            }
            return allProfiles;
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
        Session session = cluster.connect("instagrim_swturner");
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
