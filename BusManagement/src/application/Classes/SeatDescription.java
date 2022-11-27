package application.Classes;

public class SeatDescription {
	private int seatNumber;
	private String status;
	
	public SeatDescription(int snum, String st) {
		seatNumber = snum;
		status = st;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public String getStatus() {
		return status;
	}
	
}
