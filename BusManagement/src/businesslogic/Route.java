package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Route {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	
	public Route() {
		//
	}
	
	public ArrayList<String> getAllRoutes() throws SQLException {
		return dbInstance.getAllRoutes();
	}
}
