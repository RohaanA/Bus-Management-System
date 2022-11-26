package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Customer {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	private String cnic;
	private String dob;
	private String address;
	private String phone;
	
	public Customer(String username) {
		//TODO: Load customer data from username
		try {
			ArrayList<String> customerData = dbInstance.loadCustomerData(username);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
