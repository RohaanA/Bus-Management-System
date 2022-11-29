package businesslogic;

import java.sql.ResultSet;

import db.PersistenceFactory;
import db.PersistenceHandler;

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
	public BlackListedCustomer() {
		
	}
	public ResultSet displayBlackListCustomers()
	{
		ResultSet rs=mysql.displayBlackListCustomers();
		return rs;
	}
	
	public boolean blackListCustomer(String username)
	{
		return mysql.blackListCustomer(username);
	}
	
	private PersistenceHandler mysql=PersistenceFactory.getDBInstance("MySQL");
	private String Username;
	private String cnic;
	private boolean Blacklist;
	
	
}
