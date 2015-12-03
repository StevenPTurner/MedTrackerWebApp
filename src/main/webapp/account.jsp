<%-- 
    Document   : profile
    Created on : 04-Oct-2015, 15:15:08
    Author     : steven
--%>

<%@page import="java.util.Iterator"%>
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
                        <li><a href="/MedTracker/MyMeds/myMeds.jsp"><i class="fa fa-eye"></i><br>View Meds</a></li>
                        <li><a href="/MedTracker/addNewMed.jsp"><i class="fa fa-plus"></i><br>Add Meds</a></li>
                    </ul>
                </div>
                <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                        } %>
                <div class="topnav">
                    <ul class="left">
                        <li><a href="/MedTracker/">Home</a></li>
                        <li><a href="/MedTracker/Account/<%=lg.getUsername()%>">My Account</a></li>
                        <li><a href="/MedTracker/aboutus.jsp">About Us</a></li>
                    </ul>
                    <ul class="right">
                        <li><a href="#">Settings</a></li>
                        <li><a href="/MedTracker/Logout">Log Out</a></li>
                    </ul>
                </div>
                <div class="content">
                    <div class="split-title">
                        <h1><%=lg.getUsername()%>'s Account <span><a href="/MedTracker/EditAccount/<%=lg.getUsername()%>"> Edit Account</a></span></h1><hr>
                    </div>
                    <table class="table2 table-striped">
                        <tr>
                            <td>Name:</td>
                            <td><%=userProfile.getFirstName()%> <%=userProfile.getLastName()%></td>
                        </tr>
                        <tr>
                            <td>Username:</td>
                            <td><%=userProfile.getUsername()%></td>
                        </tr>
                        <tr>
                            <td>Joined on:</td>
                            <td><%=userProfile.getJoinDate()%></td>
                        </tr>
                        <tr>
                            <td>E-mail Address:</td>
                            <td><%=userProfile.getEmail()%></td>
                        </tr>
                    
                    </table>
                    
                </div>
            </div>
        </div>
    </body>
</html>