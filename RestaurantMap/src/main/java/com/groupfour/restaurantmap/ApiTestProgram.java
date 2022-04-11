package com.groupfour.restaurantmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testing API calls to the OpenData restaurant inspection database.
 *
 * @author Jonathan
 */
public class ApiTestProgram {

    /**
     * Creates a local file containing all the data received from the full
     * database. Slow, takes around 2 minutes to write all 360000 lines to file.
     */
    public static void updateLocalJSONData() {
        try {
            // Get ALL data in database
            URL url = new URL("https://data.cityofnewyork.us/resource/43nn-pn8j.json?$limit=400000&$$app_token=Dxorb7ZOjkabbBiII4JMJhkQu");// + "?dba=" + searchName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // open connection
            conn.connect();
            // read response
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            System.out.println("Response received.");
            PrintWriter writer = new PrintWriter("localData.json", "UTF-8");

            while ((inputLine = input.readLine()) != null) {
                writer.println(inputLine);
                System.out.println("...");
            }
            writer.close();
        } catch (MalformedURLException ex ) {
            Logger.getLogger(ApiTestProgram.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiTestProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Queries the database API using a restaurant name, getting the first 1000 
     * results and prints them out to System.out.
     * @param restaurantName The restaurant name.
     */
    public static void getDataFromName(String restaurantName) {
        try {
            //Begin JSON
            String jsonString = "";

            // App token is Dxorb7ZOjkabbBiII4JMJhkQu
            URL url = new URL("https://data.cityofnewyork.us/resource/43nn-pn8j.json" 
                    + "?dba=" + restaurantName 
                    + "&$limit=1000"
                            + "&$$app_token=Dxorb7ZOjkabbBiII4JMJhkQu");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // open connection
            conn.connect();
            // read response
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            System.out.println("Response received.");

            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }

            // put response into json array
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);
            // iterate over the array to process each line
            System.out.println("Latitude and Longitudes of " + restaurantName);
            for (int i = 0; i < response.size(); i++) {

                //some entries seem to not have lat long, printing those here
                if (response.get(i).getAsJsonObject().get("latitude").getAsDouble() == 0.0) {
                    System.out.println("ZERO LAT");
                    System.out.println(response.get(i));
                }

                // Print lat, long, and score
                System.out.println("Latitude: " + response.get(i).getAsJsonObject().get("latitude")
                        + " Longitude: " + response.get(i).getAsJsonObject().get("longitude")
                        + " Score: " + response.get(i).getAsJsonObject().get("grade"));

            }

            // close connection
            conn.disconnect();

        } catch (JsonSyntaxException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiTestProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
            //Test restaurant search by name
            System.out.println("Restaurant name:");
            Scanner scan = new Scanner(System.in);
            String searchName = scan.nextLine();
            getDataFromName(searchName);
            
            //Store full database locally in JSON text file
            //updateLocalJSONData();
    }
}