package dbConnectionPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mainpackage.Patient;
import mainpackage.Users;

public class PatientConnection {
	private String url="jdbc:mysql://localhost:3306/medDB";
	private String DBusername = "root";
	private String DBpassword = "maverick7975";
	private Connection connection;
	private Statement statement;
	

	public Patient findDBpatient(Users user) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = "select * from patients where usernameP='"+
				user.getUsername()+"'";
		
		ResultSet rs = statement.executeQuery(query);
		int amka;
		
		//μου φέρνει την πρώτη γραμμη του πίνακα μιας και μια γραμμή θα είναι η απάντηση
		rs.next();
		amka = Integer.parseInt(rs.getString("AMKA"));
		
		rs.close();
		connection.close();
		
		return new Patient(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),amka);
		
	}
	
	
	public ArrayList<String> getDBPatientAppointmentHistory(String username){
		ArrayList<String> appointmentHistory = new ArrayList<String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection= DriverManager.getConnection(url,DBusername,DBpassword);
			statement = connection.createStatement();

			String query = "select appointmentDate, appointmentTime, FirstName, LastName, doctors.specialty\r\n"
					+ "from appointments,doctors,users\r\n"
					+ "where appointments.usernameP='"+username+"' AND \r\n"
					+ "appointments.appointmentDate <= CURDATE() AND \r\n"
					+ "	appointments.usernameD = doctors.usernameD AND \r\n"
					+ "    appointments.usernameD = users.username;";
			
			ResultSet rs = statement.executeQuery(query);
			
			
			while(rs.next()) {
				appointmentHistory.add(rs.getString("appointmentDate"));
				appointmentHistory.add(rs.getString("appointmentTime"));
				appointmentHistory.add(rs.getString("FirstName"));
				appointmentHistory.add(rs.getString("LastName"));
				appointmentHistory.add(rs.getString("doctors.specialty"));
			}
			
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return appointmentHistory;
	}


	public boolean registerDBpatient(Patient patient) throws ClassNotFoundException, SQLException {
		UserConnection uCon = new UserConnection();
		uCon.registerDBuser(patient);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = " INSERT INTO Patients(AMKA,usernameP)\r\n"
				+ "	VALUES('"+patient.getAMKA()+"','"+patient.getUsername()+"');";
		
		int update = statement.executeUpdate(query);

		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}


	public boolean deleteDBpatient(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "DELETE FROM Appointments WHERE usernameP='"+username+"'";
		String query2 = " DELETE FROM Patients WHERE usernameP='"+username+"'";
		
		int update = statement.executeUpdate(query);
		update = statement.executeUpdate(query2);
		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
		
	}


	public boolean cancelDBpatientAppointment(Patient patient, String date, String time) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "DELETE FROM Appointments WHERE usernameP='"+patient.getUsername()+"' AND appointmentDate='"+date+"' AND appointmentTime='"+time+"'";
		int update = statement.executeUpdate(query);
		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}


	public ArrayList<String> availableDBpatientAppointments(String specialty) {
		ArrayList<String> availableApp = new ArrayList<String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection= DriverManager.getConnection(url,DBusername,DBpassword);
			statement = connection.createStatement();

			String query = "select appointmentDate, appointmentTime, FirstName, LastName,appointments.usernameD\r\n"
					+ "from appointments,doctors,users\r\n"
					+ "where appointments.usernameP IS NULL AND \r\n"
					+ "appointments.appointmentDate >= CURDATE() AND \r\n"
					+ "	appointments.usernameD = doctors.usernameD AND \r\n"
					+ "    appointments.usernameD = users.username;";
			
			ResultSet rs = statement.executeQuery(query);
			
			
			while(rs.next()) {
				availableApp.add(rs.getString("appointmentDate"));
				availableApp.add(rs.getString("appointmentTime"));
				availableApp.add(rs.getString("FirstName"));
				availableApp.add(rs.getString("LastName"));
				availableApp.add(rs.getString("usernameD"));
			}
			
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return availableApp;
	}


	public boolean createDBPatientAppointment(String username, String date, String time,String doctor) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "UPDATE Appointments"
						+	" SET usernameP='"+username+"' "
						+  "WHERE appointments.usernameD = '"+doctor+"' AND"
						+  "	appointments.appointmentDate =  '"+date+"' AND"
						+  "	appointments.appointmentTime =  '"+time+"'";
		
		int update = statement.executeUpdate(query);
		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
