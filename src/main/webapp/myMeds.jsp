<%-- 
    Document   : myMeds
    Created on : 23-Nov-2015, 12:11:53
    Author     : steven
--%>

<%@page import="uk.ac.dundee.computing.aec.MedTracker.models.User"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.MedTracker.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="/MedTracker/Styles.css" />
        <link rel="stylesheet" href="/MedTracker/flipclock.css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="/MedTracker/flipclock.js"></script>
        <link href='https://fonts.googleapis.com/css?family=NTR|Yellowtail' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">
            <div id="dashboard">
                <div class="sidebar">
                    <span class="logo"><img src="logo-white.png" alt=""/></span>
                    <ul>
                        <li><a href="/MedTracker/"><i class="fa fa-desktop"></i><br>Dashboard</a></li>
                        <li><a href="/MedTracker/myMeds.jsp"><i class="fa fa-eye"></i><br>View Meds</a></li>
                        <li><a href="/MedTracker/addNewMed.jsp"><i class="fa fa-plus"></i><br>Add Meds</a></li>
                    </ul>
                </div>
                <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");       
                java.util.LinkedList<Medicine> myMeds = (java.util.LinkedList<Medicine>) request.getAttribute("meds"); %>
                <div class="topnav">
                    <ul class="left">
                        <li><a href="/MedTracker/">Home</a></li>
                        <li><a href="/MedTracker/Account/<%=lg.getUsername()%>">My Account</a></li>
                        <li><a href="#">Support</a></li>
                    </ul>
                    <ul class="right">
                        <li><a href="#">Settings</a></li>
                        <li><a href="/MedTracker/Logout">Log Out</a></li>
                    </ul>
                </div>
                <div class="content">
                    <h1><%=lg.getUsername()%>'s Medicines</h1><hr>
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
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var clock = $('.clock').FlipClock('43200', {countdown: true});
        </script>
    </body>
</html>