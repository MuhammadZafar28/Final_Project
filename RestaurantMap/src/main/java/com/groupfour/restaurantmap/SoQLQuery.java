package com.groupfour.restaurantmap;

/**
 * An class representing SoQL queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public class SoQLQuery extends Query {
    private final String endpoint = "https://data.cityofnewyork.us/resource/43nn-pn8j.json";
    
    public SoQLQuery(String dba, Grade grade, String foodType,
            String address, Borough borough, String zipCode) {
        // TODO: implement this!
        // it should build up a valid SoQL query using the endpoint string & the above params
        return null;
    }
}
