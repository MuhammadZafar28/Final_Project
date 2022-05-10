<%-- 
    Document   : index
    Created on : May 10, 2022, 11:40:26 AM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Group 4: Restaurant Inspection Map Project</h1>
        <div>
            <form action="displayMap" method="post">
                Enter name of Restaurant:<input type="text" name="RestaurantName" required />
                <input type="submit" value="Display Map"/>
            </form>
        </div>
    </body>
</html>
