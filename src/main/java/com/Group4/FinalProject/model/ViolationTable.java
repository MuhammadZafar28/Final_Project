/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

import java.util.HashMap;

/**
 *
 * @author ramoyum
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
