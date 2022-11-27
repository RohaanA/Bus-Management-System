package businesslogic;

public class Payment {
	private String cardName;
	private String cardNumber;
	private String CVV;
	private String expiryDate;
	
	public Payment(String cardName, String cardNumber, String cVV, String expiryDate) {
		super();
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		CVV = cVV;
		this.expiryDate = expiryDate;
	}
	
	public boolean deductAmount(int payment) {
		//Add some validity checks
		if (cardNumber.length() == 16 || CVV.length() == 3) {
			System.out.println("Successfully deducted " + payment + " from card " + cardNumber);
			return true;
		}
		else System.out.println("Invalid card details!");
		return false;
	}
	
}
