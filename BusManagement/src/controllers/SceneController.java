package controllers;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Classes.BusDescription;
import businesslogic.Account;
import db.PersistenceFactory;
import db.PersistenceHandler;
import db.SQLPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * A Controller is added through Scene Builder options. 
 * Through this controls can access functions in Controller
 */

public class SceneController {

	@FXML
	TextField txt_username;
	@FXML
	PasswordField txt_password;
	@FXML 
	RadioButton login_type;
	@FXML
	TextField busSearchBar;
	@FXML
	Label nameHolder;
	
	@FXML
	Label userLabel;
    @FXML
    private Hyperlink registerLink;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	//private Parent root;
	private PersistenceHandler mysql;
	static String currentUser="";
	
	

	//JavaFx Bus Table Nodes
	@FXML
	TableView<BusDescription> tableBus;
	@FXML
	TableColumn<BusDescription,Integer> busID;
	@FXML
	TableColumn<BusDescription,String> model;
	@FXML
	TableColumn<BusDescription,String> year;
	@FXML
	TableColumn<BusDescription,Integer> seatCount;
	@FXML
	TableColumn<BusDescription,String> last_Maintenance;
	@FXML
	TableColumn<BusDescription,String> status;
	@FXML
	TableColumn<BusDescription,Float> cost;
	
	
	
	/*public void switchToLoginPage(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}*/
	
	
	public void switchToManagerView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Manager View");
		stage.show();
	}
	public void switchToCustomerView(ActionEvent event, Account acc) throws IOException {
		
//		Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerDashboard.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerDashboard.fxml"));
		root = loader.load();
		CustomerDashboardController custDashboardController = loader.getController();
		custDashboardController.setAccountInstance(acc);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customer View");
		stage.show();
	}
	
	public void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		
		String username = txt_username.getText();
		String password = txt_password.getText();
		// Pass on information to account class.
		Account acc = new Account();
		boolean status = false;
		
		
		if(login_type.isSelected())
		{
			status = acc.login(username, password, "Manager");
			if (status) {
				switchToManagerView(event);
			}
		}
		else
		{
			status = acc.login(username, password, "Customer");
			//Switch to customer view.
			if (status) {
				switchToCustomerView(event, acc);
			}
		}
		
		if (!status)
			System.out.println("Invalid user/pass");
		
	}
	
	//Manage Buses Scene
	public void switchToManageBuses(ActionEvent event) throws IOException {
		
			System.out.print("Switching to Bus Manager View...");
			Parent root = FXMLLoader.load(getClass().getResource("../application/ManageBuses.fxml"));		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
			scene= new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Manage Buses ("+currentUser+")");
			stage.show();

	}

	//Go Back to This Page once Logged out
	public void Logout(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void viewAllBuses(ActionEvent event) 
	{
		try
		{
			mysql=PersistenceFactory.getDBInstance("MySQL");
			ResultSet rs=mysql.displayAllBus();
			
			ObservableList<BusDescription> data = FXCollections.observableArrayList();
			
			busID.setCellValueFactory(new PropertyValueFactory<>("bus_id"));
			model.setCellValueFactory(new PropertyValueFactory<>("model"));
			year.setCellValueFactory(new PropertyValueFactory<>("year"));
			seatCount.setCellValueFactory(new PropertyValueFactory<>("seatCount"));
			last_Maintenance.setCellValueFactory(new PropertyValueFactory<>("last_Maintenence"));
			status.setCellValueFactory(new PropertyValueFactory<>("status"));
			cost.setCellValueFactory(new PropertyValueFactory<>("expenses"));
			
			
			
			
			while(rs.next()){
                //Iterate Row
				
				data.add(new BusDescription(
						rs.getInt("busID"),
						rs.getString("model"),
						rs.getString("year"),
						rs.getInt("SeatCount"),
						rs.getString("lastMaentenanceDate"),
						rs.getString("maintenance_active"),
						rs.getFloat("totalCost")
						
						));

            }
			
			
			tableBus.setItems(data);
			
			tableRowClickListener();
			
			rs.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Viewing All Buses");
			e.printStackTrace();
		
		}
		
	}
	public void viewBus(ActionEvent event) 
	{
		try
		{
			
			mysql=new SQLPersistence();
			ResultSet rs=mysql.displayBus(Integer.parseInt((busSearchBar.getText())));
			
			System.out.println("Finding...");
			ObservableList<BusDescription> data = FXCollections.observableArrayList();
			
			
			
			busID.setCellValueFactory(new PropertyValueFactory<>("bus_id"));
			model.setCellValueFactory(new PropertyValueFactory<>("model"));
			year.setCellValueFactory(new PropertyValueFactory<>("year"));
			seatCount.setCellValueFactory(new PropertyValueFactory<>("seatCount"));
			last_Maintenance.setCellValueFactory(new PropertyValueFactory<>("last_Maintenence"));
			status.setCellValueFactory(new PropertyValueFactory<>("status"));
			cost.setCellValueFactory(new PropertyValueFactory<>("expenses"));
			
			
			
			//System.out.println(Integer.parseInt((busSearchBar.getText())));
			while(rs.next()){
                //Iterate Row
				
				data.add(new BusDescription(
						rs.getInt("busID"),
						rs.getString("model"),
						rs.getString("year"),
						rs.getInt("SeatCount"),
						rs.getString("lastMaentenanceDate"),
						rs.getString("maintenance_active"),
						rs.getFloat("totalCost")
						
						));

            }
			
			
			tableBus.setItems(data);
			
			tableRowClickListener();
			
			rs.close();
			

			
		}
		catch(Exception e)
		{
			System.out.println("Error in Viewing All Buses");
			e.printStackTrace();
		
		}
		
	}
	
	//Listens to any row clicked
	void tableRowClickListener()
	{
		//Get Information of Clicked Row
		tableBus.setRowFactory(tv -> {
		    TableRow<BusDescription> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		             && event.getClickCount() == 2) {

		        	BusDescription clickedRow = row.getItem();
		            System.out.println(clickedRow.getBus_id());
		        }
		    });
		    return row ;
		});
		
	}
    @FXML
    void switchToRegisterView(ActionEvent event) throws IOException {
		System.out.print("Switching to Register View...");
		Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerRegister.fxml"));		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Register Account");
		stage.show();
    }

	
	
}
