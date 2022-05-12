package com.groupfour.restaurantmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class capable of pulling and packaging data from the DOHMH restaurant inspection database using API queries.
 * @author ndars
 */
public class APIDataFetcher implements DataFetcher {

    /**
     * Fetches shallow restaurant data for a query targeting zero to many restaurants.
     * Query must be formatted to grab biographical data matching the fields of Restaurant.
     * @param query 
     * @return a HashMap of Restaurants keyed by each Restaurant's CAMIS
     * @throws IllegalArgumentException if Query is not an APIQuery
     */
    @Override
    public HashMap<Integer, Restaurant> fetchRestaurants(Query query) throws IllegalArgumentException {
        if(null == query) {
            throw new IllegalArgumentException("Fetcher cannot use null queries.");
        }
        if(!(query instanceof APIQuery)) {
            throw new IllegalArgumentException("Wrong query format for this fetcher! "
                    + "Required: APIQuery | Found: " + query.getClass());
        }
        
        HashMap<Integer, Restaurant> restaurants = new HashMap<>();
        try {
            URL queryURL = new URL(query.toString());
            HttpURLConnection conn = (HttpURLConnection) queryURL.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            // read response
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            System.out.println("Response received.");
            String jsonString = "";

            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }

            // put response into json array
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);
            for (int i = 0; i < response.size(); i++) {
                JsonObject line = response.get(i).getAsJsonObject();
                System.out.println(line);
                JsonElement camis = line.get("camis");
                JsonElement dba = line.get("dba");
                JsonElement borough = line.get("borough");
                JsonElement building = line.get("building");
                JsonElement street = line.get("street");
                JsonElement zipCode = line.get("zipcode");
                JsonElement phone = line.get("phone");
                JsonElement latitude = line.get("latitude");
                JsonElement longitude = line.get("longitude");
                JsonElement foodType = line.get("cuisine_description");
                JsonElement grade = line.get("grade");
            }
        } catch (IOException ex) {
            Logger.getLogger(APIDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurants;
    }   
    
    /**
     * Fetches deep inspeciton data for a query targeting a single restaurant.
     * Query must be formatted to grab inspection data matching the fields of Inspection.
     * @param query
     * @return a single, deep Restaurant object containing all of the Restaurant's inspection data
     * @throws IllegalArgumentException if Query is not an APIQuery
     */
    @Override
    public Restaurant fetchRestaurant(Query query) throws IllegalArgumentException {
        if(null == query) {
            throw new IllegalArgumentException("Fetcher cannot use null queries.");
        }
        if(!(query instanceof APIQuery)) {
            throw new IllegalArgumentException("Wrong query format for this fetcher! "
                    + "Required: APIQuery | Found: " + query.getClass());
        }
        
        return null;
    }
}
