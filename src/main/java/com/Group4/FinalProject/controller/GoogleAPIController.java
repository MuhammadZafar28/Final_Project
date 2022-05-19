/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.controller;

import com.Group4.FinalProject.model.Restaurant;
import com.Group4.FinalProject.service.GoogleAPIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramoyum
 */

@RestController
public class GoogleAPIController {
    @Autowired
    private GoogleAPIService gs;
    
    
    
    @GetMapping("/api/google")
    public ResponseEntity <List<Restaurant>> getAllRestaurants(){
        //List<Restaurant> r = gs.searchRestaurant();
        return ResponseEntity.ok(gs.searchRestaurant());
    }
}
