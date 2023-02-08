package com.example.supply_chain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application Created By Mohd Talib!");
        System.out.println("Hello is clicked");
    }
}