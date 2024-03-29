package application;


import db.PersistenceFactory;
import db.PersistenceHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Testing
			Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Manager Login");
			Image icon = new Image("/bus-icon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Using singleton-factory pattern to instantiate single DB Instance.
		PersistenceHandler DB = PersistenceFactory.getDBInstance("MySQL");
		
		launch(args);
	}
}
