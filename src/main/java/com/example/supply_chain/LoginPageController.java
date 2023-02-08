package com.example.supply_chain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {
    @FXML
    TextField email;

    @FXML
    PasswordField password;

    public void login(MouseEvent mouseEvent) throws SQLException, IOException {
//        System.out.println(email.getText());
//        System.out.println(password.getText());

        /* Match the login email and password from the database*/
        String query = String.format("select * from user " +
                "where emailId = '%s' AND userPassword = '%s'", email.getText(), password.getText());
        ResultSet result = HelloApplication.connection.executeQuery(query);

        /* Check the user type*/
        if(result.next()){
            String userType = result.getString("userType");
            HelloApplication.emailId = result.getString("emailId");

            /* When we log in as a Buyer then open the Buyer page*/
            if (userType.equals("Buyer")) {
                System.out.println("Logged In as Buyer!");
                ProductPage products = new ProductPage();

                Header header = new Header();

                ListView<HBox> productList = products.showProducts();

                AnchorPane productPane = new AnchorPane();
                productPane.setLayoutX(125);
                productPane.setLayoutY(100);

                productPane.getChildren().add(productList);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root, productPane);


            }
            /* When we log in as a Seller then open the seller page*/
            else{
                /* When we log in as a seller then open the seller page*/
                System.out.println("Logged In as Seller!");
                AnchorPane sellerPage = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("sellerPage.fxml"))));
                HelloApplication.root.getChildren().add(sellerPage);
            }
        }else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login!");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("login Failed! Please Try Again.");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
