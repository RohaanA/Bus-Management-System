package controllers;

import java.io.IOException;
import java.sql.SQLException;

import businesslogic.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerRegisterController {
	@FXML
    private TextField cnicPartOne;

    @FXML
    private TextField cnicPartThree;

    @FXML
    private TextField cnicPartTwo;

    @FXML
    private TextField address;

    @FXML
    private DatePicker dob;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private Label successLabel;

    @FXML
    private TextField username;


	private Stage stage;
	private Scene scene;
	
//	public CustomerRegisterController() {
//		//Hide the error/success messages
//		successLabel.setVisible(false);
//		errorLabel.setVisible(false);
//	}
	
    @FXML
    void registerCustomer(ActionEvent event) throws SQLException {
    	//Guard Clause: Check for empty fields.
    	String txt_username = username.getText();
    	String txt_password = password.getText();
    	String txt_phone = phone.getText();
    	String txt_cnicPartOne = cnicPartOne.getText();
    	String txt_cnicPartTwo = cnicPartTwo.getText();
    	String txt_cnicPartThree = cnicPartThree.getText();
    	String txt_dob = dob.getValue().toString();
    	String txt_address = address.getText();
    	
    	if (txt_username.isEmpty() || txt_password.isEmpty() || txt_phone.isEmpty() || txt_cnicPartOne.isEmpty() || txt_cnicPartTwo.isEmpty() || txt_cnicPartThree.isEmpty() || txt_dob.isEmpty() || txt_address.isEmpty()) {
    		setErrorLabel("Must fill all fields!");
    		return;
    	}
    	//Guard clause: Check for CNIC
    	if (cnicPartOne.getLength() != 5 || cnicPartTwo.getLength() != 7 || cnicPartThree.getLength() != 1) {
    		setErrorLabel("Invalid CNIC!");
    		return;
    	}
    	

    	String finalCNIC = cnicPartOne + "-" + cnicPartTwo + "-" + cnicPartThree;
    	//TODO: Call to account to register.
    	Account acc = new Account();
    	boolean status = acc.registerCustomer(txt_username, txt_password, txt_phone, finalCNIC, txt_dob, txt_address);
    	if (status) {
    		errorLabel.setVisible(false);
    		successLabel.setVisible(true);
    		
    	}
    	else {
    		setErrorLabel("Failed to register account.");
    		return;
    	}
    }
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }
    
    private void setErrorLabel(String err) {
    	errorLabel.setText("Error: " + err);
    	errorLabel.setVisible(true);
    }

}
