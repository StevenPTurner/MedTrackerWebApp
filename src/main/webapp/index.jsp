<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.MedTracker.stores.*" %>
<%//@ include file= "header.jsp"%>
<%//@ include file= "footer.jsp"%>
<%
    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
    if (lg != null) {
        String UserName = lg.getUsername();
        if (lg.getlogedin()) {
%>
<%@include file="index-logged.jsp" %>
<%}
        }else{
%>
<%@include file="index-default.jsp" %>
<%}%>