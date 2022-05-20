package com.group4.rvv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private Text textResults;

    @FXML
    private TextArea textArea;

    
    QueryBuilder qb = new APIQueryBuilder();
    DataFetcher df = new APIDataFetcher();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    StringBuilder sb = new StringBuilder();

    ObservableList list = FXCollections.observableArrayList();

    // Start of alternate input option
    @FXML
    TextField textRestaurantName, textBuilding, textStreet, textZip, textBoro;
    @FXML
    Button buttonSearch;

    @FXML
    ChoiceBox choiceBoro;

    @FXML
    ChoiceBox choiceGrade;

    @FXML
    ChoiceBox choiceCuisine;

    @FXML
    MenuItem menuExit;

    @FXML
    MenuItem menuAbout;

    public void initialize() {
        ObservableList<Borough> boroughs = FXCollections.observableArrayList(Borough.values());
        choiceBoro.getItems().add(null);
        choiceBoro.getItems().addAll(boroughs);

        ObservableList<Grade> grades = FXCollections.observableArrayList(Grade.values());
        choiceGrade.getItems().add(null);
        choiceGrade.getItems().addAll(grades);

        ObservableList<String> cuisines
                = FXCollections.observableArrayList("African", "American", "Asian/Asian Fusion",
                        "Bagels/Pretzels", "Bakery Products/Desserts", "Bangladeshi", "Barbecue",
                        "Bottled Beverages", "Brazilian", "Caribbean", "Chicken", "Chinese",
                        "Chinese/Japanese", "Coffee/Tea", "Creole", "Donuts", "Filipino",
                        "French", "Frozen Desserts", "Greek", "Hamburgers", "Hawaiian",
                        "Indian", "Irish", "Italian", "Japanese", "Jewish/Kosher",
                        "Juice, Smoothies, Fruit Salads", "Korean",
                        "Latin American", "Mediterranean", "Mexican",
                        "Middle Eastern", "Pancakes/Waffles", "Pizza", "Russian",
                        "Sandwiches", "Spanish", "Soul Food", "Southeast Asian",
                        "Steakhouse", "Tex-Mex", "Thai", "Turkish", "Vegan",
                        "Vegetarian");
        choiceCuisine.getItems().add(null);
        choiceCuisine.getItems().addAll(cuisines);

    }

    @FXML
    private void searchOnClick() {
        textArea.clear();
        sb = new StringBuilder();
        QueryBuilder qb = new APIQueryBuilder();
        DataFetcher df = new APIDataFetcher();

        qb.setDba(!textRestaurantName.getText().isBlank() ? textRestaurantName.getText() : null);
        qb.setStreet(!textStreet.getText().isBlank() ? textStreet.getText() : null);
        qb.setBuilding(!textBuilding.getText().isBlank() ? textBuilding.getText() : null);
        qb.setZipCode(!textZip.getText().isBlank() ? textZip.getText() : null);
        qb.setBorough(choiceBoro.getValue() != null ? (Borough) choiceBoro.getValue() : null);
        qb.setFoodType(choiceCuisine.getValue() != null ? choiceCuisine.getValue().toString() : null);
        Query q = qb.getQuery();
        System.out.println(q);

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
                        Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //textArea.setText(s1 + s2);
                System.out.println();
            }
            //textArea.setText(restaurants.values().toString());
            System.out.println();
            //textArea.isWrapText();

        }
        textArea.setText(sb.toString());
    }

    @FXML
    private void menuExitOnClick() {
        Platform.exit();
    }

    @FXML
    private void menuAboutOnClick() {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        dialog.setContentText("Created by: \n"
                + "\n\t Jesus A. B."
                + "\n\t Natalie D."
                + "\n\t Jonathan E."
                + "\n\t Omar M."
                + "\n\t Muhammad Z."
                + "\n\n Farmingdale State College - CSC 325 - Spring 2022");
        dialog.showAndWait();
    }
}
