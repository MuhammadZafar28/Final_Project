package com.groupfour.restaurantmap;

/**
 * An abstract model for queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public abstract class Query {
    protected String query;

    @Override
    public String toString() {
        return query;
    }
}