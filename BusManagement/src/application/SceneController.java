package application;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Classes.BusDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/*
 * A Controller is added through Scene Builder options. 
 * Through this controls can access functions in Controller
 */

public class SceneController {

	//FXML Elements
	@FXML
	TextField txt_username;
	@FXML
	PasswordField txt_password;
	@FXML 
	RadioButton login_type;
	@FXML
	TextField busSearchBar;
	
	@FXML
	TableView<BusDescription> tableBus;
	@FXML
	TableColumn<BusDescription,String> busID;
	@FXML
	TableColumn<BusDescription,String> model;
	@FXML
	TableColumn<BusDescription,String> year;
	@FXML
	TableColumn<BusDescription,String> seatCount;
	@FXML
	TableColumn<BusDescription,String> last_Maintenance;
	@FXML
	TableColumn<BusDescription,String> status;
	@FXML
	TableColumn<BusDescription,String> cost;
	
	

	//Basic Variables
	private Stage stage;
	private Scene scene;
	private SQLPersistence mysql;
	static String currentUser="";
	
    
	
	//Go Back to This Page once Logged out
	public void Logout(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	//Once Logged in as Manager this is the first page
	public void switchToManagerView(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Manager View ("+currentUser+")");
		stage.show();

	}
	
	//Manage Buses Scene
	public void switchToManageBuses(ActionEvent event) throws IOException {

			Parent root = FXMLLoader.load(getClass().getResource("ManageBuses.fxml"));		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
			scene= new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Manage Buses ("+currentUser+")");
			stage.show();

		}
	
	
	//Authenticate The Login
	public void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		
		mysql=new SQLPersistence();	
		
		
		if(login_type.isSelected())
		{
			if(mysql.authenticate_Manager(txt_username.getText(), txt_password.getText()))
			{
				currentUser=txt_username.getText();
				switchToManagerView(event);			
			}
		}
		
		else
		{
			//Search in Customer SQL Table
		}
		
	}
	
	
	public void viewAllBuses(ActionEvent event) 
	{
		try
		{
			mysql=new SQLPersistence();
			ResultSet rs=mysql.displayAllBus();
			
			if(rs!=null)
			{
				while(rs.next()){
	                //Iterate Row
					/*
					 * ObservableList<String> row = FXCollections.observableArrayList(); for(int i=1
					 * ; i<=rs.getMetaData().getColumnCount(); i++){ //Iterate Column
					 * row.add(rs.getString(i)); } System.out.println("Row [1] added "+row );
					 * data.add(row);
					 */
					
					System.out.println(rs.getInt("busID"));
	            }
				
			}
			else
			{
				System.out.println("No Data Found");
			}
			//tableBus.setItems(data);
			
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
						rs.getString("busID"),
						rs.getString("model"),
						rs.getString("year"),
						rs.getString("SeatCount"),
						rs.getString("lastMaentenanceDate"),
						rs.getString("maintenance_active"),
						rs.getString("totalCost")
						
						));
				
				 /* ObservableList<BusDescription> row = FXCollections.observableArrayList(); 
				  
				  for(int i=1
				  ; i<=rs.getMetaData().getColumnCount(); i++){ //Iterate Column
					  System.out.println("Data: "+rs.getString(i) );
					  row.add(rs.getString(i)); 
					  System.out.println("Row [1] added "+row );
				  } 
				 
				 data.add(row);
				 
				 System.out.println("Good Job");*/
				
				
            }
			
			
			tableBus.setItems(data);
			
			//System.out.println(data);
			
			
			
			
			rs.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Viewing All Buses");
			e.printStackTrace();
		
		}
		
	}
	
}
