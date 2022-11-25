package db;

import java.sql.SQLException;

public abstract class PersistenceHandler {
	//public abstract void saveCustomerDetails();
	public abstract boolean authenticate_Manager(String username, String password) throws ClassNotFoundException, SQLException;
}
