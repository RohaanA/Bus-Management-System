package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import businesslogic.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account loggedIn = null;

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
    private Label oldAddress;

    @FXML
    private Label oldCNIC;

    @FXML
    private Label oldDOB;

    @FXML
    private Label oldPhone;

    @FXML
    private Label successLabel;

	//Go Back to This Page once Logged out
    @FXML
    public void Logout(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    public void setHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerDashboard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customer Dashboard");
		stage.show();
	}
    
    @FXML
    void saveCustomerDetails(ActionEvent event) {
    	//TODO: Call DB through customer.
    }

    public void setAccountInstance(Account acc) {
    	loggedIn = acc;
    	loadData();
    }
    
    /*
     * Loads data from account instance
     */
    private void loadData() {
    	oldCNIC.setText(oldCNIC.getText() + loggedIn.getCNIC());
    	oldDOB.setText(oldDOB.getText() + loggedIn.getDOB());
    	oldPhone.setText(oldPhone.getText() + loggedIn.getPhone());
    	oldAddress.setText(oldAddress.getText() + loggedIn.getAddress());
    }

}
