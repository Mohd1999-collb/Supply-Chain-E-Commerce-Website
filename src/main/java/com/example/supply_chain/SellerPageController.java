package com.example.supply_chain;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {

    @FXML
    TextField name;

    @FXML
    TextField price;

    @FXML
    TextField email;

    public void add(MouseEvent event) throws SQLException {

        /* Fetch the maximum productId from product table*/
        ResultSet result = HelloApplication.connection.executeQuery("Select max(productId) from product");
        int productId = 0;
        /* Select the maximum id and increment by 1*/
        if (result.next()) {
            productId = result.getInt("max(productId)") + 1;
        }

        String query = String.format("Insert Into product values('%s', '%s', '%s', '%s')", productId, name.getText(), price.getText(), email.getText());

        int response = HelloApplication.connection.executeInsert(query);
        System.out.println(response);

        /* If product is successfully added then dialog popup say that A New Product Is Added! */
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Product Add!");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (response != 0){
            dialog.setContentText("A New Product Is Added!");
        }else{
            dialog.setContentText("A New Product Is Not Added!");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
