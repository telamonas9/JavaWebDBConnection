package mainpackage;

import java.sql.SQLException;

import dbConnectionPackage.AdminConnection;
import dbConnectionPackage.DoctorConnection;
import dbConnectionPackage.UserConnection;

public class Admin extends Users{

	public Admin(String username,String password, String name, String surname) {
		super(username, password , name, surname);
		
	}
	
 public boolean addDoc(Doctor doctor) throws ClassNotFoundException, SQLException
 {
	 DoctorConnection doCon = new DoctorConnection();
	 return doCon.registerDBdoctor(doctor,this);
 }
 
 public void viewusers()
 {
	 System.out.println("View all register users");
 }
 
 public boolean deleteuser(String username,String password) throws ClassNotFoundException, SQLException
 {
	 UserConnection uCon = new UserConnection();
	 return uCon.deleteDBuser(username,password);
 }

 public boolean addadmin() throws ClassNotFoundException, SQLException
 {
	 AdminConnection adCon = new AdminConnection();
	 return adCon.registerDBAdmin(this);
	 
 }

}

