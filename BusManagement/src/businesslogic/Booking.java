package businesslogic;

import java.sql.ResultSet;
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
	
	
	public ResultSet displayBooking(int ID, String choice) throws ClassNotFoundException, SQLException
	{
		return dbInstance.displayBooking(ID,choice);
	}
	
	public ResultSet displayAllBooking() throws ClassNotFoundException, SQLException {
		
		return dbInstance.displayAllBooking();
	}
	public boolean cancelAllBookings(int route_ID)
	{
		return dbInstance.cancelAllBookings(route_ID);
	}
	
	public boolean changeBookingStatus(int route_ID)
	{
		return dbInstance.changeBookingStatus(route_ID);
	}
	
}
