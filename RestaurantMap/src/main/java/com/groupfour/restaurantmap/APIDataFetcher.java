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
import java.util.List;
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
            String jsonString = "";

            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }

            // put response into json array
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);
            for (int i = 0; i < response.size(); i++) {
                JsonObject line = response.get(i).getAsJsonObject();
                int camis = line.get("camis").getAsInt();
                if(!restaurants.containsKey(camis)) {
                    try {
                        Restaurant newRest = packageRestaurant(line);
                        restaurants.put(newRest.hashCode(), newRest);
                    } catch (IllegalArgumentException ex) {
                        // restaurant line is malformed; just skip it
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(APIDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurants;
    }   
    
    /**
     * Fetches deep inspection data for a single restaurant and packages it into the existing object.
     * @param rest
     * @return a single, deep Restaurant object containing all of the Restaurant's inspection data
     * @throws IllegalArgumentException if Query is not an APIQuery
     */
    @Override
    public Restaurant fetchInspections(Restaurant rest) throws IllegalArgumentException {
        if(null == rest) {
            throw new IllegalArgumentException("Fetcher cannot use null restaurants.");
        }
        
        try {
            String query = APIQuery.makeInspectionQuery(rest.getCAMIS());
            URL queryURL = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) queryURL.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            // read response
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            System.out.println("getting restaurant response");
            String jsonString = "";

            while ((inputLine = input.readLine()) != null) {
                jsonString += inputLine;
            }
            
            // put response into json array
            JsonArray response = (JsonArray) new JsonParser().parse(jsonString);
            for (int i = 0; i < response.size(); i++) {
                JsonObject line = response.get(i).getAsJsonObject();
                Inspection newInsp = packageInspection(line);
                try {
                    List<Inspection> existingInspections = rest.getInspections();
                    int inspIndex = existingInspections.indexOf(newInsp);
                    // if we already have this inspection, just add the new violation
                    // optimization: check this before we package the entire inspection, somehow
                    if(inspIndex != -1) {
                        System.out.println("adding violation to existing inspection");
                        Inspection existingInspection = existingInspections.get(inspIndex);
                        JsonElement violationCode = line.get("violation_code");
                        String violationCodeString = (violationCode == null) ? "N/A" : violationCode.getAsString();
                        existingInspection.addViolation(violationCodeString);
                    } else { // if we don't have this inspection, add it
                        System.out.println("adding inspection");
                        rest.addInspection(newInsp);
                    }
                } catch (InvalidViolationException ex) {
                   // we should already have caught this in packageInspection()
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(APIDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rest;
    }
    
    private Restaurant packageRestaurant(JsonObject restaurantLine) throws IllegalArgumentException {
        // pull each attribute
        JsonElement camis = restaurantLine.get("camis");
        JsonElement dba = restaurantLine.get("dba");
        JsonElement borough = restaurantLine.get("boro");
        JsonElement building = restaurantLine.get("building");
        JsonElement street = restaurantLine.get("street");
        JsonElement zipCode = restaurantLine.get("zipcode");
        JsonElement phone = restaurantLine.get("phone");
        JsonElement latitude = restaurantLine.get("latitude");
        JsonElement longitude = restaurantLine.get("longitude");
        JsonElement foodType = restaurantLine.get("cuisine_description");
        JsonElement grade = restaurantLine.get("grade");
        
        boolean anyNull = camis == null | dba == null | borough == null | 
                building == null | street == null | zipCode == null | phone == null | 
                latitude == null | longitude == null | foodType == null | grade == null;
        
        if(anyNull) {
            throw new IllegalArgumentException("Null fields are not permitted in Restaurant objects");
        }
        
        // do all the relevant casts
        int camisInt = camis.getAsInt();
        String dbaString = dba.getAsString();
        Borough boroughEnum = Borough.asEnum(borough.getAsString());
        String buildingString = building.getAsString();
        String streetString = street.getAsString();
        String zipCodeString = zipCode.getAsString();
        String phoneString = phone.getAsString();
        double latitudeDouble = latitude.getAsDouble();
        double longitudeDouble = longitude.getAsDouble();
        String foodTypeString = foodType.getAsString();
        Grade gradeEnum = Grade.asEnum(grade.getAsString());
        
        return new Restaurant(camisInt, dbaString, boroughEnum, 
                buildingString, streetString, zipCodeString, phoneString, 
                foodTypeString, latitudeDouble, longitudeDouble, gradeEnum);
    }
    
    private Inspection packageInspection(JsonObject inspectionLine) throws IllegalArgumentException {
        // pull each attribute
        JsonElement camis = inspectionLine.get("camis");
        JsonElement inspectionDate = inspectionLine.get("inspection_date");
        JsonElement inspectionType = inspectionLine.get("inspection_type");
        JsonElement action = inspectionLine.get("action");
        JsonElement grade = inspectionLine.get("grade");
        JsonElement score = inspectionLine.get("score");
        JsonElement violationCode = inspectionLine.get("violation_code");
        JsonElement violationDescription = inspectionLine.get("violation_description");
        
        boolean anyNull = camis == null | inspectionDate == null | inspectionType == null | action == null;
        if(anyNull) {
            throw new IllegalArgumentException("Null CAMIS, date, type, or action are not permitted in Inspection objects");
        }
        
        // do all the relevant casts
        int camisInt = camis.getAsInt();
        String inspectionDateString = inspectionDate.getAsString();
        String inspectionTypeString = inspectionType.getAsString();
        String actionString = action.getAsString();
        Grade gradeEnum = (grade == null) ? Grade.UNKNOWN : Grade.asEnum(grade.getAsString());
        int scoreInt = (score == null) ? -1 : score.getAsInt();
        String violationCodeString = (violationCode == null) ? "N/A" : violationCode.getAsString();
        String violationDescriptionString = (violationDescription == null) ? "N/A" : violationDescription.getAsString();
        Inspection insp = new Inspection(camisInt, inspectionDateString,inspectionTypeString, actionString, gradeEnum, scoreInt);
        try {
            insp.addViolation(violationCodeString);
        } catch (InvalidViolationException ex) {
            ViolationTable.addViolation(violationCodeString, violationDescriptionString);
            try {
                insp.addViolation(violationCodeString);
            } catch (InvalidViolationException ex1) {
                // do nothing; we fixed the problem already. sure wish java had a retry-catch
            }
        }
        return insp;
    }
    
}
