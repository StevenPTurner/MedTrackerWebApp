/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.stores;

import java.util.Date;



/**
 *
 * @author steven
 */
public class Medicine {

    private String username = null;
    private String medicineName = null;
    private String instructions = null;
    private int dose = 0;
    private Date lastTaken = null;
    
    public void Medicine()
    {
        
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setMedicineName(String medicineName){
        this.medicineName=medicineName;
    }
    
    public void setInstructions(String instructions){
        this.instructions=instructions;
    }
    
    public void setDose(int dose){
        this.dose=dose;
    }
    
    public void setLastTaken(Date lastTaken){
        this.lastTaken=lastTaken;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getMedicineName()
    {
        return medicineName;
    }
    
    public String getInstructions()
    {
        return instructions;
    }
    
    public int getDose()
    {
        return dose;
    }
    
    public Date getLastTaken()
    {
        return lastTaken;
    }
    
}
