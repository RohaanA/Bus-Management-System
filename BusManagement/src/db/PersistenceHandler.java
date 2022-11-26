package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PersistenceHandler {
	//public abstract void saveCustomerDetails();
	public abstract boolean authenticate(String username, String password, String type) throws SQLException;
	public abstract boolean registerCustomer(String username, String password, String name, String cnic, String dob, String address) throws SQLException;
	public abstract ResultSet displayBus(int busID) throws ClassNotFoundException, SQLException;
	public abstract ResultSet displayAllBus() throws ClassNotFoundException, SQLException;
	public abstract boolean deleteBus(int busID);
	
	//Delete Booking
	//Update Bus status
	
	//Add Bus
}
