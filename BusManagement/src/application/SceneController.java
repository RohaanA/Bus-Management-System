package application;


import java.io.IOException;
import java.sql.SQLException;

import businesslogic.Account;
import db.SQLPersistence;
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

	@FXML
	TextField txt_username;
	@FXML
	PasswordField txt_password;
	@FXML 
	RadioButton login_type;
	@FXML
	private Label name_Disp;
	
	@FXML
	Label userLabel;
	
	private Stage stage;
	private Scene scene;
	//private Parent root;
	private SQLPersistence mysql;
	
	
	/*public void switchToLoginPage(ActionEvent event) throws IOException {
 		
		Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}*/
	
	
	
	public void switchToManagerView(ActionEvent event,String Name) throws IOException {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Manager View");
		stage.show();
		System.out.println(Name);
		
		
		
		
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
				switchToManagerView(event, username);
			}
			else System.out.println("Invalid username/password.");
		}
		else
		{
			status = acc.login(username, password, "Customer");
			//Switch to customer view.
		}
		
	}
	
	
}
