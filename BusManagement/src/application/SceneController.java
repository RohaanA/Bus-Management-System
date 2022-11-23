package application;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
	
	
	

	//Basic Variables
	private Stage stage;
	private Scene scene;
	private SQLPersistence mysql;
	private String currentUser;
	
	
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
	
	
}
