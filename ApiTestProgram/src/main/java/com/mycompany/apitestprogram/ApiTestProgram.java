package com.mycompany.apitestprogram;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Testing API calls to the OpenData restaurant inspection database.
 *
 * @author Jonathan
 */
public class ApiTestProgram {

    public static void main(String[] args) {
        try {

            //Test restuarant search by name
            String searchName = "";
            Scanner scan = new Scanner(System.in);
            System.out.println("Restaurant name:");
            searchName = scan.next();

            //Begin JSON
            String jsonString = "";

            // this is a test url, must use an API key in final code
            URL url = new URL("https://data.cityofnewyork.us/resource/43nn-pn8j.json" + "?dba=" + searchName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // open connection
            conn.connect();
            // read response
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }
            // put response into json array
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);
            // iterate over the array to process each line
            System.out.println("Latitude and Longitudes of " + searchName);
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

        } catch (JsonSyntaxException | IOException ex) {
            System.err.println(ex);
        }
    }
}

