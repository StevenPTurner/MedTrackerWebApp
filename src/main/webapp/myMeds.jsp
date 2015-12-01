<%-- 
    Document   : myMeds
    Created on : 23-Nov-2015, 12:11:53
    Author     : steven
--%>

<%@page import="uk.ac.dundee.computing.aec.MedTracker.models.User"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.MedTracker.stores.*" %>
<%@ include file= "header.jsp"%>
<%@ include file= "footer.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="../Styles.css" />
        <link rel="stylesheet" href="../flipclock.css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="/MedTracker/flipclock.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
        <header>
            
        </header>
        
    
        <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");       
        java.util.LinkedList<Medicine> myMeds = (java.util.LinkedList<Medicine>) request.getAttribute("meds"); %>
        <h3>Your medicines <%=lg.getUsername()%></h3>
        <table border="solid 1px black">
            <% if (myMeds == null) { %>
            <tr><h3>You have no medicines on your account </h3></td></tr>
            <%} else { %>
             <tr>
                <Th>Medicine</Th>
                <Th>Dose</Th>
                <Th>Instructions</Th>
                <Th>Last Taken</Th>
                <Th>Next Dose</Th>
                <Th>Doses Left</Th>
            <tr>
                
               <% Iterator<Medicine> iterator1;
                iterator1 = myMeds.iterator();
                while (iterator1.hasNext()) {
                    Medicine med = (Medicine) iterator1.next(); %>
            <tr>
                <td><%=med.getMedicineName()%> </td>
                <td><%=med.getDose()%> </td>
                <td><%=med.getInstructions()%> </td>
                <td><%=med.getFormattedDate(med.getLastTaken())%> </td>
                <td><%=med.getFormattedDate(med.getNextDose())%></td>
                <td><%=med.getDosesLeft()%> </td>
                <td><a href="/MedTracker/TakeDose/<%=med.getID()%>">Take Dose</a></td>
                <td><a href="/MedTracker/EditMed/<%=med.getID()%>">Edit</a></td>
                <td><a href="/MedTracker/DeleteMed/<%=med.getID()%>">Delete</a></td>
            </tr>   
            <%}
                    }%>
        </table>
        
        
        <div>
            <h3>Alerts</h3>
            <% if (myMeds == null) { %>
                <tr><h4>You have no new alerts </h4></td></tr>
            <%} else { %>
               <% Iterator<Medicine> iterator2;
                iterator2 = myMeds.iterator();
                while (iterator2.hasNext()) {
                    Medicine med = (Medicine) iterator2.next();%> 
                    <%--<%!int timeClock = (*3600);%>--%>
                    <div class="clock">Clock here</div>
                    <%if(med.getTimeLeft() <= 1) { %>
                        <p>You are due to take a dose of <%=med.getMedicineName()%> now <a href="/MedTracker/TakeDose/<%=med.getID()%>">Take Now</a></p>
                    <% } else { %>
                        <p>You are due to take a dose of <%=med.getMedicineName()%> in <%=med.getTimeLeft()%> hours at <%=med.getFormattedDate(med.getNextDose())%>
                    <% }
                    if (med.getDosesLeft() <=5){ %>
                        <p>You have only <%=med.getDosesLeft()%> remaining <a href="/MedTracker/Refil/<%=med.getID()%>">order prescription</a></p>
                        
                    <%}%>
           
            <%}
                    }%>
        </div>

        
        <script type="text/javascript">
            var clock = $('.clock').FlipClock('43200', {countdown: true});
        </script>
        <footer>
        </footer>
    
</html>


