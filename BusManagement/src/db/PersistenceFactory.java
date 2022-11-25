package db;

public class PersistenceFactory {
	private static PersistenceHandler dbInstance = null;
	private static PersistenceFactory instance = null;
	
	private PersistenceFactory(String type) {
		if ("MySQL".equalsIgnoreCase(type)) {
			//return MySQL.
			dbInstance =  new SQLPersistence();
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
