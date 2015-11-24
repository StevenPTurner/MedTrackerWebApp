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
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/MedTracker">MedTracker</a> </h1>
        </header>
            
        <% //LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
       <%//Medicine userMed = (Medicine) request.getAttribute("userMed"); %>
            
            <h3>Edit Medicine</h3>
            
            
            <form method="POST"  action="EditMed/<%//=lg.getUsername()%>/<%//=userMed.getMedicineName()%>">
                <ul>
                    <input type="hidden" name="login" value="<%//=lg.getUsername() %>" readonly="readonly"></li>
                    <input type="hidden" name="medicine_name" value="<%//=userMed.getMedicineName() %>" readonly="readonly"></li>
                    <li>First Name<input type="text" name="first_name" value="<%//=userMed.getInstructions()%>"></li>
                    <li>Last Name <input type="text" name="last_name" value="<%//=userMed.getDose()%>"></li>
                </ul>
                <br/>
                <input class="button" type="submit" value="Edit Details"> 
            </form>
     
                
        <footer>
        </footer>
    </body>
</html>

