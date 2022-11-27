package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
//			//Get account ID of latest insertion
//			try(Statement accID_stmt = con.createStatement()) {
//				ResultSet rs = accID_stmt.executeQuery("SELECT MAX(accountID) AS id FROM account");
//				if (rs.next()) {
//					accID = rs.getInt("id");	
//					rs.close();
//				}
//				else return false;
//			}
//			catch (SQLException e) {
//				con.rollback();
//				con.setAutoCommit(true);
//				throw e;
//			}
			//Insert into customer
			try(Statement customer_stmt = con.createStatement()) {
				String customerInsertionQuery = "INSERT INTO customer (username, phone, cnic, dob, address) VALUES ('"+username+"','"+phone+"','"+cnic+"','"+dob+"','"+address+"');";
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
		
		
		
		
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM bus ");
		
		
		
		return rs;
	
	}

	@Override
	public HashMap<String, String> loadCustomerData(String username) throws SQLException {
		System.out.println("Load customer data called.");
		try (Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);) {
			
			try (Statement custStmt = con.createStatement()) {
				String accountInsertionQuery = "SELECT phone, cnic, dob, address, balance FROM CUSTOMER where username='"+username+"';";
				ResultSet rs = custStmt.executeQuery(accountInsertionQuery);
				//Converting ResultSet to ArrayList
					ResultSetMetaData rmd = rs.getMetaData();
					int columnCount = rmd.getColumnCount();
					HashMap<String, String> customerDetails = new HashMap<String, String>();
					while(rs.next()) 
						for(int i=1; i<=columnCount; i++) 
							customerDetails.put(rmd.getColumnName(i), rs.getObject(i).toString());
						
					
					con.close();
					rs.close();
					return customerDetails;
			}
			catch (SQLException e) { throw e; }
		} catch (SQLException e) { throw e; }
	}
	
	@Override
	public boolean saveCustomerData(String username, HashMap<String, String> data) throws SQLException {
		//TODO: Save customer data;
		try (Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);) {
			try (Statement custStmt = con.createStatement()) {
				String phone = data.get("phone");
				String cnic = data.get("cnic");
				String address = data.get("address");
				String dob = data.get("dob");

				System.out.println(phone);
				System.out.println(address);
				System.out.println(cnic);
				System.out.println(dob);
				
				
				String updateQuery = "UPDATE CUSTOMER SET phone = '"+phone+"', cnic='"+cnic+"', dob='"+dob+"', address='"+address+"' WHERE username='" + username + "' ;";
				custStmt.executeUpdate(updateQuery);
				
				con.close();
				return true;
			} catch (SQLException e) { throw e; }
		} catch (SQLException e) { throw e; }
	}
	
	public boolean deleteBus(int busID)
	{
		try {
			
			Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
			
			Statement stmt=con.createStatement();
			
			stmt.executeUpdate("Delete from bus where busID=" + busID);
			
			return true;
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	

	public boolean updateBusStatus(int busID)
	{
		
		
		try {
			
			//First Get status
			boolean value=false;
			Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
			
			Statement stmt1=con.createStatement();
			
			ResultSet rs=stmt1.executeQuery("Select maintenance_active from bus where busID=" + busID);
			
			System.out.println("Reached here1");
			
			while(rs.next())
			{
				//Get the value
				value=rs.getBoolean("maintenance_active");
			}
			
			Statement stmt2=con.createStatement();
			
			
			
			//Then do its complement
			stmt2.executeUpdate("UPDATE bus SET maintenance_active="+ !value +" where busID=" + busID);
			
			
			rs.close();	
	
			return true;
		} 
		
		catch (SQLException e) {
			
			System.out.println("An Error Occured while updating bus Status!");
			e.printStackTrace();
			return false;
			
		}
	}
	
	
	public ResultSet displayAllBooking()throws ClassNotFoundException, SQLException
	{
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM booking ");
		
		
		
		return rs;
		
	}
	
	public ResultSet displayBooking(int value,String type)throws ClassNotFoundException, SQLException
	{
		
		Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
		
		Statement stmt=con.createStatement();
		
		ResultSet rs;
		
		if(type=="Route ID")
		{
			rs=stmt.executeQuery("SELECT * FROM booking where routeID="+ value);			
		}
		
		else if(type=="Customer ID")
		{
			rs=stmt.executeQuery("SELECT * FROM booking where accountID="+ value);
		}
		
		else
		{
			rs=stmt.executeQuery("SELECT * FROM booking where bookingID="+ value);
		}
		
		
		
		return rs;
		
	}

	public boolean cancelAllBookings(int routeID)
	{

		try {
			
			Connection con = DriverManager.getConnection(_connectionURL, _connectAccount, _dbPassword);
			
			Statement stmt=con.createStatement();
			
			stmt.executeUpdate("Update booking set currentStatus='Cancelled' where routeID=" + routeID);
			
			return true;
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
			
		}
		
	}
}
