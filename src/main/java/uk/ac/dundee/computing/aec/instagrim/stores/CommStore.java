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

//used to store comments when taken from database using a bean makes this easy
//as it allows easy storage in a linked list
public class CommStore {

    private String commenter = null;
    private String comment = null;
    private String profile = null;
    private String datePosted = null;
    
    public void CommStore(){
    }
    
    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public void setDatePosted(String datePosted){
        this.datePosted = datePosted;
    }
    
    
    public String getCommenter() {
        return commenter;
    }
    
    public String getComment() {
        return comment;
    }
    public String getprofile() {
        return profile;
    }
    
    public String getDatePosted() {
        return datePosted;
    }
  
    
}


