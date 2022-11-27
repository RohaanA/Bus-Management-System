package controllers;

import java.awt.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import businesslogic.Account;
import businesslogic.Booking;
import businesslogic.Route;
import businesslogic.RouteDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchTicketController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account loggedIn = null;
	private Route routes = null;
	private ObservableList<RouteDescription> data = null;
    @FXML
    private TableColumn<RouteDescription, String> date;
    @FXML
    private TableColumn<RouteDescription, Integer> fare;
    @FXML
    private TableColumn<RouteDescription, Integer> routeID;
    @FXML
    private TableView<RouteDescription> routesTable;
    @FXML
    private TableColumn<RouteDescription, String> time;
    @FXML
    private ChoiceBox<String> toLoc;
    @FXML
    private ChoiceBox<String> fromLoc;
    @FXML
    private Label errorLabel;
	@FXML
	private Button searchButton;
	@FXML
	private TextField selectedRouteID;
	
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }
    
    @FXML
    private void setHome(ActionEvent event) throws IOException {
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
    private void switchToEditProfile(ActionEvent event) throws IOException {
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
     * Activated when user clicks search button.
     */
    @FXML
    private void search(ActionEvent event) {
    	String str_fromLoc = fromLoc.getValue();
    	String str_toLoc = toLoc.getValue();
//    	/* Guard Clauses */
    	if (str_fromLoc == null || str_toLoc == null) {
    		setErrorLabel("Please choose starting and ending location");
    		return;
    	}
    	if (str_fromLoc.equals(str_toLoc)) {
    		setErrorLabel("Starting and ending location are same.");
    		return;
    	}
    	
    	//Search for route.
    	//route.searchRoutes(str_fromLoc, str_toLoc);

		routeID.setCellValueFactory(new PropertyValueFactory<RouteDescription, Integer>("routeID"));
		date.setCellValueFactory(new PropertyValueFactory<RouteDescription, String>("deptDate"));
		time.setCellValueFactory(new PropertyValueFactory<RouteDescription, String>("deptTime"));
		fare.setCellValueFactory(new PropertyValueFactory<RouteDescription, Integer>("Fare"));
    
    
		data = routes.getRouteData(str_fromLoc, str_toLoc);
		routesTable.setItems(data);
    }

    /*
     * Activated when user clicks book ticket button.
     */
    @FXML
    void bookTicket(ActionEvent event) {
    	/* Guard Clauses */
    	String txtSelectedRouteID = selectedRouteID.getText(); 
    	if (data == null) 
    		return;
    	else if (txtSelectedRouteID.isEmpty() || txtSelectedRouteID.isBlank()) {
    		setErrorLabel("No Route Selected!");
    		return;
    	}
    	// Check if selected route ID is within that of showed.
    	RouteDescription selectedRoute = null;
    	int intSelectedRouteID = Integer.parseInt(txtSelectedRouteID);
    	for(int i=0; i<data.size(); i++) {
    		if (data.get(i).getRouteID() == intSelectedRouteID)
    			selectedRoute = data.get(i);
    	}		
    	if (selectedRoute == null) {
    		setErrorLabel("Please select route from table.");
    		return;
    	}
    	
//    	boolean status = routes.bookRoute(selectedRoute);
    	
    }
    /*
     * Acts as the constructor of the controller. (Used for loading data/passing account instance)
     */
	public void start(Account acc) {
		if (acc == null)
			System.out.println("[SearchTicketController]: Account is null. ");
		loggedIn = acc;
		
		//Hide error label
		errorLabel.setVisible(false);
		//Initialize routes
		initializeRoutes();
		
	}
	
	private void initializeRoutes() {
		routes = new Route();
		try {
			ArrayList<String> allRouteLocations = routes.getAllRouteLocations();
			//Populate the checkboxes.
			for(int i=0; i<allRouteLocations.size(); i++) {
				fromLoc.getItems().add(allRouteLocations.get(i));
				toLoc.getItems().add(allRouteLocations.get(i));
			}
			
		} catch (SQLException e) {e.printStackTrace();}
	}    
    private void setErrorLabel(String err) {
    	errorLabel.setText("Error: " + err);
    	errorLabel.setVisible(true);
    }
}
