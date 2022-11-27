package controllers;

import java.io.IOException;

import businesslogic.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchTicketController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account loggedIn = null;
	
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }
    
    @FXML
    public void setHome(ActionEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerDashboard.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerDashboard.fxml"));
		root = loader.load();
		CustomerDashboardController customerDashboardController = loader.getController();
		customerDashboardController.setAccountInstance(loggedIn);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customer Dashboard");
		stage.show();
	}
    @FXML
    public void switchToEditProfile(ActionEvent event) throws IOException {
		//Pass account instance.
//		Parent root = FXMLLoader.load(getClass().getResource("../application/EditProfile.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/EditProfile.fxml"));
		root = loader.load();
		EditProfileController editProfileController = loader.getController();
		editProfileController.setAccountInstance(loggedIn);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Profile");
		stage.show();
	}

	public void setAccountInstance(Account acc) {
		if (acc == null)
			System.out.println("[SearchTicketController]: Account is null. ");
		loggedIn = acc;
		
	}
}
