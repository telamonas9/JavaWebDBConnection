package dbConnectionPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import mainpackage.Admin;

public class AdminConnection {
	private String url="jdbc:mysql://localhost:3306/medDB";
	private String DBusername = "root";
	private String DBpassword = "maverick7975";
	private Connection connection;
	private Statement statement;
	
	public boolean registerDBAdmin(Admin admin) throws ClassNotFoundException, SQLException {
		
		UserConnection uCon = new UserConnection();
		uCon.registerDBuser(admin);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection(url,DBusername,DBpassword);
		statement = connection.createStatement();
		
		String query = " INSERT INTO Admins(usernameA)\r\n"
				+ "	VALUES('"+admin.getUsername()+"');";
		
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
