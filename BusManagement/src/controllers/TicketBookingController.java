package controllers;

import java.sql.SQLException;

import businesslogic.Account;
import businesslogic.Bus;
import businesslogic.Route;
import businesslogic.RouteDescription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TicketBookingController {

	private Account loggedIn = null;
	private RouteDescription routeTaken = null;
	
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<String> seatsTable;
    @FXML
    private TableColumn<?, ?> seatNumber;
    @FXML
    private TableColumn<?, ?> seatStatus;
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

    
    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void purchaseTicket(ActionEvent event) {

    }
    
    public void start(Account acc, RouteDescription route) {
    	loggedIn = acc;
    	routeTaken = route;
    	routeBus = new Bus();
    	
    	initializeSeatTable();
    }

	private void initializeSeatTable() {
		//First get no. of seats on the given route.
		try {
			int seatCount = routeBus.getSeatsFromBusID(routeTaken.getBusID());
			System.out.println("Seats: " + seatCount);
} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
