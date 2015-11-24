/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.stores;

import java.util.Date;
import java.util.UUID;



/**
 *
 * @author steven
 */
public class Medicine {

    private UUID id = null;
    private String username = null;
    private String medicineName = null;
    private String instructions = null;
    private int dose = 0;
    private int doseLeft = 0;
    private int timeBetween = 0;
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
    
    public void setID(UUID id){
        this.id=id;
    }
    
    public void setDoseLeft(int doseLeft){
        this.doseLeft=doseLeft;
    }
    
    public void setTimeBetween(int timeBetween){
        this.timeBetween = timeBetween;
    }
    
    public UUID getID()
    {
        return id;
    }
    
    public int getDosesLeft()
    {
        return doseLeft;
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
    
    public String getUsername()
    {
        return username;
    }
    
    public Date getLastTaken()
    {
        return lastTaken;
    }
    
    public int getTimeBetween()
    {
        return timeBetween;
    }
    
    
    
}
