/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

/**
 * A class representing API queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public class APIQuery extends Query{
    private final String endpoint = "https://data.cityofnewyork.us/resource/43nn-pn8j.json";
    
    public APIQuery(String dba, Grade grade, String foodType,
            String address, Borough borough, String zipCode) {
        
        StringBuilder sb = new StringBuilder();
        sb.append(endpoint).append("?");
        if(null != dba) {
            dba = dba.replace(' ', '+').toUpperCase();
            sb.append("dba=").append(dba).append("&");
        }
        if(null != grade) {
            // TODO: make grades work!
            // if a user enters grade B, they expect to get a restaurant which is CURRENTLY graded B
            // the naive filter solution will just return rows for any B graded inspection, regardless of how long ago it was
            // have to make this use SoQL to find only restaurants with a most recent inspection of the given grade
        }
        if(null != foodType) {
            foodType = foodType.replace(' ', '+');
            sb.append("cuisine_description=").append(foodType).append("&");
        }
        if(null != address) {
            // takes the first "word" of the address as the building number and the rest as the street
            address = address.toUpperCase();
            String building = "";
            String street = "";
            for(int i = 0; i < address.length(); i++) {
                if(address.charAt(i) == ' ') {
                    building = address.substring(0, i);
                    street = address.substring(i, address.length());
                    break;
                }
            }
            street = street.trim().replace(' ', '+');
            sb.append("building=").append(building).append("&");
            sb.append("street=").append(street).append("&");
        }
        if(null != borough) {
            String boroughAsStr = borough.toString();
            boroughAsStr = boroughAsStr.replace(' ', '+');
            sb.append("boro=").append(boroughAsStr).append("&");
        }
        if(null != zipCode) {
            zipCode = zipCode.replace(' ', '+');
            sb.append("zipcode=").append(zipCode).append("&");
        }
        
        query = sb.toString();
        
        if(query.endsWith("&")) {
            query = query.substring(0,query.length()-1);
        }
    }
}
