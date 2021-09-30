package dbConnectionPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mainpackage.Admin;
import mainpackage.Doctor;
import mainpackage.Users;

public class DoctorConnection {
	private String url="jdbc:mysql://localhost:3306/medDB";
	private String DBusername = "root";
	private String DBpassword = "maverick7975";
	private Connection connection;
	private Statement statement;
	

	public Doctor findDBdoctor(Users user) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = "select * from doctors where usernameD='"+
				user.getUsername()+"'";
		
		ResultSet rs = statement.executeQuery(query);
		
		String specialty;
		
		//μου φέρνει την πρώτη γραμμη του πίνακα μιας και μια γραμμή θα είναι η απάντηση
		rs.next();
		specialty = rs.getString("specialty");
		
		rs.close();
		connection.close();
		
		return new Doctor(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),specialty);
		
	}


	public boolean registerDBdoctor(Doctor doctor,Admin admin) throws ClassNotFoundException, SQLException {
		UserConnection uCon = new UserConnection();
		uCon.registerDBuser(doctor);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = " INSERT INTO Doctors(specialty,usernameD,usernameA)\r\n"
				+ "	VALUES('"+doctor.getspecialty()+"','"+doctor.getUsername()+"','"+admin.getUsername()+"');";
		
		int update = statement.executeUpdate(query);

		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean deleteDBdoctor(String username) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "DELETE FROM Appointments WHERE usernameD='"+username+"'";
		String query2 = " DELETE FROM Doctors WHERE usernameD='"+username+"'";
		
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


	public ArrayList<String> getDBDoctorAppointmentHistory(String username) {
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

			String query = "select appointmentDate,appointmentTime,AMKA,FirstName,LastName\r\n"
					+ "from appointments,patients,users\r\n"
					+ "where appointments.usernameD='"+username+"' AND \r\n"
					+ "	appointments.appointmentDate >= CURDATE() AND \r\n"
					+ "	appointments.usernameP = patients.usernameP AND \r\n"
					+ "    appointments.usernameP = users.username;";
			
			ResultSet rs = statement.executeQuery(query);
			
			
			while(rs.next()) {
				appointmentHistory.add(rs.getString("appointmentDate"));
				appointmentHistory.add(rs.getString("appointmentTime"));
				appointmentHistory.add(rs.getString("AMKA"));
				appointmentHistory.add(rs.getString("FirstName"));
				appointmentHistory.add(rs.getString("LastName"));
			}
			
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return appointmentHistory;
	}


	public boolean cancelDBdoctorAppointment(Doctor doctor, String date, String time) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "DELETE FROM Appointments WHERE usernameD='"+doctor.getUsername()+"' AND appointmentDate='"+date+"' AND appointmentTime='"+time+"'";
		int update = statement.executeUpdate(query);
		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}


	public boolean createDBdoctorAppointment(Doctor doctor, String date, String time) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		String query = "INSERT INTO Appointments(appointmentDate,appointmentTime,usernameD)"
						+	" VALUES('"+date+"','"+time+"','"+doctor.getUsername()+"')";
		
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
