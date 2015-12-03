<%-- 
    Document   : editMed
    Created on : 23-Nov-2015, 20:28:19
    Author     : lee
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
                    <div id="page-content">
                    <span class="title">About Us</span>
                        <p>MedTracker is an application that is designed to streamline the process of remembering to take your medication. Once you have registered with us, the only work you have to do is to input your medication details and we do the rest for you.</p>
                        <p>You will receive a simple alert to remind you when it is time to take your medicine, when you are running low on your medicine, and on top of that, allow you to order a repeat prescription.</p>
                        <p>Take away the stress, and sign up for a free account now.</p>
                    
                </div>
            </div>
    </body>
</html>