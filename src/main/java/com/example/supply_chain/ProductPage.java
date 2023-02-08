package com.example.supply_chain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;

    /* Show the searched  product table only*/
    ListView<HBox> searchProductsbyName(String search) throws SQLException {
        /* If any changes in the list then observableList is automatically change and see the undated listView*/
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet result = HelloApplication.connection.executeQuery("select * from product");
        products = new ListView<>();

        Label name = new Label();
        Label price = new Label();
        Label id = new Label();
        HBox details = new HBox();

        name.setMinWidth(75);
        id.setMinWidth(75);
        price.setMinWidth(75);

        name.setText("Name ");
        price.setText("Price ");
        id.setText("Product Id ");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while(result.next()) {
            if(result.getString("productName").toLowerCase().contains(search.toLowerCase())){
                Label productName = new Label();
                Label productPrice = new Label();
                Label productId = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();

                productName.setMinWidth(75);
                productId.setMinWidth(75);
                productPrice.setMinWidth(78);

                buy.setText("Buy");
                /* When we clicked the buy button then some action is performed*/
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login!");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login First Before Placing The Order.");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            try {
                                Order place = new Order();
                                place.placeOrder(productId.getText(), productName.getText());
                                System.out.println("You clicked on buy button");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                productName.setText(result.getString("productName"));
                productPrice.setText(result.getString("productPrice"));
                productId.setText("" + result.getString("productId"));

                productDetails.getChildren().addAll(productId, productName, productPrice, buy);
                productList.add(productDetails);

            }
        }
        /*If product list is empty then show the dialog box*/
        if (productList.size() == 1) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search Result!");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No Product Is Available For Your search.");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> showProducts() throws SQLException {
        /* If any changes in the list then observableList is automatically change and see the undated listView*/
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet result = HelloApplication.connection.executeQuery("select * from product");
        products = new ListView<>();

        Label name = new Label();
        Label price = new Label();
        Label id = new Label();
        HBox details = new HBox();

        name.setMinWidth(75);
        id.setMinWidth(75);
        price.setMinWidth(75);

        name.setText("Name ");
        price.setText("Price ");
        id.setText("Product Id ");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while(result.next()){
            Label productName = new Label();
            Label productPrice = new Label();
            Label productId = new Label();
            Button buy = new Button();
            HBox productDetails = new HBox();

            productName.setMinWidth(75);
            productId.setMinWidth(75);
            productPrice.setMinWidth(78);

            buy.setText("Buy");
            /* When we clicked the buy button then some action is performed*/
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    if(HelloApplication.emailId.equals("")){
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login!");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login First Before Placing The Order.");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }else {
                        try {
                            Order place = new Order();
                            place.placeOrder(productId.getText(), productName.getText());
                            System.out.println("You clicked on buy button");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            productName.setText(result.getString("productName"));
            productPrice.setText(result.getString("productPrice"));
            productId.setText("" + result.getString("productId"));

            productDetails.getChildren().addAll(productId, productName, productPrice, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
