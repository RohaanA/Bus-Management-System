package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerRegisterController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField newAddress;

    @FXML
    private TextField newCNICPartOne;

    @FXML
    private TextField newCNICPartThree;

    @FXML
    private TextField newCNICPartTwo;

    @FXML
    private TextField newDOB;

    @FXML
    private TextField newPhone;

    @FXML
    private TextField newPhone2;

    @FXML
    private Label successLabel;

    @FXML
    void registerCustomer(ActionEvent event) {

    }
    @FXML
    void switchToLogin(ActionEvent event) {
    	
    }

}
