package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import application.Classes.BusDescription;
import businesslogic.BookingDescription;
import db.PersistenceFactory;
import db.PersistenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class Manager_BookingController {
	
	 
		private Stage stage;
		private Scene scene;
		private PersistenceHandler mysql;		
		String currentUser="";

	    @FXML
	    private TableColumn<BookingDescription, String> username;

	    @FXML
	    private TableColumn<BookingDescription, Integer> bookingID;
	    
	    @FXML
	    private TableColumn<BookingDescription, String> bookingStatus;

	    @FXML
	    private TableColumn<BookingDescription, Integer> routeID;
	    
	    @FXML
	    private TableColumn<BookingDescription, String> paymentStatus;
	    
	    @FXML
	    private TableColumn<BookingDescription, Integer> seatNumber;
	    
	    @FXML
	    private TableView<BookingDescription> tableBooking;
	    
	    
	    @FXML
	    private TextField SearchBar;

	   

	    @FXML
	    private ChoiceBox<String> filterChoice;

	    @FXML
	    private Label nameHolder;

	   

	    @FXML
	    private TextField selectedID;

	   
	    
	    public void initialize() {
	    	filterChoice.getItems().removeAll(filterChoice.getItems());
	    	filterChoice.getItems().addAll("Route ID","Booking ID");
	    	filterChoice.getSelectionModel().select("Route ID");
		}

	   
			//Go Back to This Page once Logged out
		public void Logout(ActionEvent event) throws IOException {
	 		
			Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	
	    public void switchToManagerView(ActionEvent event) throws IOException {
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../application/ManagerView.fxml"));	
			Parent root=loader.load();
			ManagerHome_Controller controller=loader.getController();
			
			
			controller.Display_User(currentUser);
			
			//Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Manager View");
			stage.show();
			
		}
		
		
		
		//Manage Buses Scene
		public void switchToManageBuses(ActionEvent event) throws IOException {
			
			System.out.print("Switching to Bus Manager Bus...");
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../application/ManageBuses.fxml"));	
			//Parent root = FXMLLoader.load(getClass().getResource("../application/ManageBuses.fxml"));	
			
			Parent root=loader.load();
			Manager_BusController controller=loader.getController();
			
			
			controller.Display_User(currentUser);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
			scene= new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Manage Buses ("+currentUser+")");
			stage.show();
				
		}

		//Filter and Search
		public void filterSearch(ActionEvent event) {
			
			try
			{
				
				mysql=PersistenceFactory.getDBInstance("MySQL");
				ResultSet rs=mysql.displayBooking(Integer.parseInt((SearchBar.getText())),filterChoice.getValue());
				
				System.out.println("Finding...");
				ObservableList<BookingDescription> data = FXCollections.observableArrayList();
				
				bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
				routeID.setCellValueFactory(new PropertyValueFactory<>("routeID"));
				username.setCellValueFactory(new PropertyValueFactory<>("username"));
				bookingStatus.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
				paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
				seatNumber.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
				
				
				
				while(rs.next()){
	                //Iterate Row
					
					data.add(new BookingDescription(
							rs.getInt("bookingID"),
							rs.getInt("routeID"),
							rs.getInt("seatNumber"),
							rs.getString("username"),
							
							rs.getString("bookingStatus"),
							rs.getString("paymentStatus")
							
	
							
							));

	            }
				
				tableBooking.setItems(data);
				
				//tableRowClickListener();
				
				rs.close();
				

				
			}
			catch(Exception e)
			{
				System.out.println("Error in Viewing booking");
				e.printStackTrace();
			
			}
			
			
			
		}
			
		
		public void viewAllBookings(ActionEvent event) 
		{
			try
			{
				mysql=PersistenceFactory.getDBInstance("MySQL");
				ResultSet rs=mysql.displayAllBooking();
				
				ObservableList<BookingDescription> data = FXCollections.observableArrayList();
				
				bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
				routeID.setCellValueFactory(new PropertyValueFactory<>("routeID"));
				username.setCellValueFactory(new PropertyValueFactory<>("username"));
				bookingStatus.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
				paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
				seatNumber.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
				
				
				
				while(rs.next()){
	                //Iterate Row
					
					
					data.add(new BookingDescription(
							rs.getInt("bookingID"),
							rs.getInt("routeID"),
							rs.getInt("seatNumber"),
							rs.getString("username"),
							
							rs.getString("bookingStatus"),
							rs.getString("paymentStatus")
							
	
							
							));

	            }
				
				
				tableBooking.setItems(data);
				
				//tableRowClickListener();
				
				rs.close();
				
			}
			catch(Exception e)
			{
				System.out.println("Error in Viewing All Bookings");
				e.printStackTrace();
			
			}
			
		}
		
		public void cancelAllBookings(ActionEvent event) {
			
			int route_ID=Integer.parseInt(selectedID.getText());
			mysql=PersistenceFactory.getDBInstance("MySQL");
			
			if(mysql.cancelAllBookings(route_ID))
			{
				viewAllBookings(event); 
				System.out.print("Cancelled Successfully");
				
			}
			else
			{
				System.out.print("Unable to Cancel");
			}
			
			
			
		}
		
		public void changeBookingStatus(ActionEvent event)
		{

			int route_ID=Integer.parseInt(selectedID.getText());
			mysql=PersistenceFactory.getDBInstance("MySQL");
			
			if(mysql.changeBookingStatus(route_ID))
			{
				viewAllBookings(event); 
				System.out.println("changed Successfully");
				
			}
			else
			{
				System.out.println("Unable to change");
			}
			
		}
	
	    void Display_User(String Name)
		{
			
			currentUser=Name;		
			nameHolder.setText(currentUser);
			
		}

	
	
	

}
