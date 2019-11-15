<%-- 
    Document   : addAvenger
    Created on : 6-Nov-2019, 8:25:42 PM
    Author     : Michal Zarnowski
    Description: JSP page used to display the form for adding a new Avenger to 
                 the database. This page uses a custom tag (PowerSourceHandler)
                 to display a drop down list of pwoer sources acquired from the 
                 database for the user to choose from.
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

        <!-- New Avneger details input form -->
        <form action="AddAvenger.do" method="POST">
            Name: <input type="text" name="name" class="input-text"><br><br>
            Description: <input type="text" name="description" class="input-text"><br><br>
            Power Source: <powerSourceHandler:PowerSourceOptions /><br><br>
            <input type="submit" value="Submit" class="btn">
        </form>
            
        <br><br>
        
        <!-- Link to return to home page -->
        <a class="fill-button" href="index.html">
            <span class="fill-button-hover">		
                <span class="fill-button-text">Home</span>
            </span>
        </a>

    </body>
</html>
