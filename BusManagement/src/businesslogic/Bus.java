package businesslogic;

import java.sql.ResultSet;
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
	
	public ResultSet displayAllBus()
	{
		try {
			return dbInstance.displayAllBus();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet displayBus(int BusId)
	{
		try {
			return dbInstance.displayBus(BusId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteBus(int BusId)
	{
		return dbInstance.deleteBus(BusId);
	}
	
	public boolean updateBusStatus(int BusId)
	{
		return dbInstance.updateBusStatus(BusId);
	}
}
