module com.example.supply_chain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supply_chain to javafx.fxml;
    exports com.example.supply_chain;
}