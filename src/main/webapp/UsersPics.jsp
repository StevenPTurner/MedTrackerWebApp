<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html class="background">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/InstagrimSWTurner/Styles.css" />
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
                <li class="nav"><a href="/InstagrimSWTurner/upload.jsp">Upload</a></li>
                <li><a href="/InstagrimSWTurner/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                
            </ul>
        </nav>
 
        <article>
            <h1>Your Pics</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();

        %>
        <a href="/InstagrimSWTurner/Image/<%=p.getSUUID()%>" ><img src="/InstagrimSWTurner/Thumb/<%=p.getSUUID()%>"></a><br/><%

            }
            }
        %>
        </article>
        <footer>
            <ul>
                 <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
