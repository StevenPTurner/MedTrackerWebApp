<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.MedTracker.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/MedTracker">MedTracker</a> </h1>
        </header>
            <ul>
                <%
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    if (lg != null) {
                        String UserName = lg.getUsername();
                        if (lg.getlogedin()) {
                %>
                <li><a href="">MY Meds</a></li>
                <li><a href="/MedTracker/Account/<%=lg.getUsername()%>">My Account</a></li>
                <li><a href="/MedTracker/Logout">Logout</a>
                
                <%}
                    }else{
                          %>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="about.jsp">About</a></li>
                <li><a href="register.jsp">Register</a></li>
                <%}%>
            </ul>
        <footer>
        </footer>
    </body>
</html>
