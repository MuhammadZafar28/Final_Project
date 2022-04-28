package com.groupfour.restaurantmap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    
    public String getAction() {
        return action;
    }

    public Grade getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    /**
     * Returns an unmodifiable set of this inspection's violation codes.
     * If you want to add to the set, use {@link #addViolation(java.lang.String) addViolation} instead.
     * @return 
     */
    public Set<String> lookAtViolationCodes() {
        return Collections.unmodifiableSet(violationCodes);
    }
    
    /**
     * Adds the given violation code to this inspection's violations.
     * @param code
     * @throws InvalidViolationException if the code does not map to an existing violation in {@link com.groupfour.restaurantmap.ViolationTable ViolationTable}.
     */
    public void addViolation(String code) throws InvalidViolationException {
       if(ViolationTable.isValidCode(code)) {
           violationCodes.add(code);
       } else {
           throw new InvalidViolationException();
       }
    }
        
    @Override
    public int hashCode() {
        long rawHash = CAMIS + date.hashCode() + (inspectionType.hashCode() - date.hashCode())/2;
        return (int)(rawHash % Integer.MAX_VALUE);
    }
    
    /**
     * Returns true iff the restaurant, the date, and the inspection type are all the same.
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
        Inspection insp = (Inspection)obj;
        return (insp.getCAMIS() == this.CAMIS) && 
                (insp.getDate().equals(this.date)) &&
                (insp.getInspectionType().equals(this.inspectionType));
    }

    /**
     * Orders inspections by date.
     * @param insp
     * @return -1 if this is more recent, 1 if insp is more recent, 0 if same date.
     */
    @Override
    public int compareTo(Inspection insp) {
        if(insp == this) {
            return 0;
        }
        return -1*(insp.getDate().compareTo(this.date));
    }
}