package db;

import java.sql.SQLException;

public abstract class PersistenceHandler {
	//public abstract void saveCustomerDetails();
	public abstract boolean authenticate(String username, String password, String type) throws SQLException;
	public abstract boolean registerCustomer(String username, String password, String name, String cnic, String dob, String address) throws SQLException;
}
