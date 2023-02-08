package com.example.supply_chain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class Header {
    public Pane root;
    Header() throws IOException {
        root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("header.fxml"))));
    }
}
