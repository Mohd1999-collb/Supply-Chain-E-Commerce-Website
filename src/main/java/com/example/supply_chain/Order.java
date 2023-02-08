package com.example.supply_chain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    /* Here we place the order into the order table*/
    public void placeOrder(String productId, String productName) throws SQLException {
        ResultSet result = HelloApplication.connection.executeQuery("select max(orderId) from orders");
        int orderId = 0;
        if(result.next()){
            orderId = result.getInt("max(orderId)") + 1;
            String query = String.format("insert into orders values('%s', '%s', '%s', '%s')", orderId, productName, productId, HelloApplication.emailId);
            int response = HelloApplication.connection.executeInsert(query);
            if(response != 0){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Order!");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Your Order is placed.");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
                System.out.println("Order is placed.");
            }
        }
    }
}
