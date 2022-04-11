package com.groupfour.restaurantmap;

/**
 * A class which stores DOHMH restaurant and inspection data.
 * @author ndars
 */
public class Restaurant {
    private int CAMIS;
    private String DBA;
    private Borough borough;
    private String building;
    private String street;
    private String zipCode;
    private String phone;
    private String foodType;
    //private SomeKindOfCollection<Inspection> inspections
    
    @Override
    public int hashCode() {
        return CAMIS; // the CAMIS is unique per restaurant
    }

    public int getCAMIS() {
        return CAMIS;
    }

    public String getDBA() {
        return DBA;
    }

    public Borough getBorough() {
        return borough;
    }

    public String getBuilding() {
        return building;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getFoodType() {
        return foodType;
    }
}
