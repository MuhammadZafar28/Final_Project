package com.groupfour.restaurantmap;

import java.util.HashMap;

/**
 * A class capable of pulling and packaging data from the DOHMH restaurant inspection database using API queries.
 * @author ndars
 */
public class APIDataFetcher implements DataFetcher {

    @Override
    public HashMap<Integer, Restaurant> fetchData(Query query) throws IllegalArgumentException {
        if(null == query) {
            throw new IllegalArgumentException("Fetcher cannot use null queries.");
        }
        if(!(query instanceof APIQuery)) {
            throw new IllegalArgumentException("Wrong query format for this fetcher! "
                    + "Required: SoQLQuery | Found: " + query.getClass());
        }
        return 0;
    }   
}
