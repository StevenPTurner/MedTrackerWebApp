<%-- 
    Document   : edit
    Created on : 06-Oct-2015, 13:23:31
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html class="background">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="../Styles.css" />
    </head>
    <body>
        <header>
        <h1><a href="/InstagrimSWTurner">InstaGrim!</a></h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                
                <li><a href="/InstagrimSWTurner/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
       <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
       <% String UserName = lg.getUsername(); %>
       <%  UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile"); %>
        <article>
            
            <h3>Edit your profile: <%=lg.getUsername() %></h3>
            
            <% if(UserName.equals(userProfile.getUsername())) { %>
            <form method="POST"  action="Profile/<%=lg.getUsername() %>">
                <ul>
                    <input type="hidden" name="login" value="<%=lg.getUsername() %>" readonly="readonly"></li>
                    <li>First Name<input type="text" name="first_name"></li>
                    <li>Last Name <input type="text" name="last_name"></li>
                    <li>Country   <input type="text" name="country"></li>
                    <li>E-mail    <input type ="email" name="email"</li>
                    <!--<li>Password  <input type="password" name="password"></li>-->
                </ul>
                <br/>
                <input class="button" type="submit" value="Edit Details"> 
            </form>
            <% } else { %>
            <h3> ERROR not your profile </h3>
            <%}%>
        </article>
        <footer>
            <ul>
                 <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
