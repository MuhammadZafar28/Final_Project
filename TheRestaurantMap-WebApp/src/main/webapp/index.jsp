<%-- 
    Document   : index
    Created on : May 10, 2022, 11:40:26 AM
    Author     : jesus
    MISSING: Omar's API Key************
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
        <iframe
            width="600"
            height="450"
            style="border:0"
            loading="lazy"
            allowfullscreen
            referrerpolicy="no-referrer-when-downgrade"
            src="https://www.google.com/maps/embed/v1/place?key=
            &q=Space+Needle,Seattle+WA">
        </iframe>
    </body>
</html>
