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
        StringBuilder sb = new StringBuilder();
        sb.append(endpoint).append("?");
        if(null != dba) {
            dba = dba.replace(' ', '+');
            sb.append("dba=").append(dba).append("&");
        }
        if(null != grade) {
            // needs to be modified; api uses 'Z' for pending, 'N' for not graded, 'P' for grade pending issued on reopening
            // also, if a user enters grade A, they expect to get a restaurant which is CURRENTLY graded A
            // have to change this to use SoQL to find only restaurants with a most recent inspection of the given grade
            String gradeAsStr = grade.name();
            sb.append("grade=").append(gradeAsStr).append("&");
        }
        if(null != foodType) {
            foodType = foodType.replace(' ', '+');
            sb.append("cuisine_description=").append(foodType).append("&");
        }
        if(null != address) {
            // needs to be modified; api uses separate building & street fields
        }
        if(null != borough) {
            String boroughAsStr = borough.name();
            sb.append("boro=").append(boroughAsStr).append("&");
        }
        if(null != zipCode) {
            zipCode = zipCode.replace(' ', '+');
            sb.append("zipcode=").append(zipCode).append("&");
        }
       query = sb.toString();
    }
}
