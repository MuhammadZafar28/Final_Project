package com.groupfour.restaurantmap;

/**
 * An class representing API queries to the DOHMH restaurant inspection
 * database.
 *
 * @author Jon-CSC, ndars
 */
public class APIQuery extends Query {

    private final String ENDPOINT = "https://data.cityofnewyork.us/resource/43nn-pn8j.json";
    private final String SHALLOW_ATTRIBUTE_LIST = "camis,dba,boro,building,street,zipcode,phone,latitude,longitude,grade_date,grade,cuisine_description";
    
    // App token must be included at the end of the final URL.
    private final String APP_TOKEN = "Dxorb7ZOjkabbBiII4JMJhkQu";
    

    public APIQuery(String dba, Grade grade, String foodType,
            String building, String street, Borough borough, String zipCode) {

        // Choose query based on info provided, most specific to least
        
        // A specific restaurant at an exact address and name
        if (building != null && street != null && zipCode != null
                && dba != null) {
            street = formatString(street);
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+dba+LIKE+%27" + dba + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        /* Any restaurant at an exact address and grade, but no specific 
        name (ie, the food court in a mall)*/ 
        else if (building != null && street != null && zipCode != null
                && grade != null) {
            street = formatString(street);
            query = ENDPOINT 
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        /* Any restaurant at an exact address and food type, but no specific 
        name (ie, the food court in a mall*/  
        else if (building != null && street != null && zipCode != null
                && foodType != null) {
            street = formatString(street);
            foodType = formatString(foodType);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // Any restaurant at an exact address, regardless of grade or food type 
        else if (building != null && street != null && zipCode != null) {
            street = formatString(street);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=building+LIKE+%27" + building + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // A specific restaurant on a specific street & zip
        else if (street != null && zipCode != null && dba != null) {
            street = formatString(street);
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=dba+LIKE+%27" + dba + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }  
        // Any restaurant on a specific street with a specific grade
        else if (street != null && zipCode != null && grade != null) {
            street = formatString(street);
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=grade+LIKE+%27" + grade.inQueryFormat() + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // Any restaurant on a specific street with a specific cuisine type
        else if (street != null && zipCode != null && foodType != null) {
            street = formatString(street);
            foodType = formatString(foodType);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=cuisine_description+LIKE+%27" + foodType + "%27+AND+street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // All restaurants on a specific street
        else if (street != null && zipCode != null) {
            street = formatString(street);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=street+LIKE+%27" + street + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // A specific restaurant name within a specific zip
        else if (zipCode != null && dba != null) {
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=dba+LIKE+%27" + dba + "%27+AND+zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // Any restaurant in a specific zipcode with a specific grade 
        else if (zipCode != null && grade != null) {
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=zipcode+LIKE+%27" + zipCode + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // Any restaurant in a specific zipcode with a specific cuisine type 
        else if (zipCode != null && foodType != null) {
            foodType = formatString(foodType);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=zipcode+LIKE+%27" + zipCode + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // All restaurants within a specific zip code
        else if (zipCode != null) {
        query = ENDPOINT
                + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=zipcode+LIKE+%27" + zipCode + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // A specific restaurant name within a specific borough
        else if (borough != null && dba != null) {
            String formattedBoro = formatString(borough.toString());
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=boro+LIKE+%27" + formattedBoro + "%27+AND+dba+LIKE+%27" + dba + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // Any restaurant in a specific borough with a specific grade
        else if (borough != null && grade != null) {
            String formattedBoro = formatString(borough.toString());
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=boro+LIKE+%27" + formattedBoro + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // Any restaurant in a specific borough with a specific cuisine type
        else if (borough != null && foodType != null) {
            String formattedBoro = formatString(borough.toString());
            foodType = formatString(foodType);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=boro+LIKE+%27" + formattedBoro + "%27+AND+cuisine_description+LIKE+%27" + foodType + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        } 
        // All restaurants within a specific borough
        else if (borough != null) {
            String formattedBoro = formatString(borough.toString());
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=boro+LIKE+%27" + formattedBoro+ "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // All restaurants of a specific name and grade within the database
        else if(dba != null && grade != null){
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=dba+LIKE+%27" + dba + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // All restaurants with a specific cuisine and grade within the database
        else if(foodType != null && grade != null){
            foodType = formatString(foodType);
            query = ENDPOINT 
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=cuisine_description+LIKE+%27" + dba + "%27+AND+grade+LIKE+%27" + grade.inQueryFormat() + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // All restaurants with a specific name within the database
        else if(dba != null){
            dba = formatString(dba);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=dba+LIKE+%27" + dba + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // All restaurants with a specific cuisine type within the database
        else if(foodType != null){
            foodType = formatString(foodType);
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=cuisine_description+LIKE+%27" + foodType + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        // All restaurants with a specific grade within the database
        else if(grade != null){
            query = ENDPOINT
                    + "?$select=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$where=grade+LIKE+%27" + grade.inQueryFormat() + "%27"
                    + "&$group=" + SHALLOW_ATTRIBUTE_LIST
                    + "&$order=camis,grade_date+desc"
                    + "&$limit=40000"
                    + "&$$app_token=" + APP_TOKEN;
        }
        

//      
//        StringBuilder sb = new StringBuilder();
//        sb.append(ENDPOINT).append("?");
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
