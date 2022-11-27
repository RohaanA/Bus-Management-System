package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import businesslogic.Account;
import businesslogic.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SearchTicketController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account loggedIn = null;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> fare;
    @FXML
    private TableColumn<?, ?> routeID;
    @FXML
    private TableColumn<?, ?> routeStatus;
    @FXML
    private TableView<?> tableBus;
    @FXML
    private TableColumn<?, ?> time;
    @FXML
    private ChoiceBox<String> toLoc;
    @FXML
    private ChoiceBox<String> fromLoc;
	
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
    /*
     * Acts as the constructor of the controller. (Used for loading data/passing account instance)
     */
	public void setAccountInstance(Account acc) {
		if (acc == null)
			System.out.println("[SearchTicketController]: Account is null. ");
		loggedIn = acc;
		
		//Initialize routes
		initializeRoutes();
		
	}
	
	private void initializeRoutes() {
		Route routes = new Route();
		try {
			ArrayList<String> allRoutes = routes.getAllRoutes();
			//Populate the checkboxes.
			for(int i=0; i<allRoutes.size(); i++) {
				fromLoc.getItems().add(allRoutes.get(i));
				toLoc.getItems().add(allRoutes.get(i));
			}
			
		} catch (SQLException e) {e.printStackTrace();}
	}
}
