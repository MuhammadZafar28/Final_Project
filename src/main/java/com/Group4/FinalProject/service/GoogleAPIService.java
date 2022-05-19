/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.service;

import com.Group4.FinalProject.model.Restaurant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ramoyum
 */
@Service
public class GoogleAPIService {
    
    RestTemplate rt = new RestTemplate();
    String url = "http://localhost:8080/api/google";
    
    public List<Restaurant> searchRestaurant(){
        return new ArrayList<Restaurant>();
    }
    
    public ResponseEntity<Restaurant> res 
            = rt.getForEntity(url + "/1", Restaurant.class);

}
