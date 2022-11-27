package controllers;

import businesslogic.Account;
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

    
    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void purchaseTicket(ActionEvent event) {

    }
    
    public void start(Account acc, RouteDescription route) {
    	loggedIn = acc;
    	routeTaken = route;
    	
    	initializeSeatTable();
    }

	private void initializeSeatTable() {
		//TODO: Populate seats table.
	}

}
