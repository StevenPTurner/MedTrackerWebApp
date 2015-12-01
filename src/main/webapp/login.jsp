<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file= "header.jsp"%>
<%@ include file= "footer.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            
        </header>
        
        <h3>Login</h3>
            <form method="POST"  action="Login">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                ${error}
                <input class = "button" type="submit" value="Login"> 
    </body>
</html>
