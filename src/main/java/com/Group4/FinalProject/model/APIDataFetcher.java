/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

import com.google.gson.JsonArray;
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
 *
 * @author ramoyum
 */
public class APIDataFetcher implements DataFetcher{
    @Override
    public HashMap<Integer, Restaurant> fetchData(Query query) throws IllegalArgumentException {
        if(null == query) {
            throw new IllegalArgumentException("Fetcher cannot use null queries.");
        }
        if(!(query instanceof APIQuery)) {
            throw new IllegalArgumentException("Wrong query format for this fetcher! "
                    + "Required: APIQuery | Found: " + query.getClass());
        }
        
        try {
            URL queryURL = new URL(query.toString());
            HttpURLConnection conn = (HttpURLConnection) queryURL.openConnection();
            conn.setRequestMethod("GET");
            // open connection
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
            // iterate over the array to process each line
            for (int i = 0; i < response.size(); i++) {
                System.out.println(response.get(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(APIDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}
