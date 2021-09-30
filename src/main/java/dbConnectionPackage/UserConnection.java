package dbConnectionPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mainpackage.Doctor;
import mainpackage.Users;

public class UserConnection {
	private String url="jdbc:mysql://localhost:3306/medDB";
	private String DBusername = "root";
	private String DBpassword = "maverick7975";
	private Connection connection;
	private Statement statement;
	
	public Users findDBuser(String username,String password) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = "select * from Users where username='"+
				username+"' AND password='"+password+"';";
		
		ResultSet rs = statement.executeQuery(query);
		String userType;
		
		
		
		String name;
		String surname;
		
		//μου φέρνει την πρώτη γραμμη του πίνακα μιας και μια γραμμή θα είναι η απάντηση
		rs.next();
		userType = rs.getString("userType");
		name = rs.getString("FirstName"); 
		surname = rs.getString("LastName");
		
		rs.close();
		connection.close();
		Users user =  new Users(username,password,name,surname);
		user.setUserType(userType);
		
		return user;
		
	}
	
	public boolean registerDBuser(Users user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = " INSERT INTO Users(username,password,FirstName,LastName,userType)\r\n"
				+ "	VALUES('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getSurname()+"','"+user.getUserType()+"');";
		
		int update = statement.executeUpdate(query);

		connection.close();
		
		if(update != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean deleteDBuser(String username,String password) throws SQLException, ClassNotFoundException {
		
		DoctorConnection dCon = new DoctorConnection();
		PatientConnection pCon = new PatientConnection();
		
		Users user = findDBuser(username,password);
		if(user.getUserType().equals("Patient")) {
			pCon.deleteDBpatient(username);
		}
		else if(user.getUserType().equals("Doctor")) {
			dCon.deleteDBdoctor(username);
		}
		
		
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		
		
		String query = " DELETE FROM Users WHERE username='"+user.getUsername()+"' AND password='"+user.getPassword()+"'";
		
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
