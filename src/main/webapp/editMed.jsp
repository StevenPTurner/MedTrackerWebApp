<%-- 
    Document   : editMed
    Created on : 23-Nov-2015, 20:28:19
    Author     : steven
--%>

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
            
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
       <%Medicine userMed = (Medicine) request.getAttribute("userMed"); %>
            
            <h3>Edit Medicine</h3>
            
            
            <form method="POST"  action="EditMed/<%=userMed.getID()%>">
                <ul>
                    <input type="hidden" name="login" value="<%=userMed.getUsername() %>" readonly="readonly"></li>
                    <input type="hidden" name="id" value="<%=userMed.getID() %>" readonly="readonly"></li>
                    <input type="hidden" name="medicine_name" value="<%=userMed.getMedicineName() %>" readonly="readonly"></li>
                    <li>Instructions<input type="text" name="instructions" value="<%=userMed.getInstructions()%>"></li>
                    <li>Dose <input type="text" name="dose" value="<%=userMed.getDose()%>"></li>
                    <li>Time Between Doses <input type="text" name="time_between" value="<%=userMed.getTimeBetween()%>"></li>
                </ul>
                <br/>
                <input class="button" type="submit" value="Edit Details"> 
            </form>
     
                
        <footer>
        </footer>
    </body>
</html>

