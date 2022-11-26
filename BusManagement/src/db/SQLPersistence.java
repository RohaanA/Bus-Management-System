package db;

import java.sql.*;

//Database persistence Handler


public class SQLPersistence extends PersistenceHandler {

	private String _connectionURL = "jdbc:mysql://localhost:3306/busdb";
	private String _connectAccount = "root";
	private String _dbPassword = "tiger12345";
	
	public SQLPersistence() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public boolean authenticate(String user,String pass, String type) throws SQLException {
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		

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
		
		//Close connection & result set.
		rs.close();
		con.close();
		
		if(found == 0)
			return false;
		return true;
		
	}

	@Override
	public boolean registerCustomer(String username, String password, String phone, String cnic, String dob, String address) throws SQLException {
		try (Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);) {
			con.setAutoCommit(false);
			int accID;
			
			//Insert into account
			try (Statement account_stmt = con.createStatement()) {
				String accountInsertionQuery = "INSERT INTO account (username, password, accountType) VALUES ('"+username+"', '"+password+"', 'Customer');";
				account_stmt.executeUpdate(accountInsertionQuery);
			}
			catch (SQLException e) {
				con.rollback();
				con.setAutoCommit(true);
				throw e;
			}
			//Get account ID of latest insertion
			try(Statement accID_stmt = con.createStatement()) {
				ResultSet rs = accID_stmt.executeQuery("SELECT MAX(accountID) AS id FROM account");
				if (rs.next()) {
					accID = rs.getInt("id");	
					rs.close();
				}
				else return false;
			}
			catch (SQLException e) {
				con.rollback();
				con.setAutoCommit(true);
				throw e;
			}
			//Insert into customer
			try(Statement customer_stmt = con.createStatement()) {
				String customerInsertionQuery = "INSERT INTO customer (accountID, phone, cnic, dob, address) VALUES ('"+accID+"','"+phone+"','"+cnic+"','"+dob+"','"+address+"');";
				customer_stmt.executeUpdate(customerInsertionQuery);	
			} catch (SQLException e) {
				con.rollback();
				con.setAutoCommit(true);	
				throw e;
			}
			
			con.commit();
			con.setAutoCommit(true);
			con.close();
			return true;
	} catch (SQLException e) {
		throw e;
	}
}
	
//	View All Buses
	public ResultSet displayBus(int busID) throws ClassNotFoundException, SQLException {
		
	
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM bus WHERE busID="+ busID);
		
		if(rs==null)
		{
			 System.out.println( "EMPTY");
		}
		else
		{
			System.out.println( "Good");
		}
		

		
		return rs;

		
	}
	
	//	View All Buses
	public ResultSet displayAllBus() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM bus ");
		
		
		
		return rs;
	
	}
	
	

}
