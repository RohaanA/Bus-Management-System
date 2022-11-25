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
	private TextField txt_username;
	@FXML
	private PasswordField txt_password;
	@FXML 
	private RadioButton login_type;
   
	private Stage stage;
	private Scene scene;

	static String currentUser="";
	
	
	public void switchToManagerView(ActionEvent event) throws IOException {
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../application/ManagerView.fxml"));	
		Parent root=loader.load();
		ManagerHome_Controller controller=loader.getController();
		System.out.println("Hello");
		System.out.println(currentUser);
		controller.Display_User(currentUser);
		
		//Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Manager View");
		stage.show();
		
	}
	
	public void switchToCustomerView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../application/CustomerDashboard.fxml"));
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
				currentUser=username;
				switchToManagerView(event);
				
			}
		}
		else
		{
			status = acc.login(username, password, "Customer");
			//Switch to customer view.
			if (status) {
				currentUser=username;
				switchToCustomerView(event);
				
			}
		}
		
		if (!status)
			System.out.println("Invalid user/pass");
		
	}
	
	
	//Go Back to This Page once Logged out
	public void Logout(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("../application/ManagerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	
}
