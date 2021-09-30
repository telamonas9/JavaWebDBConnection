package mainpackage;

import java.sql.SQLException;
import java.util.ArrayList;

import dbConnectionPackage.DoctorConnection;

public class Doctor extends Users {
  
	private String specialty;
	
	public Doctor(String username,String password, String name, String surname, String spec) 
	{
		
		super(username, password , name, surname);
		this.specialty=spec;	
	}
	
	public String getspecialty()
	{
		return specialty;
	}
	
	
	
	public ArrayList<String> scheduledappointmets()
	{
		DoctorConnection dCon = new DoctorConnection();
    	return dCon.getDBDoctorAppointmentHistory(super.getUsername());
	}


	public boolean cancelAppointment(String date, String time) throws ClassNotFoundException, SQLException {
		DoctorConnection dCon = new DoctorConnection();
    	return dCon.cancelDBdoctorAppointment(this,date,time);
	}

	public boolean createAppMonth(String date, String time) throws ClassNotFoundException, SQLException {
		DoctorConnection dCon = new DoctorConnection();
    	return dCon.createDBdoctorAppointment(this,date,time); 
	}
}
