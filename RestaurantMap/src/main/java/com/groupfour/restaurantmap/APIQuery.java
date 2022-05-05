package com.groupfour.restaurantmap;

/**
 * An class representing API queries to the DOHMH restaurant inspection
 * database.
 *
 * @author ndars
 */
public class APIQuery extends Query {

    private final String endpoint = "https://data.cityofnewyork.us/resource/43nn-pn8j.json";
    /* App token for our program isis Dxorb7ZOjkabbBiII4JMJhkQu, must be 
    included at the end of the final URL.*/
    private final String apiKey = "Dxorb7ZOjkabbBiII4JMJhkQu";

    public APIQuery(String dba, Grade grade, String foodType,
            String building, String street, Borough borough, String zipCode) {

        // Choose query based on info provided, most specific to least
        
        // A specific restaurant at an exact address and name
        if (building != null && street != null && zipCode != null
                && dba != null) {}
        /* Any restaurant at an exact address and grade, but no specific 
        name (ie, the food court in a mall)*/ 
        else if (building != null && street != null && zipCode != null
                && grade != null) {} 
        /* Any restaurant at an exact address and food type, but no specific 
        name (ie, the food court in a mall*/  
        else if (building != null && street != null && zipCode != null
                && foodType != null) {}
        // Any restaurant at an exact address, regardless of grade or food type 
        else if (building != null && street != null && zipCode != null) {} 
        // A specific restaurant on a specific street & zip
        else if (street != null && zipCode != null && dba != null) {}  
        // Any restaurant on a specific street with a specific grade
        else if (street != null && zipCode != null && grade != null) {} 
        // Any restaurant on a specific street with a specific cuisine type
        else if (street != null && zipCode != null && foodType != null) {} 
        // All restaurants on a specific street
        else if (street != null && zipCode != null) {} 
        // A specific restaurant name within a specific zip
        else if (zipCode != null && dba != null) {}
        // Any restaurant in a specific zipcode with a specific grade 
        else if (zipCode != null && grade != null) {}
        // Any restaurant in a specific zipcode with a specific cuisine type 
        else if (zipCode != null && foodType != null) {} 
        // All restaurants within a specific zip code
        else if (zipCode != null) {} 
        // A specific restaurant name within a specific borough
        else if (borough != null && dba != null) {} 
        // Any restaurant in a specific borough with a specific grade
        else if (borough != null && grade != null) {} 
        // Any restaurant in a specific borough with a specific cuisine type
        else if (borough != null && foodType != null) {} 
        // All restaurants within a specific borough
        else if (borough != null) {}
        // All restaurants of a specific name and grade within the database
        else if(dba != null && grade != null){}
        // All restaurants with a specific cuisine and grade within the database
        else if(foodType != null && grade != null){}
        // All restaurants with a specific name within the database
        else if(dba != null){}
        // All restaurants with a specific cuisine type within the database
        else if(foodType != null){}
        // All restaurants with a specific grade within the database
        else if(grade != null){}
//      
//        StringBuilder sb = new StringBuilder();
//        sb.append(endpoint).append("?");
//        if(null != dba) {
//            dba = dba.replace(' ', '+').toUpperCase();
//            sb.append("dba=").append(dba).append("&");
//        }
//        if(null != grade) {
//            // TODO: make grades work!
//            // if a user enters grade B, they expect to get a restaurant which is CURRENTLY graded B
//            // the naive filter solution will just return rows for any B graded inspection, regardless of how long ago it was
//            // have to make this use SoQL to find only restaurants with a most recent inspection of the given grade
//        }
//        if(null != foodType) {
//            foodType = foodType.replace(' ', '+');
//            sb.append("cuisine_description=").append(foodType).append("&");
//        }
//        if(null != address) {
//            // takes the first "word" of the address as the building number and the rest as the street
//            address = address.toUpperCase();
//            String building = "";
//            String street = "";
//            for(int i = 0; i < address.length(); i++) {
//                if(address.charAt(i) == ' ') {
//                    building = address.substring(0, i);
//                    street = address.substring(i, address.length());
//                    break;
//                }
//            }
//            street = street.trim().replace(' ', '+');
//            sb.append("building=").append(building).append("&");
//            sb.append("street=").append(street).append("&");
//        }
//        if(null != borough) {
//            String boroughAsStr = borough.toString();
//            boroughAsStr = boroughAsStr.replace(' ', '+');
//            sb.append("boro=").append(boroughAsStr).append("&");
//        }
//        if(null != zipCode) {
//            zipCode = zipCode.replace(' ', '+');
//            sb.append("zipcode=").append(zipCode).append("&");
//        }
//        
//        query = sb.toString();
//        
//        if(query.endsWith("&")) {
//            query = query.substring(0,query.length()-1);
//        }
    }
}
