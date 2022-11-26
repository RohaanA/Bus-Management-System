package businesslogic;

import java.sql.SQLException;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Account {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	
	public Account() {
		
	}
	
	public boolean login(String username, String password, String type) throws SQLException {
		return dbInstance.authenticate(username, password, type);
	}
	public boolean registerCustomer(String username, String password, String phone, String cnic, String dob, String address) throws SQLException {
		return dbInstance.registerCustomer(username, password, phone, cnic, dob, address);
	}
}
