<%-- 
    Document   : allProfiles
    Created on : 20-Oct-2015, 16:37:16
    Author     : steven
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html class="background">
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1><a href="/Instagrim">InstaGrim!</a> </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

               
                <li><a href="upload.jsp">Upload</a></li>
                <li><a href="/Instagrim/search.jsp">Search</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                
                    <%}
                            }else{
                                %>
                 <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                <%}%>
            </ul>
        </nav>
            
        <article class="article2">
        <% java.util.LinkedList<UserProfile> allProfiles = (java.util.LinkedList<UserProfile>) request.getAttribute("allProfiles"); %>
            <h1>All Profiles</h1>
        <% if (allProfiles == null) { %>
            <li>there are no viewable profiles!</li>
        <%} else { 
            Iterator<UserProfile> iterator;
            iterator = allProfiles.iterator();
            while (iterator.hasNext()) {
                UserProfile profile = (UserProfile) iterator.next(); %>
            <li class="allUsers"><a href="/Instagrim/Profile/<%=profile.getUsername()%>"><%=profile.getUsername()%>: <%=profile.getFirstName()%> <%=profile.getLastName()%></a></li>
            <%}
        }%>
        
            
                
        </article> 
            
        <footer>
            <ul>
                <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
