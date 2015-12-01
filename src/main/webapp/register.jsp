<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
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
        
        
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>First Name<input type="text" name="first_name"></li>
                    <li>Last Name <input type="text" name="last_name"></li>
                    <li>E-mail    <input type ="email" name="email"</li>
                    <li>Password  <input type="password" name="password"></li>
                </ul>
                <br/>
                <input class="button" type="submit" value="Register"> 
            </form>

        </article>
    </body>
</html>
