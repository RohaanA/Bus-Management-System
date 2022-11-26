package businesslogic;

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
	}
}
