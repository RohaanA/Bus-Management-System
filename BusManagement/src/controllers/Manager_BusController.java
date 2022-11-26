package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import application.Classes.BusDescription;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class Manager_BusController {
	
	@FXML
	private TextField busSearchBar;
	@FXML
	private Label nameHolder;
   @FXML
    private TextField selectedID;
	
	
	
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
		
		 
	    
		private Stage stage;
		private Scene scene;
		private PersistenceHandler mysql;		
		String currentUser="";
			
		
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
				
				mysql=PersistenceFactory.getDBInstance("MySQL");
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
			        	
			        	//Put it in Selected Id as well
			        	selectedID.setText(Integer.toString((clickedRow.getBus_id())));
			        	
			        	
			            System.out.println(clickedRow.getBus_id());
			        }
			    });
			    return row ;
			});
			
		}
		
		public void deleteBus(ActionEvent event)
		{
			
			int bus_ID=Integer.parseInt(selectedID.getText());
			mysql=PersistenceFactory.getDBInstance("MySQL");
			
			if(mysql.deleteBus(bus_ID))
			{
				viewAllBuses(event); 
				System.out.print("Deleted Successfully");
				
			}
			else
			{
				System.out.print("Unable to Delete");
			}
			
			
		}
		
		
		public void Display_User(String Name)
		{
			
			currentUser=Name;		
			nameHolder.setText(currentUser);
			
		}

}
