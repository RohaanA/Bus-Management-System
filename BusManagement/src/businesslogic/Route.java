package businesslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.PersistenceFactory;
import db.PersistenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Route {
	private PersistenceHandler dbInstance = PersistenceFactory.getDBInstance("MySQL");
	private ArrayList<RouteDescription> allRoutes = null;
	private ArrayList<Integer> bookedSeats;

	public Route() {
		allRoutes = new ArrayList<RouteDescription>();
		try {
			ResultSet rs= dbInstance.getAllRouteData();
			while(rs.next()) {	
					allRoutes.add(new RouteDescription(
							rs.getInt("routeID"),
							rs.getString("fromLocation"),
							rs.getString("toLocation"),
							rs.getInt("cost"),
							rs.getString("deptDate"),
							rs.getString("deptTime")
							));
	            }
			//rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		bookedSeats = new ArrayList<Integer>();
}
	
	public ArrayList<String> getAllRouteLocations() throws SQLException {
		return dbInstance.getAllRouteLocations();
	}

	public ObservableList<RouteDescription> getRouteData(String str_fromLoc, String str_toLoc) {
		ObservableList<RouteDescription> data = FXCollections.observableArrayList();
		
		for(int i=0; i<allRoutes.size(); i++) {
			if (allRoutes.get(i).getFromLocation().equals(str_fromLoc) && 
				allRoutes.get(i).getToLocation().equals(str_toLoc)) {
				//System.out.println("Adding route: " + allRoutes.get(i).getDeptDate());
				data.add(allRoutes.get(i));
			}
				
		}
		return data;
	}

	public ArrayList<Integer> getBookedSeats(int routeID) throws SQLException {
		return dbInstance.getBookedSeats(routeID);
	}
}
