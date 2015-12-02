<%-- 
    Document   : loggedout
    Created on : 04-Oct-2015, 11:19:27
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file= "header.jsp"%>
<%@ include file= "footer.jsp"%>
<%
    String redirectURL = "/MedTracker/";
    response.sendRedirect(redirectURL);
%>
