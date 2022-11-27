package businesslogic;

public class Booking {

	private int bookingID;
	private int routeID;
	
	private int accountID;
	private String currentStatus;
	
	public Booking(int bookingID, int routeID, int accountID, String currentStatus) {
		super();
		this.bookingID = bookingID;
		this.routeID = routeID;
		this.accountID = accountID;
		this.currentStatus = currentStatus;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
	
	
	
}
