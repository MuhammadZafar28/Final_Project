/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apitestprogram;

/**
 *
 * @author jesus
 */
public class Driver {
    
    public static void main(String[] args){
    
    System.out.println("hello world");
    ApiTestProgram api = new ApiTestProgram();
    api.getDataFromName("MCDONALD'S");
    
    var theMap = new MapUI();
    theMap.setUpUI();
    
    
    
    }
    
}
