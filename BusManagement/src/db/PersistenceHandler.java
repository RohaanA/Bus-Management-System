package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class PersistenceHandler {
	//public abstract void saveCustomerDetails();
	public abstract boolean authenticate(String username, String password, String type) throws SQLException;
	public abstract boolean registerCustomer(String username, String password, String phone, String name, String cnic, String dob) throws SQLException;
	public abstract ResultSet displayBus(int busID) throws ClassNotFoundException, SQLException;
	public abstract ResultSet displayAllBus() throws ClassNotFoundException, SQLException;
	public abstract boolean deleteBus(int busID);
	public abstract boolean updateBusStatus(int busID);
	public HashMap<String, String> loadCustomerData(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
