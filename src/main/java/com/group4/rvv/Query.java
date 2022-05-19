package com.group4.rvv;

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