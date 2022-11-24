package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Testing
			Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
			Scene scene = new Scene(root,607,415);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Manager Login");
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Use factory pattern to instantiate singleton DB.
		
		
		launch(args);
	}
}
