package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

	private Stage stage;
	private Scene scene;
	
    @FXML
    void registerCustomer(ActionEvent event) {
    	//TODO: Call to account to register.
    }
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }

}
