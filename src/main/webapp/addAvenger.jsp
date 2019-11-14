<%-- 
    Document   : addAvenger
    Created on : 6-Nov-2019, 8:25:42 PM
    Author     : Michal Zarnowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="powerSourceHandler" uri="/WEB-INF/tlds/tag_library.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Avenger</title>
        <link rel='stylesheet' type='text/css' href='css/main.css' />
        <link rel='stylesheet' type='text/css' href='css/button.css' />
        <link href="https://fonts.googleapis.com/css?family=Marvel&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <h1>Add an Avenger</h1>

        <form action="AddAvenger.do" method="POST">
            Name: <input type="text" name="name" class="input-text"><br><br>
            Description: <input type="text" name="description" class="input-text"><br><br>
            Power Source: <powerSourceHandler:PowerSourceOptions /><br><br>
            <input type="submit" value="Submit" class="btn">
        </form>
        <br><br>
        <a class="fill-button" href="index.html">
            <span class="fill-button-hover">		
                <span class="fill-button-text">Home</span>
            </span>
        </a>

    </body>
</html>
