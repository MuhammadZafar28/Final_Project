package com.groupfour.restaurantmap;

/**
 * An class representing API queries to the DOHMH restaurant inspection
 * database.
 *
 * @author Jon-CSC
 */
public class APIQuery extends Query {

    private static final String endpoint = "https://data.cityofnewyork.us/resource/43nn-pn8j.json";
    /* App token for our program isis Dxorb7ZOjkabbBiII4JMJhkQu, must be 
    included at the end of the final URL.*/
    private static final String apiKey = "Dxorb7ZOjkabbBiII4JMJhkQu";
    private final String selectStatement = "?$select=camis,dba,boro,building,street,zipcode,phone,latitude,longitude,grade_date,grade,cuisine_description";
    private final String groupStatement = "&$group=camis,dba,boro,building,street,zipcode,phone,latitude,longitude,grade_date,grade,cuisine_description";
    private final String orderStatement = "&$order=camis,grade_date+desc";
    private final String limitStatement = "&$limit=40000";
    private static final String apiKeyStatement = "&$$app_token=" + apiKey;;

    
    public APIQuery(String dba, Grade grade, String foodType,
            String building, String street, Borough borough, String zipCode) {

        query = endpoint + selectStatement;
        // Choose where clause based on info provided, most specific to least
        
        // A specific restaurant at an exact address and name
        if (building != null && street != null && zipCode != null
                && dba != null) {
            street = formatString(street);
            dba = formatString(dba);
            query += "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+dba+LIKE+%27" + dba + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
                    
        }
        /* Any restaurant at an exact address and grade, but no specific 
        name (ie, the food court in a mall)*/ 
        else if (building != null && street != null && zipCode != null
                && grade != null) {
            street = formatString(street);
            query += "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        } 
        /* Any restaurant at an exact address and food type, but no specific 
        name (ie, the food court in a mall*/  
        else if (building != null && street != null && zipCode != null
                && foodType != null) {
            street = formatString(street);
            foodType = formatString(foodType);
            query += "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        }
        // Any restaurant at an exact address, regardless of grade or food type 
        else if (building != null && street != null && zipCode != null) {
            street = formatString(street);
            query += "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        } 
        // A specific restaurant on a specific street & zip
        else if (street != null && zipCode != null && dba != null) {
            street = formatString(street);
            dba = formatString(dba);
            query += "&$where=dba+LIKE+%27" + dba + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        }  
        // Any restaurant on a specific street with a specific grade
        else if (street != null && zipCode != null && grade != null) {
            street = formatString(street);
            dba = formatString(dba);
            query += "&$where=grade+LIKE+%27" + grade.inQueryFormat() + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        } 
        // Any restaurant on a specific street with a specific cuisine type
        else if (street != null && zipCode != null && foodType != null) {
            street = formatString(street);
            foodType = formatString(foodType);
            query += "&$where=cuisine_description+LIKE+%27" + foodType + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        } 
        // All restaurants on a specific street
        else if (street != null && zipCode != null) {
            street = formatString(street);
            query += "&$where=street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        }
        else if (street != null && dba != null) {
            street = formatString(street);
            dba = formatString(dba);
            query += "&$where=street+LIKE+%27" + street + "%27+AND+dba+LIKE+%27" + dba + "%27";
        } 
        // A specific restaurant name within a specific zip
        else if (zipCode != null && dba != null) {
            dba = formatString(dba);
            query += "&$where=dba+LIKE+%27" + dba + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27";
        }
        // Any restaurant in a specific zipcode with a specific grade 
        else if (zipCode != null && grade != null) {
            query += "&$where=zipcode+LIKE+%27" + zipCode + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27";
        }
        // Any restaurant in a specific zipcode with a specific cuisine type 
        else if (zipCode != null && foodType != null) {
            foodType = formatString(foodType);
            query += "&$where=zipcode+LIKE+%27" + zipCode + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27";
        } 
        // All restaurants within a specific zip code
        else if (zipCode != null) {
        query += "&$where=zipcode+LIKE+%27" + zipCode + "%27";
        } 
        // A specific restaurant name within a specific borough
        else if (borough != null && dba != null) {
            
            dba = formatString(dba);
            query += "&$where=boro+LIKE+%27" + borough.toString() + "%27+AND+dba+LIKE+%27" + dba + "%27";
        } 
        // Any restaurant in a specific borough with a specific grade
        else if (borough != null && grade != null) {
            
            query += "&$where=boro+LIKE+%27" + borough.toString() + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27";
        } 
        // Any restaurant in a specific borough with a specific cuisine type
        else if (borough != null && foodType != null) {
            foodType = formatString(foodType);
            query += "&$where=boro+LIKE+%27" + borough.toString() + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27";
        } 
        // All restaurants within a specific borough
        else if (borough != null) {
            query += "&$where=boro+LIKE+%27" + borough.toString() + "%27";
        }
        // All restaurants of a specific name and grade within the database
        else if(dba != null && grade != null){
            dba = formatString(dba);
            query += "&$where=dba+LIKE+%27" + dba + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27";
        }
        // All restaurants with a specific cuisine and grade within the database
        else if(foodType != null && grade != null){
            foodType = formatString(foodType);
            query += "&$where=cuisine_description+LIKE+%27" + dba + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27";
        }
        // All restaurants with a specific name within the database
        else if(dba != null){
            dba = formatString(dba);
            query += "&$where=dba+LIKE+%27" + dba + "%27";
        }
        // All restaurants with a specific cuisine type within the database
        else if(foodType != null){
            foodType = formatString(foodType);
            query += "&$where=cuisine_description+LIKE+%27" + foodType + "%27";
        }
        // All restaurants with a specific grade within the database
        else if(grade != null){
            query += "&$where=grade+LIKE+%27" + grade.inQueryFormat() + "%27";               
        }
        
        query += groupStatement + orderStatement + limitStatement + apiKeyStatement;
    }
    
    // makes a query that pulls all inspection data for a particular restaurant
    // yes, i'm aware making it a static method instead of refactoring is hacky leave me alone
    public static String makeInspectionQuery(int camis) {
        String inspQuery = endpoint + "?camis=" + camis + apiKeyStatement;
        return inspQuery;
    }
    
    /**
     * Replaces characters in strings to their URL encoded equivalents.
     * @param string
     * @return A formatted string with replaced characters.
     */
    private String formatString(String string){
        string = string.replaceAll("\\s", "\\+"); //replace all spaces with +
        string = string.replaceAll("\\'", "%27%27"); //replace ' with %27%27
        string = string.toUpperCase();
        return string;
    }
}
