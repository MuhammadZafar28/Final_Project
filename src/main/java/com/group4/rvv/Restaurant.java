package com.group4.rvv;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which stores DOHMH restaurant and inspection data.
 * @author ndars
 */
public class Restaurant {

    private final int CAMIS;
    private final String DBA;
    private final Borough borough;
    private final String building;
    private final String street;
    private final String zipCode;
    private final String phone;
    private final String foodType;
    private final double latitude;
    private final double longitude;
    // the grade of the most recent, non-nulled grade inspection; needed to get grade of shallow restaurants
    private final Grade currentGrade;
    
    private ArrayList<Inspection> inspections;
    
    // true if this is a shallow object; i.e. no inspection data is stored, just biographical
    private boolean isShallow;
    
    public Restaurant(int CAMIS, String DBA, Borough borough, String building, String street, 
                    String zipCode, String phone, String foodType, double latitude, 
                    double longitude, Grade currentGrade) {
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
        this.currentGrade = currentGrade;
        this.isShallow = true;
        
        inspections = new ArrayList<>();
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
    
    public Grade getCurrentGrade() {
        return currentGrade;
    }
    
    /**
     * Returns a list of this Restaurant's inspections.
     * @return
     * @see #isShallow() for more about shallowness.
     */
    public List<Inspection> getInspections() {
        return inspections;
    }
    
    /**
     * Adds the given inspection to this restaurant. The inspection must correspond to this restaurant (i.e. have the same CAMIS) to be successfully added.
     * @param insp
     * @return true if the inspection was added, false if not
     */
    public boolean addInspection(Inspection insp) {
        if(this.CAMIS == insp.getCAMIS()) {
            inspections.add(insp);
            if(this.isShallow) {
                isShallow = false;
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the "shallowness" of this restaurant object.
     * A shallow restaurant contains only biographical data and no inspections.
     * Inversely, a deep(?) restaurant will contain a nonzero number of inspections.
     * @return 
     */
    public boolean isShallow() {
        return isShallow;
    }
 
    @Override
    public int hashCode() {
        return CAMIS; // the CAMIS is unique per restaurant
    }
    
    /**
     * Returns true iff the the provided restaurant has the same CAMIS.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Restaurant rest = (Restaurant)obj;
        return rest.getCAMIS() == this.CAMIS;
    }
}