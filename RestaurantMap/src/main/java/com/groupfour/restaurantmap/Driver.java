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

    public static void main(String[] args) {

        System.out.println("hello world");
        ApiTestProgram api = new ApiTestProgram();

        //Test restaurant search by name
        // api.getDataFromName("MCDONALD'S");
        //System.out.println("Restaurant name:");
        //Scanner scan = new Scanner(System.in);
        //String searchName = scan.nextLine();
        //api.getDataFromName(searchName);
        //api.getLatestGradeDataFromName(searchName);
        // test by zip
        //api.getDataFromZip("10024");
        // test by address
        api.getDataFromAddress("570","lexington avenue");
//    var theMap = new MapUI();
//    theMap.setUpUI();
    }

}
