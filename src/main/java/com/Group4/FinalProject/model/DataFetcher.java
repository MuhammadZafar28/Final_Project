/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

import java.util.HashMap;

/**
 *
 * @author ramoyum
 */
public interface DataFetcher {
    public HashMap<Integer, Restaurant> fetchData(Query query);
}
