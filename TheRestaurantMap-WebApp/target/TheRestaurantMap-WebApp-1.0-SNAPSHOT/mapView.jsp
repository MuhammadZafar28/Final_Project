<%-- 
    Document   : mapView
    Created on : May 10, 2022, 11:45:28 AM
    Author     : jesus
--%>

<%@page import="com.groupfour.therestaurantmap.webapp.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
    <style>
      #map {position: absolute; top: 0; right: 0; bottom: 0; left: 0;}
    </style>
  </head>
  <body>
    
        </<div><h1>Hello world: ${helloLat} </h1></div>
    <div id="map">
      <a href="https://www.maptiler.com" style="position:absolute;left:10px;bottom:10px;z-index:999;"><img src="https://api.maptiler.com/resources/logo.svg" alt="MapTiler logo"></a>
    </div>
    <p><a href="https://www.maptiler.com/copyright/" target="_blank">© MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">© OpenStreetMap contributors</a></p>
    <script>
       
      var map = L.map('map').setView([40.71, -74], 1);
      L.tileLayer('https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=7EDqqTFt0P3CedsxRJwU',{
        tileSize: 512,
        zoomOffset: -1,
        minZoom: 2,
        attribution: "\u003ca href=\"https://www.maptiler.com/copyright/\" target=\"_blank\"\u003e\u0026copy; MapTiler\u003c/a\u003e \u003ca href=\"https://www.openstreetmap.org/copyright\" target=\"_blank\"\u003e\u0026copy; OpenStreetMap contributors\u003c/a\u003e",
        crossOrigin: true
      }).addTo(map);

    <% 
        
        Restaurant temp = new Restaurant();
        String myLat = temp.lattitude;
        String myLon = temp.longitude;
            %> 

    var lat = 40.73;
    var lon = -73.5;           
    var lat2 =  parseFloat(<%= myLat%>);
    var lon2 = parseFloat(<%= myLon%>);            
    
    var marker = L.marker([lat,lon]).addTo(map);
      
      
    </script>
  </body>
</html>
