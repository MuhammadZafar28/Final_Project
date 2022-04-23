package com.groupfour.restaurantmap;

/**
 *
 * @author ndars
 */
public class TestDriver {
    public static void main(String[] args) {
        QueryBuilder qb = new APIQueryBuilder();
        qb.setDba("pizza hut");
        qb.setBorough(Borough.BRONX);
        qb.setAddress("2829 edson avenue");
        qb.setFoodType("Pizza");
        Query q = qb.getQuery();
        System.out.println(q);
        
        DataFetcher df = new APIDataFetcher();
        df.fetchData(q);
    }
}
