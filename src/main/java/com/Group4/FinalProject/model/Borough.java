/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

/**
 * enum
 * @author ndars
 */
public enum Borough {
    BRONX,
    BROOKLYN,
    MANHATTAN,
    QUEENS,
    STATEN_ISLAND;
    
    @Override
    public String toString() {
        switch(this) {
            case BRONX: return "Bronx";
            case BROOKLYN: return "Brooklyn";
            case MANHATTAN: return "Manhattan";
            case QUEENS: return "Queens";
            case STATEN_ISLAND: return "Staten Island";
            default: throw new IllegalArgumentException();
        }
    }
}
