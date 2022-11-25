package db;

import java.sql.SQLException;

public class PersistenceFactory {
	private static PersistenceHandler dbInstance = null;
	private static PersistenceFactory instance = null;
	
	private PersistenceFactory(String type) {
		if ("MySQL".equalsIgnoreCase(type)) {
			//return MySQL.
			try {
				dbInstance =  new SQLPersistence();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else dbInstance = null;
	}
	
	public static PersistenceHandler getDBInstance(String type) {
		if (instance == null) {
			instance = new PersistenceFactory(type);
		}
		
		return dbInstance;
	}
}
