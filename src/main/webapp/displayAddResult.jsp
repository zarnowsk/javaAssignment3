<%-- 
    Document   : displayAddResult
    Created on : 14-Nov-2019, 12:42:09 PM
    Author     : Michal Zarnowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avenger Added</title>
        <link rel='stylesheet' type='text/css' href='css/main.css' />
        <link rel='stylesheet' type='text/css' href='css/button.css' />
        <link href="https://fonts.googleapis.com/css?family=Marvel&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <c:choose>
            <c:when test="${queryResult == 1}">
                <h1>Avenger added successfully!</h1>
            </c:when>
            <c:when test="${queryResult == 0}">
                <h1>Oops! Something went wrong, Avenger not added.</h1>
            </c:when>
            <c:otherwise>
                <h1>There was an issue with database query, please check the database manually.</h1>
            </c:otherwise>
        </c:choose>

        <a class="fill-button" href="index.html">
            <span class="fill-button-hover">		
                <span class="fill-button-text">Home</span>
            </span>
        </a>
    </body>
</html>
