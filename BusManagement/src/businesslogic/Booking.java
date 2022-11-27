package businesslogic;

import java.sql.SQLException;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Booking {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	
	public Booking() {
		
	}
	
	public boolean saveBooking(BookingDescription bk) {
		try {return dbInstance.saveBooking(bk);} catch (SQLException e) {e.printStackTrace();} return false;
	}
}
