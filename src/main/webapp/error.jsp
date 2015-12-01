<%-- 
    Document   : error
    Created on : 27-Sep-2015, 15:53:33
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page isErrorPage="true"%>
<%@ include file= "header.jsp"%>
<%@ include file= "footer.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MedTracker</title>
        <link rel="stylesheet" type="text/css" href="/Styles.css" />

    </head>
    <body>
        <header >
        </header>
       
            <h1 class="Error">Something went wrong. Sorry =( </h1>
            <div class="homeButton "><a href="/MedTracker">Go home</a></div>
        <footer>
            <ul>
                 <li>&COPY; Steven Turner</li>
            </ul>
        </footer>
    </body>
</html>
