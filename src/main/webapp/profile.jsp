<%-- 
    Document   : profile
    Created on : 04-Oct-2015, 15:15:08
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
            <h1><a href="/Instagrim">InstaGrim!</a> </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

                <li><a href="/Instagrim/Logout">Logout</a></li>
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        UserProfile userProfile = (UserProfile) request.getAttribute("UserProfile");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <!-- only .equals would work, no idea why-->
                    <%          if(UserName.equals(userProfile.getUsername())) { %>               
                <li><a href="/Instagrim/EditProfile/<%=lg.getUsername()%>">Edit</a></li>
                    <%          } 
                            } else {
                    %>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                    <%      } 
                        }
                    %>
              
            </ul>
        </nav>
            <article>
                <h1><%=userProfile.getUsername()%></h1>
                <img class = "avatar" src="../images/avatar.png">
                <ul>
                    <li>Name: <%=userProfile.getFirstName()%> <%=userProfile.getLastName()%></li>
                    <li>Joined on:  <%=userProfile.getJoinDate()%></li>
                    <li>From: <%=userProfile.getCountry()%></li>
                    <li>E-mail address: <%=userProfile.getEmail()%></li>
                </ul>
                
                <table>
                    <tr>
                        <th>Profile Comments</th>
                    </tr>
                </table>
                
                <a href="../Comment/<%=userProfile.getUsername()%>">leave a comment</a>
                
            </article>
        <footer>
            <ul>
                <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
