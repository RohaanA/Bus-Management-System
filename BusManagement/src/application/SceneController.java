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
		
		mysql=new SQLPersistence();	
		
		
		if(login_type.isSelected())
		{
			if(mysql.authenticate_Manager(txt_username.getText(), txt_password.getText()))
			{
				switchToManagerView(event,txt_username.getText());
				//login_type.setText("Hello");
				//userLabel.setText("poop");
			}
		}
		
		else
		{
			//Search in Customer SQL Table
		}
		
	}
	
	
}
