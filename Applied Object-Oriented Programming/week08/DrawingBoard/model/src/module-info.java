module model {
    requires javafx.controls;
    requires javafx.fxml;
    opens geometry to javafx.fxml;
    exports geometry to javafx.graphics, gui;
}