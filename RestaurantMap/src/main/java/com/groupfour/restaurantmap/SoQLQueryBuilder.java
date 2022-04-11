package com.groupfour.restaurantmap;

/**
 * A builder class for creating SoQL queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public class SoQLQueryBuilder extends QueryBuilder {
    @Override
    public Query getQuery() {
        return new SoQLQuery(dba, grade, foodType, address, borough, zipCode);
    }
}
