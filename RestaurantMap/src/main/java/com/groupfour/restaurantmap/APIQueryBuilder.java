package com.groupfour.restaurantmap;

/**
 * A builder class for creating API queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public class APIQueryBuilder extends QueryBuilder {
    @Override
    public Query getQuery() {
        return new APIQuery(dba, grade, foodType, building, street, borough, zipCode);
    }
}
