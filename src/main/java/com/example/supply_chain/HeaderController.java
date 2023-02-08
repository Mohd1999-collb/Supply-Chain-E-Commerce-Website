package com.example.supply_chain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class HeaderController {
    @FXML
    Button loginButton;

    @FXML
    Label email;

    @FXML
    Button logoutButton;

    @FXML
    public void initialize(){
        if (!HelloApplication.emailId.equals("")) {
            loginButton.setOpacity(0);
            email.setText(HelloApplication.emailId);
        }
    }
    @FXML
    public void login(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        AnchorPane loginPage = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("loginPage.fxml"))));
        HelloApplication.root.getChildren().add(loginPage);
    }

    @FXML
    /* Here we search the product by name */
    TextField searchText;
    public void search(MouseEvent mouseEvent) throws SQLException, IOException {
        Header header = new Header();
        ProductPage products = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(products.searchProductsbyName(searchText.getText()));
        productPane.setLayoutX(125);
        productPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root, productPane);
    }

    /* If we click the logout button then we log out from the page*/
    public void logout(MouseEvent mouseEvent) {
        if(logoutButton.getOpacity() == 0) {
            logoutButton.setOpacity(1);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailId = "";
                    try {
                        Header header = new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            logoutButton.setOpacity(0);
        }
    }
}
