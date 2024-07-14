package converter;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBinary;

    @FXML
    private Button btnDecimal;

    @FXML
    private Button btnHex;

    @FXML
    private MenuItem mniBinary;

    @FXML
    private MenuItem mniDecimal;

    @FXML
    private MenuItem mniHex;

    @FXML
    private MenuItem mniQuit;

    @FXML
    private TextField txtBinary;

    @FXML
    private TextField txtDecimal;

    @FXML
    private TextField txtHex;

    @FXML
    void btnBinaryOnAction(ActionEvent event) {
        convertBinary();
    }

    @FXML
    void btnDecimalOnAction(ActionEvent event) {
        convertDecimal();
    }

    @FXML
    void btnHexOnAction(ActionEvent event) {
        convertHex();
    }

    @FXML
    void mniBinaryOnAction(ActionEvent event) {
        convertBinary();
    }

    @FXML
    void mniDecimalOnAction(ActionEvent event) {
        convertDecimal();
    }

    @FXML
    void mniHexOnAction(ActionEvent event) {
        convertHex();
    }

    @FXML
    void mniQuitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void txtBinaryOnAction(ActionEvent event) {
        convertBinary();
    }

    @FXML
    void txtDecimalOnAction(ActionEvent event) {
        convertDecimal();
    }

    @FXML
    void txtHexOnAction(ActionEvent event) {
        convertHex();
    }

    @FXML
    void initialize() {
        assert btnBinary != null : "fx:id=\"btnBinary\" was not injected: check your FXML file 'view.fxml'.";
        assert btnDecimal != null : "fx:id=\"btnDecimal\" was not injected: check your FXML file 'view.fxml'.";
        assert btnHex != null : "fx:id=\"btnHex\" was not injected: check your FXML file 'view.fxml'.";
        assert mniBinary != null : "fx:id=\"mniBinary\" was not injected: check your FXML file 'view.fxml'.";
        assert mniDecimal != null : "fx:id=\"mniDecimal\" was not injected: check your FXML file 'view.fxml'.";
        assert mniHex != null : "fx:id=\"mniHex\" was not injected: check your FXML file 'view.fxml'.";
        assert mniQuit != null : "fx:id=\"mniQuit\" was not injected: check your FXML file 'view.fxml'.";
        assert txtBinary != null : "fx:id=\"txtBinary\" was not injected: check your FXML file 'view.fxml'.";
        assert txtDecimal != null : "fx:id=\"txtDecimal\" was not injected: check your FXML file 'view.fxml'.";
        assert txtHex != null : "fx:id=\"txtHex\" was not injected: check your FXML file 'view.fxml'.";

    }

    private void convertDecimal() {
        String text = txtDecimal.getText();
        int number = Integer.parseInt(text);

        txtHex.setText(Integer.toHexString(number).toUpperCase());
        txtBinary.setText(Integer.toBinaryString(number));
    }

    private void convertHex() {
        String text = txtHex.getText();
        int number = Integer.parseInt(text, 16);

        txtDecimal.setText(Integer.toString(number));
        txtBinary.setText(Integer.toBinaryString(number));
    }

    private void convertBinary() {
        String text = txtBinary.getText();
        int number = Integer.parseInt(text, 2);

        txtDecimal.setText(Integer.toString(number));
        txtHex.setText(Integer.toHexString(number).toUpperCase());
    }
}
