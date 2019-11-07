<%-- 
    Document   : displayAvengers
    Created on : 6-Nov-2019, 7:56:12 PM
    Author     : Michal Zarnowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avenger Listing</title>
    </head>
    <body>
        <h1>Here are the Avengers</h1>

        <!-- Display all avengers -->
        <%-- Loop through request attribute 'avengers'(ArrayList) to get
             relevant data --%>
        <c:forEach var="avenger" items="${avengers}">
            <h2>${avenger.name}, ${avenger.description}</h2>
            <p>Power Source: ${avenger.powerSource.description}</p>
        </c:forEach>

        <a href="index.html">Back to index</a>
    </body>
</html>
