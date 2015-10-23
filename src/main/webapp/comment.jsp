<%-- 
    Document   : comment
    Created on : 11-Oct-2015, 13:37:22
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html class="background">
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="../Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/InstagrimSWTurner">InstaGrim!</a> </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

               
                <li><a href="upload.jsp">Upload</a></li>
                <li><a href="/InstagrimSWTurner/search.jsp">Search</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/InstagrimSWTurner/Logout">Logout</a></li>
                <li><a href="/InstagrimSWTurner/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/InstagrimSWTurner/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                
                    <%}
                            }else{
                                %>
                 <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                <%
                                        
                            
                    }%>
            </ul>
        </nav>
        
        <article>
            <h3>Make a comment</h3>
            <form method="POST"  action="Comment">
                <ul>
                    <li>Comment <input type="text" name="comment"></li>
                    
                </ul>
                <br/>
                <input class = "button" type="submit" value="Post Comment"> 
            </form>
        </article>
            
        <footer>
            <ul>
                <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>

