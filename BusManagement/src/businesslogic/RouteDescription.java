package businesslogic;

public class RouteDescription {
	private int routeID;
	private String deptDate;
	private String deptTime;
	private int fare;
	private String fromLocation;
	private String toLocation;
	
	public RouteDescription(int routeID, String fromLocation, String toLocation, int fare, String deptDate, String deptTime) {
		this.routeID = routeID;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fare = fare;
		this.deptDate = deptDate;
		this.deptTime = deptTime;
	}

	public int getRouteID() {
		return routeID;
	}

	public String getDeptDate() {
		return deptDate;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public int getFare() {
		return fare;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	
	
	
	
}