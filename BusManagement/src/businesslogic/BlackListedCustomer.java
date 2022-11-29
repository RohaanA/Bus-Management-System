package businesslogic;

public class BlackListedCustomer {



	

	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getCnic() {
		return cnic;
	}
	public void setCnic(String cnic) {
		this.cnic = cnic;
	}
	public boolean isBlacklist() {
		return Blacklist;
	}
	public void setBlacklist(boolean blacklist) {
		Blacklist = blacklist;
	}
	public BlackListedCustomer(String username, String cnic, boolean blacklist) {
		super();
		Username = username;
		this.cnic = cnic;
		Blacklist = blacklist;
	}
	private String Username;
	private String cnic;
	private boolean Blacklist;
	
	
}
