<%-- 
    Document   : addAvenger
    Created on : 6-Nov-2019, 8:25:42 PM
    Author     : Michal Zarnowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Avenger</title>
    </head>
    <body>
        <h1>Add an Avenger</h1>
        
        <form action="AddAvenger.do" method="POST">
            Name: <input type="text" name="name"><br><br>
            Description: <input type="text" name="description"><br><br>
            Power Source: <select></select><br><br>
            <input type="submit" value="Submit">
        </form>
        
    </body>
</html>
