<%-- 
    Document   : myMeds
    Created on : 23-Nov-2015, 12:11:53
    Author     : steven
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.MedTracker.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
        <header>
            <h1><a href="/MedTracker">MedTracker</a> </h1>
        </header>
        <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        java.util.LinkedList<Medicine> myMeds = (java.util.LinkedList<Medicine>) request.getAttribute("meds"); %>
        <h3>Your medicines</h3>
        <table>
            <tr>
                <Th>Username</Th>
                <Th>Medicine</Th>
                <Th>Dose</Th>
                <Th>Doses Left</Th>
                <Th>Instructions</Th>
                <Th>Last Taken</Th>
                <Th>Time Between Doses</Th>
            <tr>
            <% if (myMeds == null) { %>
            <tr><h3>You have no medicines on your account </h3></td></tr>
            <%} else { 
                Iterator<Medicine> iterator;
                iterator = myMeds.iterator();
                while (iterator.hasNext()) {
                    Medicine med = (Medicine) iterator.next(); %>
            <tr>
                <td><%=med.getUsername()%> </td>
                <td><%=med.getMedicineName()%> </td>
                <td><%=med.getDose()%> </td>
                <td><%=med.getDosesLeft()%> </td>
                <td><%=med.getInstructions()%> </td>
                <td><%=med.getLastTaken()%> </td>
                <td><%=med.getTimeBetween()%> </td>
                <td><a href="">Take Dose</d></td>
                <td><a href="/MedTracker/EditMed/<%=med.getID()%>">Edit</a></td>
                <td><a href="/MedTracker/DeleteMed/<%=med.getID()%>">Delete</a></td>
            </tr>   
            <%}
                    }%>
        </table>
        
        
        <footer>
        </footer>
    
</html>
