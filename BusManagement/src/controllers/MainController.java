package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * The function of this controller is to act as a bridge b/w all controllers. (ie allows them to switch scenes without duplicate functions on every controller).
 * This controller is not initialized, but only its functions are used.
 */
public class MainController {
	private MainController() {} // Ensure class cannot be instantiated.
	
	static void test() {
		
	}
}
