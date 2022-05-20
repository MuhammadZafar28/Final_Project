
package com.group4.rvv;

import java.util.HashMap;

/**
 * A static lookup table for DOHMH violation codes.
 * This starts empty; hardcoding the violations and their codes seems like a bad idea.
 * Instead, this should be built up during runtime as new violations are encountered.
 * @author ndars
 */
public class ViolationTable {
    
    // key is a violation code, value is the description
    private static HashMap<String, String> table = new HashMap<>();
            
    private ViolationTable() {
        // just here to prevent instantiation of an otherwise static lookup table
    }
    
    public static boolean isValidCode(String code) {
        return table.containsKey(code);
    }
    
    public static String lookup(String code) throws InvalidViolationException {
        if(!table.containsKey(code)) {
            throw new InvalidViolationException();
        } else {
            return table.get(code);
        }
    }
    
    public static void addViolation(String code, String description) {
        table.put(code, description);
    }
}

class InvalidViolationException extends Exception {}
