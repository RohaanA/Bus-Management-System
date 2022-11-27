package businesslogic;

import java.sql.SQLException;

import db.PersistenceFactory;
import db.PersistenceHandler;

/*
 * Contains functions related to bus.
 */
public class Bus {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	
	
	public int getSeatsFromBusID(String busID) throws SQLException {
		return dbInstance.getBusSeatCount(busID);
	}
}
