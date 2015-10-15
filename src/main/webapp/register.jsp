<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="background">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <header>
        <h1><a href="/Instagrim">InstaGrim!</a></h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
       
        <article class="article2">
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>First Name<input type="text" name="first_name"></li>
                    <li>Last Name <input type="text" name="last_name"></li>
                    <li>Country   <input type="text" name="country"></li>
                    <li>E-mail    <input type ="email" name="email"</li>
                    <li>Password  <input type="password" name="password"></li>
                </ul>
                <br/>
                <input class="button" type="submit" value="Register"> 
            </form>

        </article>
        <footer>
            <ul>
                 <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
