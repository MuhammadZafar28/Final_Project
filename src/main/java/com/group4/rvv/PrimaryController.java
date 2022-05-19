package com.group4.rvv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import static com.group4.rvv.Borough.BRONX;
import static com.group4.rvv.Borough.BROOKLYN;
import static com.group4.rvv.Borough.MANHATTAN;
import static com.group4.rvv.Borough.QUEENS;
import static com.group4.rvv.Borough.STATEN_ISLAND;
import java.lang.Enum;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private TextField textField, tf2, tf3;
    @FXML
    private Text textResults;
    @FXML
    private Button gButton, rButton, zButton, bButton;
    @FXML
    private TextArea textArea;

    ApiTestProgram api = new ApiTestProgram();
    QueryBuilder qb = new APIQueryBuilder();
    DataFetcher df = new APIDataFetcher();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    StringBuilder sb = new StringBuilder();
    @FXML 
    private static ChoiceBox<String> choiceB;
    
    ObservableList list = FXCollections.observableArrayList();
    
    /**
     * based on grades.
     *
     * @throws IOException
     */
    @FXML
    private void onButtonPress() throws IOException {
        String res = textField.getText();
        
        String response = tf2.getText(); // Response should have textField param
        if (response.equalsIgnoreCase("A")) {
            qb.setDba(res);
            qb.setGrade(Grade.A);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (response.contains("B")) {
            qb.setDba(res);
            qb.setGrade(Grade.B);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (response.equalsIgnoreCase("C")) {
            qb.setDba(res);
            qb.setGrade(Grade.C);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            } 

        }
        //textResults.setText(api.getLatestGradeDataFromName(response).toString());
        //textArea.setText(api.getLatestGradeDataFromName(response).toString());
        //JsonElement je = JsonParser.parseString(api.getLatestGradeDataFromName(response).toString());
        //String result = gson.toJson(je);
        //textArea.setText(result);

        //restaurantDetails();
    }

    @FXML
    private void onTFClick() throws IOException {
        gButton.setDisable(false);
        zButton.setDisable(false);
        rButton.setDisable(false);
        //bButton.setDisable(false);
    }

    @FXML
    private void onRestNameGo() {
        String response = textField.getText();
        qb.setDba(response);
        Query q = qb.getQuery();
        System.out.println(q);

        DataFetcher df = new APIDataFetcher();
        HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
        for (Restaurant rest : restaurants.values()) {
            // peek at all the biographical fields
            String s1 = ("\n"
                    + "CAMIS: " + rest.getCAMIS() + "\n"
                    + "DBA: " + rest.getDBA() + "\n"
                    + "Borough: " + rest.getBorough() + "\n"
                    + "Building: " + rest.getBuilding() + "\n"
                    + "Street: " + rest.getStreet() + "\n"
                    + "Zip Code: " + rest.getZipCode() + "\n"
                    + "Phone: " + rest.getPhone() + "\n"
                    + "Food Type: " + rest.getFoodType() + "\n"
                    + "Lat: " + rest.getLatitude() + "\n"
                    + "Long: " + rest.getLongitude() + "\n"
                    + "Current grade: " + rest.getCurrentGrade());
            sb.append(s1);

            System.out.println("CAMIS: " + rest.getCAMIS());
            System.out.println("DBA: " + rest.getDBA());
            System.out.println("Borough: " + rest.getBorough());
            System.out.println("Building: " + rest.getBuilding());
            System.out.println("Street: " + rest.getStreet());
            System.out.println("Zip Code: " + rest.getZipCode());
            System.out.println("Phone: " + rest.getPhone());
            System.out.println("Food Type: " + rest.getFoodType());
            System.out.println("Lat: " + rest.getLatitude());
            System.out.println("Long: " + rest.getLongitude());
            System.out.println("Current grade: " + rest.getCurrentGrade());

            for (Inspection i : df.fetchInspections(rest).getInspections()) {
                // peek at all the inspection fields
                String s2 = ("\n"
                        + "\tInsp date: " + i.getDate() + "\n"
                        + "\tInsp type: " + i.getInspectionType() + "\n"
                        + "\tAction: " + i.getAction() + "\n"
                        + "\tGrade: " + i.getGrade() + "\n"
                        + "\tScore: " + i.getScore() + "\n");
                sb.append(s2);

                System.out.println("\tCAMIS: " + i.getCAMIS());
                System.out.println("\tInsp date: " + i.getDate());
                System.out.println("\tInsp type: " + i.getInspectionType());
                System.out.println("\tAction: " + i.getAction());
                System.out.println("\tGrade: " + i.getGrade());
                System.out.println("\tScore: " + i.getScore());

                for (String violCode : i.lookAtViolationCodes()) {
                    try {
                        String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                        System.out.println("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode));
                        //textArea.setText(s1 + s2 + s3);
                        sb.append(s3);
                    } catch (InvalidViolationException ex) {
                        Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //textArea.setText(s1 + s2);
                System.out.println();
            }
            //textArea.setText(restaurants.values().toString());
            System.out.println();
            //textArea.isWrapText();
            textArea.setText(sb.toString());
        }
    }

    // Searches based on zip
    @FXML
    private void searchZIP() {
        String r = textField.getText();
        qb.setZipCode(r);
        Query q = qb.getQuery();
        System.out.println(q);

        DataFetcher df = new APIDataFetcher();
        HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
        for (Restaurant rest : restaurants.values()) {
            // peek at all the biographical fields
            String s1 = ("\n"
                    + "CAMIS: " + rest.getCAMIS() + "\n"
                    + "DBA: " + rest.getDBA() + "\n"
                    + "Borough: " + rest.getBorough() + "\n"
                    + "Building: " + rest.getBuilding() + "\n"
                    + "Street: " + rest.getStreet() + "\n"
                    + "Zip Code: " + rest.getZipCode() + "\n"
                    + "Phone: " + rest.getPhone() + "\n"
                    + "Food Type: " + rest.getFoodType() + "\n"
                    + "Lat: " + rest.getLatitude() + "\n"
                    + "Long: " + rest.getLongitude() + "\n"
                    + "Current grade: " + rest.getCurrentGrade());
            sb.append(s1);

            for (Inspection i : df.fetchInspections(rest).getInspections()) {
                // peek at all the inspection fields
                String s2 = ("\n"
                        + "\tInsp date: " + i.getDate() + "\n"
                        + "\tInsp type: " + i.getInspectionType() + "\n"
                        + "\tAction: " + i.getAction() + "\n"
                        + "\tGrade: " + i.getGrade() + "\n"
                        + "\tScore: " + i.getScore() + "\n");
                sb.append(s2);

                for (String violCode : i.lookAtViolationCodes()) {
                    try {
                        String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                        sb.append(s3);
                    } catch (InvalidViolationException ex) {
                        Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //textArea.setText(s1 + s2);
                System.out.println();
            }
            //textArea.setText(restaurants.values().toString());
            System.out.println();
            //textArea.isWrapText();
            textArea.setText(sb.toString());
        }
    }

    /**
     * Searches based on boro criteria
     */
    @FXML
    private void BoroSearch() {
        String r = textField.getText();
        if (r.equalsIgnoreCase("Brooklyn")) {
            qb.setBorough(BROOKLYN);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (r.equalsIgnoreCase("Queens")) {
            qb.setBorough(QUEENS);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (r.equalsIgnoreCase("Bronx")) {
            qb.setBorough(BRONX);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (r.equalsIgnoreCase("Staten Island")) {
            qb.setBorough(STATEN_ISLAND);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        } else if (r.equalsIgnoreCase("Manhattan")) {
            qb.setBorough(MANHATTAN);
            Query q = qb.getQuery();
            System.out.println(q);

            DataFetcher df = new APIDataFetcher();
            HashMap<Integer, Restaurant> restaurants = df.fetchRestaurants(q);
            for (Restaurant rest : restaurants.values()) {
                // peek at all the biographical fields
                String s1 = ("\n"
                        + "CAMIS: " + rest.getCAMIS() + "\n"
                        + "DBA: " + rest.getDBA() + "\n"
                        + "Borough: " + rest.getBorough() + "\n"
                        + "Building: " + rest.getBuilding() + "\n"
                        + "Street: " + rest.getStreet() + "\n"
                        + "Zip Code: " + rest.getZipCode() + "\n"
                        + "Phone: " + rest.getPhone() + "\n"
                        + "Food Type: " + rest.getFoodType() + "\n"
                        + "Lat: " + rest.getLatitude() + "\n"
                        + "Long: " + rest.getLongitude() + "\n"
                        + "Current grade: " + rest.getCurrentGrade());
                sb.append(s1);

                for (Inspection i : df.fetchInspections(rest).getInspections()) {
                    // peek at all the inspection fields
                    String s2 = ("\n"
                            + "\tInsp date: " + i.getDate() + "\n"
                            + "\tInsp type: " + i.getInspectionType() + "\n"
                            + "\tAction: " + i.getAction() + "\n"
                            + "\tGrade: " + i.getGrade() + "\n"
                            + "\tScore: " + i.getScore() + "\n");
                    sb.append(s2);

                    for (String violCode : i.lookAtViolationCodes()) {
                        try {
                            String s3 = ("\t\tViolation: " + violCode + "; " + ViolationTable.lookup(violCode) + "\n");
                            sb.append(s3);
                        } catch (InvalidViolationException ex) {
                            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //textArea.setText(s1 + s2);
                    System.out.println();
                }
                //textArea.setText(restaurants.values().toString());
                System.out.println();
                //textArea.isWrapText();
                textArea.setText(sb.toString());
            }
        }
    } 
}
