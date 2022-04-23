package com.groupfour.restaurantmap;

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
}
