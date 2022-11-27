package controllers;

import java.io.IOException;

import businesslogic.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerDashboardController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account loggedIn = null;

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
	//Go Back to This Page once Logged out
	public void Logout(ActionEvent event) throws IOException {
 		loggedIn = null; //Clear account instance
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void setAccountInstance(Account acc) {
			if (acc == null)
				System.out.println("[CustomerDashboardController]: Account is null. ");
		loggedIn = acc;
	}
	public void switchToSearch(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/SearchTicket.fxml"));
		root = loader.load();
		SearchTicketController searchTicketController = loader.getController();
		searchTicketController.start(loggedIn);

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Search Ticket");
		stage.show();
		
	}
}
