/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.lib;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

/**
 *
 * @author steven
 */
public class AlertMail {
       public void sendNoticeMail(String userEmail, String firstName, String lastName, String medicineName)
        {
        final String username = "MedTrackerAlert@gmail.com";
        final String password = "54321ytrewq";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("MedTrackerAlert@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(userEmail));
            message.setSubject("ALERT: a perscription has almost ran out");
            
            String messageBody = "Hello " + firstName + " " + lastName + " your perscription of " + medicineName +
                                 " has almost ran out. Please get a new perscription then hit the refill prescription"
                                 + " Buttom on the alter under my meds, Thank you! ";
                   
            message.setText(messageBody);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
   }
   
}
