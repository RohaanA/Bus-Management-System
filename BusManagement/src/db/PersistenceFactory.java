package db;

public class PersistenceFactory {
	public static PersistenceHandler createPersistenceHandler(String type) {
		if ("MySQL".equalsIgnoreCase(type)) {
			//return MySQL.
			return new SQLPersistence();
		}
		else return null;
		
	}
}
