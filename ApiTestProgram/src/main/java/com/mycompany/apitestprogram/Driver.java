/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apitestprogram;

import java.util.Scanner;

/**
 *
 * @author jesus
 */
public class Driver {
    
    public static void main(String[] args){
    
    System.out.println("hello world");
    ApiTestProgram api = new ApiTestProgram();
   // api.getDataFromName("MCDONALD'S");
   //Test restaurant search by name
    System.out.println("Restaurant name:");
    Scanner scan = new Scanner(System.in);
    String searchName = scan.nextLine();
    api.getDataFromName(searchName);

    var theMap = new MapUI();
    theMap.setUpUI();
    
    }
    
}
