<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register - MedTracker</title>
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
                <span class="title">Register</span>
                <form method="POST" action="Register">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username">
                    </div>
                    <div class="form-group">
                        <label for="first_name">First Name</label>
                        <input type="text" name="first_name" id="first_name">
                    </div>
                    <div class="form-group">
                        <label for="last_name">Last Name</label>
                        <input type="text" name="last_name" id="last_name">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password">
                    </div>
                    ${error}
                    <button type="submit">Register</button>
                </form>
            </div>
            <footer>
            </footer>
        </div>
    </body>
</html>