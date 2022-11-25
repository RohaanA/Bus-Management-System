package db;

import java.sql.SQLException;

public abstract class PersistenceHandler {
	//public abstract void saveCustomerDetails();
	public abstract boolean authenticate(String username, String password, String type) throws ClassNotFoundException, SQLException;
	public abstract boolean registerCustomer(String name, String cnic, String dob, String address);
}
