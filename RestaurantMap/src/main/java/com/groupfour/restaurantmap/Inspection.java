package com.groupfour.restaurantmap;

import java.util.HashSet;

/**
 * A class which stores data for a particular DOHMH restaurant inspection.
 * @author ndars
 */
public class Inspection implements Comparable<Inspection> {
    
    // the following three fields uniquely identify an inspection
    private final int CAMIS; // the restaurant whose inspection this is
    private final String date; // the date of the inspection
    private final String inspectionType; // the inspection type; e.g. cycle, administrative, etc.
    
    private final String action; // the action taken as a result of the inspection
    private final Grade grade;
    private final int score;
    
    private HashSet<String> violationCodes; // a list of the violation codes found at this inspection
    
    public Inspection(int CAMIS, String date, String inspectionType, 
                        String action, Grade grade, int score) {
        this.CAMIS = CAMIS;
        this.date = date;
        this.inspectionType = inspectionType;
        this.action = action;
        this.grade = grade;
        this.score = score;
        
        violationCodes = new HashSet<>();
    }
    
    public int getCAMIS() {
        return CAMIS;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getInspectionType() {
        return inspectionType;
    }
    
    public void addViolationCode(String code) throws InvalidViolationException {
        // if it's canonical add it
        // if it's not throw the exception
    }
    
    @Override
    public int hashCode() {
        long rawHash = CAMIS + date.hashCode() + (inspectionType.hashCode() - date.hashCode())/2;
        return (int)(rawHash % Integer.MAX_VALUE);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Inspection insp = (Inspection)obj;
        return (insp.getCAMIS() == this.CAMIS) && 
                (insp.getDate().equals(this.date)) &&
                (insp.getInspectionType().equals(this.inspectionType));
    }

    @Override
    public int compareTo(Inspection insp) {
        if(insp == this) {
            return 0;
        }
        return -1*(insp.getDate().compareTo(this.date));
    }
}

class InvalidViolationException extends Exception {}