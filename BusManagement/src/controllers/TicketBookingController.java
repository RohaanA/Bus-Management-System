package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Classes.SeatDescription;
import businesslogic.Account;
import businesslogic.Booking;
import businesslogic.BookingDescription;
import businesslogic.Bus;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TicketBookingController {

	private Account loggedIn = null;
	private RouteDescription routeTaken = null;
	private Route allRoutes = null;
	ArrayList<String> allSeatArr = null;
	
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<SeatDescription> seatsTable;
    @FXML
    private TableColumn<SeatDescription, Integer> seatNumber;
    @FXML
    private TableColumn<SeatDescription, String> seatStatus;
    @FXML
    private TextField selectedSeatNumber;
    @FXML
    private TextField cardNumber;
    @FXML
    private DatePicker cardValidity;
    @FXML
    private TextField cvv;
    @FXML
    private TextField nameOnCard;
    Bus routeBus = null;
    private ArrayList<Integer> availableSeats = null;
    private Stage stage;
    private Scene scene;
    
    @FXML
    void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }

    @FXML
    void purchaseTicket(ActionEvent event) {
    	String txt_selectedSeatNumber = selectedSeatNumber.getText();
    	String txt_nameOnCard = nameOnCard.getText();
    	String txt_cardNumber = cardNumber.getText();
    	String txt_cvv = cvv.getText();
    	
    	/* Guard Clauses */
    	if (txt_selectedSeatNumber.isEmpty() || txt_selectedSeatNumber.isBlank()) {
        	setErrorLabel("Must select seat number");
    		return;	
    	}
    	else if (txt_nameOnCard.isEmpty() || txt_nameOnCard.isBlank() ||
    			 txt_nameOnCard.isEmpty() || txt_nameOnCard.isBlank() ||
    			 txt_cardNumber.isEmpty() || txt_cardNumber.isBlank() ||
    			 cardValidity.getValue() == null) {
        	setErrorLabel("Missing Card Fields");
    		return;	
    	}
    	String status = allSeatArr.get(Integer.parseInt(txt_selectedSeatNumber)-1);
    	System.out.println(status);
    	if (status.equals("booked")) {
    		setErrorLabel("Seat is not available!");
    		return;
    	}
    	/* Guard Clauses End */
    	String txt_cardValidity = cardValidity.getValue().toString();    	
    	
    	Booking booking = new Booking();
    	boolean bookingStatus = booking.saveBooking(new BookingDescription(-1, routeTaken.getRouteID(), Integer.parseInt(txt_selectedSeatNumber), loggedIn.getUsername(), "ongoing", "paid"));
    	if (bookingStatus)
    		System.out.println("Succesfully booked into database.");
    	else System.out.println("Error occured while booking.");
    }
    
    public void start(Account acc, RouteDescription route, Route allRoutes) {
    	loggedIn = acc;
    	routeTaken = route;
    	routeBus = new Bus();
    	this.allRoutes = allRoutes;
    	errorLabel.setVisible(false);
    	allSeatArr = new ArrayList<String>();
    	
    	initializeSeatTable();
    }

	private void initializeSeatTable() {
		//First get no. of seats on the given route.
		try {
//			System.out.println(routeTaken.getBusID());
			int seatCount = routeBus.getSeatsFromBusID(routeTaken.getBusID());
			
			ArrayList<Integer> seatsBooked = allRoutes.getBookedSeats(routeTaken.getRouteID());
			
			for(int i=0; i<seatsBooked.size(); i++)
				System.out.println(seatsBooked.get(i));
			
			//Populate the table
			seatNumber.setCellValueFactory(new PropertyValueFactory<SeatDescription, Integer>("seatNumber"));
			seatStatus.setCellValueFactory(new PropertyValueFactory<SeatDescription, String>("status"));
			
			ObservableList<SeatDescription> allSeatStatus = FXCollections.observableArrayList();
			
			
			for(int i=0; i<seatCount; i++) {
					if (seatsBooked.contains(i+1)) {
						allSeatStatus.add(new SeatDescription(i+1, "booked"));
						allSeatArr.add("booked");
					}
					else { 
						allSeatStatus.add(new SeatDescription(i+1, "available"));
						allSeatArr.add("available");
					}
			}
			seatsTable.setItems(allSeatStatus);
			
			
			
			
} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private void setErrorLabel(String err) {
    	errorLabel.setText("Error: " + err);
    	errorLabel.setVisible(true);
    }

}
