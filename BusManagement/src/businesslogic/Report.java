package businesslogic;

import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import db.PersistenceFactory;
import db.PersistenceHandler;

public class Report {
	
	private float earned;
	private float spent;
	private float totalProfit;
	private PersistenceHandler mysql=PersistenceFactory.getDBInstance("MySQL");
	
	public Report() {
		
		this.earned = 0;
		this.spent =0;
		this.totalProfit = 0;
		
	}
	
	public Report(float earned, float spent, float totalProfit) {
		super();
		this.earned = earned;
		this.spent = spent;
		this.totalProfit = totalProfit;
		
	}
	public float getEarned() {
		return earned;
	}
	public void setEarned(float earned) {
		this.earned = earned;
	}
	public float getSpent() {
		return spent;
	}
	public void setSpent(float spent) {
		this.spent = spent;
	}
	public float getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(float totalProfit) {
		this.totalProfit = totalProfit;
	}

	public ResultSet generateReport()
	{
		mysql=PersistenceFactory.getDBInstance("MySQL");
		ResultSet rs=mysql.generateReport();
		return rs;
	}
	

}
