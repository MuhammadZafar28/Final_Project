package com.groupfour.restaurantmap;

import java.util.ArrayList;

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
    private double latitude;
    private double longitude;
    private ArrayList<Inspection> inspections;
    
    // true if this is a shallow object; i.e. no inspection data is stored, just biographical
    private boolean isShallow;
    
    public Restaurant(int CAMIS, String DBA, Borough borough, String building, String street, 
                    String zipCode, String phone, String foodType, 
                    double latitude, double longitude, boolean isShallow) {
        this.CAMIS = CAMIS;
        this.DBA = DBA;
        this.borough = borough;
        this.building = building;
        this.street = street;
        this.zipCode = zipCode;
        this.phone = phone;
        this.foodType = foodType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isShallow = isShallow;
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
    
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    public ArrayList<Inspection> getInspections() throws ShallowObjectException {
        if(isShallow) {
            throw new ShallowObjectException();
        } else {
            return inspections;
        }
    }
    
    public void setInspections(ArrayList<Inspection> inspections) {
        if(inspections == null) {
            return;
        }
        isShallow = false;
        this.inspections = inspections;
    }
    
    public boolean isShallow() {
        return isShallow;
    }
    
    @Override
    public int hashCode() {
        return CAMIS; // the CAMIS is unique per restaurant
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Restaurant rest = (Restaurant)obj;
        return this.CAMIS == rest.getCAMIS();
    }
}

class ShallowObjectException extends Exception {}