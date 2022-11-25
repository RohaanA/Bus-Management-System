package db;

import java.sql.*;

//Database persistence Handler


public class SQLPersistence extends PersistenceHandler {

	private Connection con;
	private String _connectionURL = "jdbc:mysql://localhost:3306/busdb";
	private String _connectAccount = "root";
	private String _dbPassword = "tiger12345";
	
	public SQLPersistence() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public boolean authenticate(String user,String pass, String type) throws SQLException {
		con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		System.out.println("Worked Till here");

		int found=0;
		Statement stmt=con.createStatement();
		ResultSet rs;
		
		if (type.equalsIgnoreCase("manager")) {
			rs=stmt.executeQuery("SELECT COUNT(*) FROM Account WHERE Username='"+ user +"' AND Password='"+pass+ "' AND accountType= 'Manager';");
		}
		else if (type.equalsIgnoreCase("customer")) {
			rs=stmt.executeQuery("SELECT COUNT(*) FROM Account WHERE Username='"+ user +"' AND Password='"+pass+ "' AND accountType= 'Customer';");
		}
		else return false;
		
		while(rs.next())
		{
			found=rs.getInt("COUNT(*)");
		}
		
		//Close connection & respond.
		con.close();
		
		if(found == 0)
			return false;
		return true;
		
	}

	@Override
	public boolean registerCustomer(String username, String password, String name, String cnic, String dob, String address) throws SQLException {
		con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		Statement stmt=con.createStatement();
		ResultSet rs;
		
		String insertionQuery = "INSERT INTO account (username, password, name, accountType) VALUES ('"+username+"', '"+password+"', '"+name+"' , Customer);";
		stmt.executeUpdate(insertionQuery);
		
		con.close();
		return false;
	}
	

}
