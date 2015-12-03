<%-- 
    Document   : editMed
    Created on : 23-Nov-2015, 20:28:19
    Author     : steven
--%>

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
                <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
                <%Medicine userMed = (Medicine) request.getAttribute("userMed"); %>
                <div class="topnav">
                    <ul class="left">
                        <li><a href="/MedTracker/">Home</a></li>
                        <li><a href="/MedTracker/Account/<%=lg.getUsername()%>">My Account</a></li>
                        <li><a href="aboutus.jsp">About Us</a></li>
                    </ul>
                    <ul class="right">
                        <li><a href="#">Settings</a></li>
                        <li><a href="/MedTracker/Logout">Log Out</a></li>
                    </ul>
                </div>
                <div class="content">
                    <h1>Edit <%=lg.getUsername()%>'s Medicine</h1><hr>
                    <form method="POST"  action="EditMed/<%=userMed.getID()%>">
                        <input type="hidden" name="login" value="<%=userMed.getUsername() %>" readonly="readonly">
                        <input type="hidden" name="id" value="<%=userMed.getID() %>" readonly="readonly">
                        <input type="hidden" name="medicine_name" value="<%=userMed.getMedicineName() %>" readonly="readonly"></li>
                        <div class='form-group'>
                            <label for='instructions'>Instructions</label>
                            <input type="text" name="instructions" id="instructions" value="<%=userMed.getInstructions()%>">
                        </div>
                        <div class='form-group'>
                            <label for='dose'>Dose</label>
                            <input type="text" name="dose" id="dose" value="<%=userMed.getDose()%>">
                        </div>
                        <div class='form-group'>
                            <label for='time_between'>Time Between Doses</label>
                            <input type="text" name="time_between" id="time_between" value="<%=userMed.getTimeBetween()%>">
                        </div>
                        <button type="submit">Edit Details</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
