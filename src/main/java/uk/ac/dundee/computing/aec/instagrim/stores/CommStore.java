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
public class CommStore {

    private String commenter = null;
    private String comment = null;
    private String profile = null;
    
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
    
    
    public String getCommenter() {
        return commenter;
    }
    
    public String getComment() {
        return comment;
    }
    public String getprofile() {
        return profile;
    }
    
}


