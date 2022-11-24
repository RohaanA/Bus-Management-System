package db;

public interface PersistenceHandler {
	public void saveCustomerDetails();
	public boolean authenticate_Manager(String username, String password);
}
