<%-- 
    Document   : invalidInput
    Created on : 14-Nov-2019, 12:56:53 PM
    Author     : Michal Zarnowski
    Description: JSP page used as an error page when invalid input is detected
                 by the AddAvenger servlet inside the form for adding a new
                 Avenger into the database.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid Avenger</title>
        <link rel='stylesheet' type='text/css' href='css/errorMain.css' />
        <link rel='stylesheet' type='text/css' href='css/button.css' />
        <link href="https://fonts.googleapis.com/css?family=Marvel&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <!-- Error message -->
        <h1>Oops! Something went wrong.</h1>
        <h2>Please try again with valid Avenger details.</h2>
        <br><br><br><br><br>
        
        <!-- Link to return to home page -->
        <a class="fill-button" href="index.html">
            <span class="fill-button-hover">		
                <span class="fill-button-text">Home</span>
            </span>
        </a>
    </body>
</html>
