<%-- 
    Document   : edit
    Created on : 06-Oct-2015, 13:23:31
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
        <link href='https://fonts.googleapis.com/css?family=NTR|Yellowtail' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">
            <div id="dashboard">
                <div class="sidebar">
                    <span class="logo"><img src="/MedTracker/logo-white.png" alt=""/></span>
                    <ul>
                        <li><a href="/MedTracker/"><i class="fa fa-desktop"></i><br>Dashboard</a></li>
                        <li><a href="/MedTracker/myMeds.jsp"><i class="fa fa-eye"></i><br>View Meds</a></li>
                        <li><a href="/MedTracker/addNewMed.jsp"><i class="fa fa-plus"></i><br>Add Meds</a></li>
                    </ul>
                </div>
                <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
                <% String UserName = lg.getUsername(); %>
                <%  UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile"); %>
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
                    <h1><%=lg.getUsername()%>'s Account</h1><hr>
                    <% if(UserName.equals(userProfile.getUsername())) { %>
                    <form method="POST"  action="EditAccount/<%=lg.getUsername() %>">
                        <input type="hidden" name="login" value="<%=lg.getUsername() %>" readonly="readonly">
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" name="first_name" id="first_name" value="<%=userProfile.getFirstName()%>">
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label>
                            <input type="text" name="last_name" id="last_name" value="<%=userProfile.getLastName()%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type ="email" name="email" value="<%=userProfile.getEmail()%>">
                        </div>
                        <button type="submit">Edit Details</button>
                    </form>
                    <% } else { %>
                    <h3> ERROR not your profile </h3>
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>