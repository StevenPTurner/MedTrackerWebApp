<%-- 
    Document   : pageNotFound
    Created on : 27-Sep-2015, 14:58:55
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us - MedTracker</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <link href='https://fonts.googleapis.com/css?family=Playfair+Display:400,400italic|Lato:400,400italic,300,300italic' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="container">
            <div id="page-header">
                <div class="logo"><a href="/MedTracker/"><img src="logo.png"></a></div>
                <div class="buttons">
                    <ul>
                        <li><a href="about.jsp">About Us</a></li>
                        <li><a href="register.jsp">Register</a></li>
                        <li><a href="login.jsp">Login</a></li>
                    </ul>
                </div>
            </div>
            <div id="page-content">
                <span class="title">ERROR 404</span>
                <p>Sorry, the page you tried to access doesn't exist.</p>
                <p><a href='/MedTracker/'>Go Home</a></p>
            </div>
            <footer>
            </footer>
        </div>
    </body>
</html>
