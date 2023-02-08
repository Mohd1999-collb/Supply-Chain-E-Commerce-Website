package com.example.supply_chain;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailId;
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        emailId = "";
        connection = new DatabaseConnection();
        ResultSet result = connection.executeQuery("Select * from product");

        /* Iterate the ResultSet similar to Hashset*/
        while(result.next()){
            System.out.println(result.getString("productName"));
            System.out.println(result.getString("productPrice"));
        }

//        int result2 = connection.executeInsert("insert into product values(4, 'IPhone', 80000, \"shubham4567@gmail.com\")");
//        if(result2 != 0){
//            System.out.println("A new row(data) is inserted");
//        }

        root = new Group();
        Header header = new Header();

        ProductPage products = new ProductPage();
        ListView<HBox> productList = products.showProducts();

        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(125);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(header.root, productPane); // Add the header children of anchor pane
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Supply Chain E-Commerce!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                connection.con.close();
                System.out.println("DATABASE CONNECTION IS CLOSED!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}