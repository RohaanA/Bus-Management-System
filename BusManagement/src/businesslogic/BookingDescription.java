package businesslogic;

public class BookingDescription {

	private int bookingID;
	private int routeID;
	private int seatNumber;
	private String username;
	private String bookingStatus;
	private String paymentStatus;
	
	public BookingDescription(int bookingID, int routeID, int seatNumber, String username, String bookingStatus,
			String paymentStatus) {
		super();
		this.bookingID = bookingID;
		this.routeID = routeID;
		this.seatNumber = seatNumber;
		this.username = username;
		this.bookingStatus = bookingStatus;
		this.paymentStatus = paymentStatus;
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
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
	
	
	
	
	
	
}
