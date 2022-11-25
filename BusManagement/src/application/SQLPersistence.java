package application;

import java.sql.*;

//Database persistence Handler

/*1) ReAssign Buses to routes
2) Add new routes
3) Add new Bus
4) vIEW rOUTES WITH MISSING Bus
5) Change Bus statuses*/

public class SQLPersistence {
	
	
	
	
	
	public boolean authenticate_Manager(String user,String pass) throws ClassNotFoundException, SQLException {
		
		
	
		int found=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//Add your own password here
		Connection con=DriverManager.getConnection( 
				"jdbc:mysql://localhost:3306/busdb","root","moizrules1");
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM account WHERE username='"+ user +"' AND password='"+pass+ "' AND accountType='Manager'; ");
		
		
		
		 
		 
		
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
	
	//	View All Buses
	ResultSet displayBus(int busID) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//Add your own password here
		Connection con=DriverManager.getConnection( 
				"jdbc:mysql://localhost:3306/busdb","root","moizrules1");
		
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
		
	
        //Iterate Row
		
		 
		 
	
		 
		
		
		
		return rs;
		
		
		
		
		
		
	}
	
	//	View All Buses
	ResultSet displayAllBus() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//Add your own password here
		Connection con=DriverManager.getConnection( 
				"jdbc:mysql://localhost:3306/busdb","root","moizrules1");
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM bus ");
		
		
		
		return rs;
		
		
		
		
		
		
	}
	
	
	
	//Remove Bus based on id
	/*
	 * public void remove_Bus(int BusID) {
	 * 
	 * int found=0; Class.forName("com.mysql.cj.jdbc.Driver");
	 * 
	 * 
	 * //Add your own password here Connection con=DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/BusManagement","root","moizrules1");
	 * 
	 * Statement stmt=con.createStatement(); ResultSet
	 * rs=stmt.executeQuery("SELECT COUNT(*) FROM account WHERE username='"+ user
	 * +"' AND password='"+pass+ "' AND accountType='Manager'; ");
	 * 
	 * 
	 * 
	 * }
	 */
	

}
