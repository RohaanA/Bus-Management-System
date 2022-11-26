package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Customer {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	private String cnic;
	private String dob;
	private String address;
	private String phone;
	private int balance;
	
	public Customer(String username) {
		//TODO: Load customer data from username
		try {
			HashMap<String, String> customerData = dbInstance.loadCustomerData(username);

			phone = customerData.get("phone");
			cnic = customerData.get("cnic");
			dob = customerData.get("dob");
			address = customerData.get("address");
			balance = Integer.parseInt(customerData.get("balance"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getCNIC() {
		return cnic;
	}
	protected String getDOB() {
		return dob;
	}
	protected String getAddress() {
		return address;
	}
	protected String getPhone() {
		return phone;
	}
	protected int getBalance() {
		return balance;
	}
}
