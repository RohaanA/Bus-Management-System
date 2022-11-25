package businesslogic;

import java.sql.SQLException;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Account {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	
	public Account() {
		
	}
	
	public boolean login(String username, String password, String type) throws ClassNotFoundException, SQLException {
		return dbInstance.authenticate(username, password, type);
	}
	public boolean registerCustomer(String name, String cnic, String dob, String address) {
		return dbInstance.registerCustomer(name, cnic, dob, address);
	}
}
