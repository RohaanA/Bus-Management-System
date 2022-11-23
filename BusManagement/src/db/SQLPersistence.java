package db;

import java.sql.*;

//Database persistence Handler


public class SQLPersistence {
	
	public boolean authenticate_Manager(String user,String pass) throws ClassNotFoundException, SQLException {
		
		
		System.out.println("Worked Till here");
		int found=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//Add your own password here
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/BusManagement","root","moizrules1");
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM Manager WHERE Username='"+ user +"' AND Password='"+pass+ "'; ");
		
		while(rs.next())
		{
			found=rs.getInt("COUNT(*)");
		}
		
		if(found==0)
		{
			System.out.println("Incorrect Username/Password");
			con.close();
			return false;
		}
		
		else
		{
			System.out.println("Logged In");
			con.close();
			return true;
		}
		
		
	}
	

}
