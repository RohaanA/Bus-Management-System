package application.Classes;

public class BusDescription {
	
	//Attributes
	private int bus_id;
	private String model;
	private String year;
	private int seatCount;
	private String last_Maintenence;
	private String status;
	private float expenses;
	
	public BusDescription(int bus_id,String model,String year,int seatCount,String lastmaint,String status,float cost)
	{
		
		this.bus_id= bus_id;
		this.model= model;
		this.year=year;
		this.seatCount=seatCount;
		this.last_Maintenence=lastmaint;
		this.status=status;
		this.expenses=cost;
	}

	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getLast_Maintenence() {
		return last_Maintenence;
	}

	public void setLast_Maintenence(String last_Maintenence) {
		this.last_Maintenence = last_Maintenence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getExpenses() {
		return expenses;
	}

	public void setExpenses(float expenses) {
		this.expenses = expenses;
	}

	
	
	
	
	

}
