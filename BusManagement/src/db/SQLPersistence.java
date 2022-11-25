package db;

import java.sql.*;

//Database persistence Handler


public class SQLPersistence extends PersistenceHandler {

	private Connection con;
	
	public SQLPersistence() throws SQLException, ClassNotFoundException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdb","root","tiger12345");
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public boolean authenticate(String user,String pass, String type) throws ClassNotFoundException, SQLException {
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
	public boolean registerCustomer(String name, String cnic, String dob, String address) {
//		Statement stmt=con.createStatement();
//		ResultSet rs;
//		
		
		return false;
	}
	

}
