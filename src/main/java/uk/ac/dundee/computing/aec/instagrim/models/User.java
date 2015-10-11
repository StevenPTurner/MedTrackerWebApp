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
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.UserProfile;



/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    //updated to take in more data apon login
    public boolean RegisterUser(String username, String country, String first_name, String last_name,String email, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        
        //used to get sign up date and format it
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
        
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, country, first_name, joindate, last_name, email, password) Values(?,?,?,?,?,?,?)");
       
        
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, country, first_name, joindate, last_name, email, EncodedPassword));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public void editUserProfile(String login, String first_name, String last_name, String country, String email)
    {
        // was going to be used to change user password
        /*AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }*/
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("update userprofiles set first_name = ?, last_name = ?, country = ?, email = ? where login = ?");
           
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(first_name, last_name, country, email, login));
    }
    
    //used to get all user data from database into a bean 
    //was based on the IsValidUser method below
    public UserProfile getUserProfile(String username){
        //sets up needed objects and the cql statements to read from database
        UserProfile userProfile = new UserProfile();
        Session session = cluster.connect("instagrim");
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
    
    public boolean searchForUser(String username)
    {
        //UserProfile userProfile = new UserProfile();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from userprofiles where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        
        rs=session.execute(boundStatement.bind(username)); // executes statement
        
        session.close();
        
        if (rs.isExhausted()) {
            System.out.println("User does not exist");
            return false;
        }else{ 
            return true;
        }
    }
    
    public void addComment(String commenterUsername, String comment, String profileUsername)
    {
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into usercomments (commentID, commenterusername, comment, profileusername) Values(?,?,?,?)");
        
        Convertors convertor = new Convertors();
        java.util.UUID commentID = convertor.getTimeUUID();
        
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(commentID, commenterUsername, comment, profileUsername));
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
        Session session = cluster.connect("instagrim");
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
