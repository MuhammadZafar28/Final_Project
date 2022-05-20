package com.group4.rvv;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndars
 */
public class TestDriver {
    public static void main(String[] args) {
        QueryBuilder qb = new APIQueryBuilder();
        qb.setDba("mcdonald's");
        Query q = qb.getQuery();
        System.out.println(q);
        
        DataFetcher df = new APIDataFetcher();
        HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
        for(Restaurant rest : restaurants.values()) {
            // peek at all the biographical fields
            System.out.println("CAMIS: " + rest.getCAMIS());
            System.out.println("DBA: " + rest.getDBA());
            System.out.println("Borough: " + rest.getBorough());
            System.out.println("Building: " + rest.getBuilding());
            System.out.println("Street: " + rest.getStreet());
            System.out.println("Zip Code: " + rest.getZipCode());
            System.out.println("Phone: " + rest.getPhone());
            System.out.println("Food Type: " + rest.getFoodType());
            System.out.println("Lat: " + rest.getLatitude());
            System.out.println("Long: " + rest.getLongitude());
            System.out.println("Current grade: " + rest.getCurrentGrade());
            
            for(Inspection i : df.fetchInspections(rest).getInspections()) {
                // peek at all the inspection fields
                System.out.println("\tCAMIS: " + i.getCAMIS());
                System.out.println("\tInsp date: " + i.getDate());
                System.out.println("\tInsp type: " + i.getInspectionType());
                System.out.println("\tAction: " + i.getAction());
                System.out.println("\tGrade: " + i.getGrade());
                System.out.println("\tScore: " + i.getScore());
                
                for(String violCode : i.lookAtViolationCodes()) {
                    try {
                        System.out.println("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode));
                    } catch (InvalidViolationException ex) {
                        Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
