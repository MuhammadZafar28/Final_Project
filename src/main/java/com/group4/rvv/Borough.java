package com.group4.rvv;

/**
 * An enum representing the boroughs of New York City.
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
    
    public static Borough asEnum(String input) {
        switch(input) {
            case "Bronx": return BRONX;
            case "Brooklyn": return BROOKLYN;
            case "Manattan": return MANHATTAN;
            case "Queens": return QUEENS;
            case "Staten Island": return STATEN_ISLAND;
            default: throw new IllegalArgumentException();
        }
    }
}
