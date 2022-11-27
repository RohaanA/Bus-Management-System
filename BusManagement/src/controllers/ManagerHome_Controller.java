package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import businesslogic.BlackListedCustomer;
import businesslogic.BookingDescription;
import businesslogic.Report;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManagerHome_Controller {
	

    @FXML
    private Label nameHolder;
    @FXML
    private TextField username_textbox;
    
    private PersistenceHandler mysql;		
    
    //Report Table
    @FXML
    private TableColumn<Report, Float> Earned;

    @FXML
    private TableColumn<Report, Float> Spent;

    @FXML
    private TableColumn<Report, Float> totalProfit;
    @FXML
    private TableView<Report> reportTable;
    
    @FXML
    private TableColumn<Report, Integer> routeID_report;
    
    //BlackList Table
    
    @FXML
    private TableView<BlackListedCustomer> blackListTable;

    @FXML
    private TableColumn<BlackListedCustomer, Boolean> blackListed;

    @FXML
    private TableColumn<BlackListedCustomer, String> cnic;
    
    @FXML
    private TableColumn<BlackListedCustomer, String> username;
    
   

   


	private Stage stage;
	private Scene scene;
	
	String currentUser="";
	public void initialize()
	{
		DisplayblackListCustomers();
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
	
	
	public void switchToManageBooking(ActionEvent event) throws IOException {
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../application/ManageBooking.fxml"));	
		Parent root=loader.load();
		Manager_BookingController controller=loader.getController();
		
		
		controller.Display_User(currentUser);
		
		//Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Manage Booking");
		stage.show();
		
	}
	
	//Manage Buses Scene
	public void switchToManageBuses(ActionEvent event) throws IOException {
		
			System.out.print("Switching to Bus Manager View...");
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

	//Go Back to This Page once Logged out
	public void Logout(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void generateReport(ActionEvent event)
	{
		try
		{
			
			Report object=new Report();
			//mysql=PersistenceFactory.getDBInstance("MySQL");
			ResultSet rs=object.generateReport();
			
			ObservableList<Report> data = FXCollections.observableArrayList();
			
			Earned.setCellValueFactory(new PropertyValueFactory<>("earned"));
			Spent.setCellValueFactory(new PropertyValueFactory<>("spent"));
			totalProfit.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));
			//routeID_report.setCellValueFactory(new PropertyValueFactory<>("routeID"));
			
			
			
			while(rs.next()){
                //Iterate Row
				
				
				data.add(new Report(
												
						rs.getFloat(1),rs.getFloat(2),(rs.getFloat(1)-rs.getFloat(2))
	
						));

            }
			
			
			reportTable.setItems(data);
			
			
			
			rs.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Viewing Report");
			e.printStackTrace();
		
		}
	}
	
	void DisplayblackListCustomers()
	{
		
		try
		{
			BlackListedCustomer obj=new BlackListedCustomer();
			
			
			ResultSet rs=obj.displayBlackListCustomers();
			
			ObservableList<BlackListedCustomer> data = FXCollections.observableArrayList();
			
			
			cnic.setCellValueFactory(new PropertyValueFactory<>("cnic"));
			username.setCellValueFactory(new PropertyValueFactory<>("Username"));
			blackListed.setCellValueFactory(new PropertyValueFactory<>("Blacklist"));
			System.out.println("Hi");
			
			
			
			
			while(rs.next()){
                //Iterate Row
				
				data.add(new BlackListedCustomer(
						
						rs.getString("username"),
						rs.getString("cnic"),
						rs.getBoolean("isBlacklisted")
						
						));

            }
			
			
			blackListTable.setItems(data);
			
			//tableRowClickListener();
			
			rs.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Viewing All Customers");
			e.printStackTrace();
		
		}
		
		
	}
	
	public void blackListCustomer(ActionEvent event)
	{
		String username=username_textbox.getText();
		
		BlackListedCustomer obj=new BlackListedCustomer();
	
		if(obj.blackListCustomer(username))
		{
			DisplayblackListCustomers(); 
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
