package businesslogic;

import java.sql.SQLException;
import java.util.HashMap;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Account {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	private String username = "";
	private Customer cust = null;
	
	private void loadAccountData(String username, String type) {
		if (type.equalsIgnoreCase("customer")) {
			//TODO: Load customer data
			this.username = username;
			cust = new Customer(username);
		}
		else if (type.equalsIgnoreCase("manager")) {
			//TODO: Load manager data
		}
		else System.out.println("ERROR! Account type invalid.");
	}
	
	public Account() {
		
	}
	
	public boolean login(String username, String password, String type) throws SQLException {
		boolean authStatus = dbInstance.authenticate(username, password, type);
		if (authStatus) {
			loadAccountData(username, type);
		}
		return authStatus;
	}
	public boolean registerCustomer(String username, String password, String phone, String cnic, String dob, String address) throws SQLException {
		return dbInstance.registerCustomer(username, password, phone, cnic, dob, address);
	}
	public boolean save(String username, String type, HashMap<String, String> data) {
		if (type.equals("customer")) {
			//Save customer details
			try {
				return cust.save(username, data);
			} catch (SQLException e) {e.printStackTrace();}
		}
		else if (type.equals("manager")) {
			//Save manager details
		}
		return false;
	}

	public String getCNIC() {
		if (cust == null)
			return null;
		
		return cust.getCNIC();
	}

	public String getDOB() {
		if (cust == null)
			return null;
		
		return cust.getDOB();
	}

	public String getPhone() {
		if (cust == null)
			return null;
		
		return cust.getPhone();
	}

	public String getAddress() {
		if (cust == null)
			return null;
		
		return cust.getAddress();
	}

	public String getUsername() {
		return this.username;
	}
}
