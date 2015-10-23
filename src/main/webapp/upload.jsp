<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
            <h1><a href="/InstagrimSWTurner">InstaGrim!</a></h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
               <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
                <li><a href="/InstagrimSWTurner/Logout">Logout</a></li>
                <li class="nav"><a href="upload.jsp">Upload</a></li>
                <li class="nav"><a href="/InstagrimSWTurner/Images/majed">Sample Images</a></li>
                <li><a href="/InstagrimSWTurner/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                <p>File to upload:</p> <input type="file" name="upfile"><br/>

                <br/>
                <input class="button" type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                 <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
