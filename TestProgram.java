package com.mycompany.jsondatafetchtest;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Testing API calls to the OpenData restaurant inspection database.
 * 
 * @author Jonathan
 */
public class TestProgram {

    public static void main(String[] args) {
        try {
            String jsonString = "";
            URL url = new URL("https://data.cityofnewyork.us/resource/43nn-pn8j.json?$limit=1000"); // this is a test url, must use an API key in final code, limit determines how many results returned
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);

            for (int i = 0; i < response.size(); i++) {
                System.out.println(response.get(i));
            }
            conn.disconnect();

        } catch (JsonSyntaxException | IOException ex) {
            System.err.println(ex);
        }
    }
}
