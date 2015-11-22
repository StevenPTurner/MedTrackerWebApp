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
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/MedTracker">MedTracker</a> </h1>
        </header>
        
       <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
       <% String UserName = lg.getUsername(); %>
       <%  UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile"); %>
            
            <h3>Edit your profile: <%=lg.getUsername() %></h3>
            
            <% if(UserName.equals(userProfile.getUsername())) { %>
            <form method="POST"  action="Profile/<%=lg.getUsername() %>">
                <ul>
                    <input type="hidden" name="login" value="<%=lg.getUsername() %>" readonly="readonly"></li>
                    <li>First Name<input type="text" name="first_name" value="<%=userProfile.getFirstName()%>"></li>
                    <li>Last Name <input type="text" name="last_name" value="<%=userProfile.getLastName()%>"></li>
                    <li>E-mail    <input type ="email" name="email" value="<%=userProfile.getEmail()%>"</li>
                    <!--<li>Password  <input type="password" name="password"></li>-->
                </ul>
                <br/>
                <input class="button" type="submit" value="Edit Details"> 
            </form>
            <% } else { %>
            <h3> ERROR not your profile </h3>
            <%}%>
    </body>
</html>
