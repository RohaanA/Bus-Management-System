package application.Classes;

public class BusDescription {
	
	//Attributes
	private String bus_id;
	private String model;
	private String year;
	private String seatCount;
	private String last_Maintenence;
	private String status;
	private String expenses;
	
	public BusDescription(String bus_id,String model,String year,String seatCount,String lastmaint,String status,String cost)
	{
		
		this.bus_id= bus_id;
		this.model= model;
		this.year=year;
		this.seatCount=seatCount;
		this.last_Maintenence=lastmaint;
		this.status=status;
		this.expenses=cost;
	}

	public String getBus_id() {
		return bus_id;
	}

	public void setBus_id(String bus_id) {
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

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
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

	public String getExpenses() {
		return expenses;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}
	
	
	
	

}
