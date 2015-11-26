/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.stores;

import java.text.SimpleDateFormat;
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
    private int dosePerPrescription = 0;
    private int timeBetween = 0;
    private int timeLeft = 0;
    private Date lastTaken = null;
    private Date nextDose = null;
    boolean takeDose = false;
    
    
    
    public void Medicine()
    {
        
    }
    
    public void setID(UUID id){
        this.id=id;
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
    
    public void setDoseLeft(int doseLeft){
        this.doseLeft=doseLeft;
    }
    
    public void setDosesPerPrescription(int dosesPerPrescription){
        this.dosePerPrescription = dosesPerPrescription;
    }
    
    public void setTimeBetween(int timeBetween){
        this.timeBetween = timeBetween;
    }
    
    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
    
    public void setLastTaken(Date lastTaken){
        this.lastTaken=lastTaken;
    }
    
    public void setNextDose(Date nextDose){
        this.nextDose = nextDose;
    }
    
    public void setTakeDose(boolean takeDose){
        this.takeDose = takeDose;
    }
   
    
    public UUID getID()
    {
        return id;
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
    
    public int getDosesLeft()
    {
        return doseLeft;
    }
    
    public int getDosesPerPresciption()
    {
        return dosePerPrescription;
    }
 
    public int getTimeBetween()
    {
        return timeBetween;
    }
    
    public int getTimeLeft()
    {
        return timeLeft;
    }
    
    public Date getLastTaken()
    {
        return lastTaken;
    }
    
    public Date getNextDose()
     {
         return nextDose;
     }
    
    public boolean getTakeDose()
    {
         return takeDose;
    }
    
    
    //used to format dates to viewable format
    public String getFormattedDate(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, hh:mm aaa");
        String formattedDate = format.format(date);
        //formattedDate = formattedDate.replace(".", " ");
        return formattedDate;
    }
    
    
}
