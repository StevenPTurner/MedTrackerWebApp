
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
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/MedTracker">MedTracker</a></h1>
        </header>
        
        
            <h3>Add a New Medicine</h3>
            <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
            <form method="POST"  action="AddNewMed">
                <ul>
                    <input type="hidden" name="login" value="<%=lg.getUsername() %>" readonly="readonly"></li>
                    <li>Medicine Name<input type="text" name="medicine_name"></li>
                    <li>Instructions <input type="text" name="instructions"></li>
                    <li>Dose(Per pill) <input type="number" name="dose"></li>
                </ul>
                <br/>
                <input class="button" type="submit" value="Add medicine"> 
            </form>

        </article>
    </body>
</html>