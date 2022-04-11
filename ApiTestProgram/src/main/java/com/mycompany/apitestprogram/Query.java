package com.mycompany.apitestprogram;

/**
 * An abstract model for queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public abstract class Query {
    private String query;

    @Override
    public String toString() {
        return query;
    }
}
