
<%-- 
    Document   : addNewMed
    Created on : 23-Nov-2015, 13:57:22
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
                    <h1>Add New Medicine</h1><hr>
                    <form method="POST"  action="AddNewMed">
                        <input type="hidden" name="login" value="<%=lg.getUsername() %>" readonly="readonly">
                        <div class="form-group">
                            <label for="medicine_name">Medicine Name</label>
                            <input type="text" name="medicine_name" id="medicine_name">
                        </div>
                        <div class="form-group">
                            <label for="dose">Dose (in mg)</label>
                            <input type="text" name="dose" id="dose">
                        </div>
                        <div class="form-group">
                            <label for="doses_left">Doses Per Prescription</label>
                            <input type="text" name="doses_left" id="doses_left">
                        </div>
                        <div class="form-group">
                            <label for="instructions">Instructions</label>
                            <input type="text" name="instructions" id="instructions">
                        </div>
                        <div class="form-group">
                            <label for="time_between">Time Between Doses In Hours</label>
                            <input type="text" name="time_between" id="time_between">
                        </div>
                        <button type="submit">Add Medicine</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>