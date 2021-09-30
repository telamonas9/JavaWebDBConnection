package mainpackage;

import java.sql.SQLException;
import java.util.ArrayList;

import dbConnectionPackage.PatientConnection;

public class Patient extends Users{
	
	private int amka;
	
	public Patient(String username,String password, String name, String surname, int am) 
	{
		super(username, password , name, surname);
		this.amka=am;
		//mporw edw na grafw ton patient sthn klassh

	}

	
	
	
	public int getAMKA()
	{
		return amka;
	}
	
	public boolean registration() throws ClassNotFoundException, SQLException
	{
		 PatientConnection ptCon = new PatientConnection();
		 return ptCon.registerDBpatient(this);
	}
	
	public ArrayList<String> availableApp(String specialty)
	{
		PatientConnection ptCon = new PatientConnection();
		 return ptCon.availableDBpatientAppointments(specialty);
	}
	
	public boolean cancelAppointment(String date, String time) throws ClassNotFoundException, SQLException
	{
		 PatientConnection ptCon = new PatientConnection();
		 return ptCon.cancelDBpatientAppointment(this,date,time);
	}
	
    

    public ArrayList<String> appointmentshistory()
    {
    	PatientConnection con = new PatientConnection();
    	return con.getDBPatientAppointmentHistory(super.getUsername());
    }




	public boolean createApp(String date, String time, String doctor) throws ClassNotFoundException, SQLException {
		PatientConnection con = new PatientConnection();
    	return con.createDBPatientAppointment(super.getUsername(),date,time,doctor);
	}
}
