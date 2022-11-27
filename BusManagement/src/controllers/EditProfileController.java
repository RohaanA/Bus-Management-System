package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import businesslogic.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
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
    private DatePicker newDOB;

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
		//Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerDashboard.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerDashboard.fxml"));
		root = loader.load();
		CustomerDashboardController customerDashboardController = loader.getController();
		customerDashboardController .setAccountInstance(loggedIn);
		
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customer Dashboard");
		stage.show();
	}
    
    @FXML
    void saveCustomerDetails(ActionEvent event) {
    	//Validation Checks
    	String txt_newPhone = newPhone.getText();
    	String txt_newCNICPartOne = newCNICPartOne.getText();
    	String txt_newCNICPartTwo = newCNICPartTwo.getText();
    	String txt_newCNICPartThree = newCNICPartThree.getText();
    	String txt_newDob = "";
    	if (newDOB.getValue() != null)
    		txt_newDob = newDOB.getValue().toString();
    	String txt_newAddress = newAddress.getText();
    	
    	//Guard Clause: Atleast one field has data.
    	if (txt_newPhone.isEmpty() && txt_newCNICPartOne.isEmpty() && txt_newCNICPartTwo.isEmpty() && txt_newCNICPartThree.isEmpty() && txt_newDob.isEmpty() && txt_newAddress.isEmpty()) {
    		setErrorLabel("No new data!");
    		return;
    	}
    	//Guard clause: Check for CNIC
    	if (!txt_newCNICPartOne.isEmpty() || !txt_newCNICPartTwo.isEmpty() || !txt_newCNICPartThree.isEmpty()) {
	    	if (txt_newCNICPartOne.length() != 5 || txt_newCNICPartTwo.length() != 7 || txt_newCNICPartThree.length() != 1) {
	    		setErrorLabel("Invalid CNIC!");
	    		return;
	    	}
    	}
    	String finalCNIC = txt_newCNICPartOne + "-" + txt_newCNICPartTwo + "-" + txt_newCNICPartThree;
    	
    	//Storing details and sending to customer.
    	HashMap<String, String> details = new HashMap<String, String>();
    	if (txt_newPhone.isEmpty() || txt_newPhone.isBlank())
    		details.put("phone", loggedIn.getPhone());
    	else details.put("phone", txt_newPhone);

    	if (txt_newDob.isEmpty() || txt_newDob.isBlank())
    		details.put("dob", loggedIn.getDOB());
    	else details.put("dob", txt_newDob);

    	if (txt_newAddress.isEmpty() || txt_newAddress.isBlank())
    		details.put("address", loggedIn.getAddress());
    	else details.put("address", txt_newAddress);

    	if (finalCNIC.equals("--"))
    		details.put("cnic", loggedIn.getCNIC());
    	else details.put("cnic", finalCNIC);

    	boolean status = loggedIn.save(loggedIn.getUsername(), "customer", details);
    	if (status) {
    		successLabel.setVisible(true);
    	}
    	else setErrorLabel("Update failed");
    }

    public void setAccountInstance(Account acc) {
		if (acc == null)
			System.out.println("[EditProfileController]: Account is null. ");
    	loggedIn = acc;
    	loadData();
    }
    
    public void switchToSearch(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/SearchTicket.fxml"));
		root = loader.load();
		SearchTicketController searchTicketController = loader.getController();
		searchTicketController .setAccountInstance(loggedIn);

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Profile");
		stage.show();
    }
    
    /*
     * Loads data from account instance (on view load)
     */
    private void loadData() {
    	oldCNIC.setText(oldCNIC.getText() + " " + loggedIn.getCNIC());
    	oldDOB.setText(oldDOB.getText() + " " + loggedIn.getDOB());
    	oldPhone.setText(oldPhone.getText() + " " + loggedIn.getPhone());
    	oldAddress.setText(oldAddress.getText() + " " + loggedIn.getAddress());
    	
    	errorLabel.setVisible(false);
    	successLabel.setVisible(false);
    }
    private void setErrorLabel(String err) {
    	errorLabel.setText("Error: " + err);
    	errorLabel.setVisible(true);
    }
    
    
}
