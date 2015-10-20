/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;



/**
 *
 * @author steven
 */

//bean used to store user data when taken from database
public class UserProfile {

    private String username=null;
    private String firstName=null;
    private String lastName=null;
    private String email=null;
    private String country=null;
    private String joinDate=null;
    
    public void UserProfile(){
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getJoinDate() {
        return joinDate;
    }
    
}

