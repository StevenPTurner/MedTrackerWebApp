<%-- 
    Document   : profile
    Created on : 04-Oct-2015, 15:15:08
    Author     : steven
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            
        </header>
         <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                        } %>
        <h3>Your Profile</h3>
                
        <ul>
            <li><%=userProfile.getFirstName()%> <%=userProfile.getLastName()%></li>
            <li>UserName: <%=userProfile.getUsername()%></li>
            <li>Joined on <%=userProfile.getJoinDate()%></li>
            <li>E-mail Address: <%=userProfile.getEmail()%></li>
        </ul>
        <a href="/MedTracker/EditAccount/<%=lg.getUsername()%>">Edit Account</a>
    </body>
</html>
