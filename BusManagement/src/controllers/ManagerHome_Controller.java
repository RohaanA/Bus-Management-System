package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManagerHome_Controller {
	

    @FXML
    private Label nameHolder;
	

	private Stage stage;
	private Scene scene;
	
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
	
	void Display_User(String Name)
	{
		
		currentUser=Name;		
		nameHolder.setText(currentUser);
		
	}

}
