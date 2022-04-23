package com.groupfour.restaurantmap;

import java.util.HashMap;

/**
 * An interface for pulling and packaging restaurant data from the DOHMH restaurant database.
 * @author ndars
 */
public interface DataFetcher {
    public HashMap<Integer, Restaurant> fetchData(Query query);
}
