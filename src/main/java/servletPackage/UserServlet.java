package servletPackage;

import java.io.IOException;
import java.sql.SQLException;

import dbConnectionPackage.DoctorConnection;
import dbConnectionPackage.PatientConnection;
import dbConnectionPackage.UserConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Admin;
import mainpackage.Doctor;
import mainpackage.Patient;
import mainpackage.Users;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	UserConnection userCon = new UserConnection();
    	PatientConnection patientCon = new PatientConnection();
    	DoctorConnection doctorCon = new DoctorConnection();
    	 
    	HttpSession session = request.getSession(); 
    	
    	Users user;
    	String name;
    	String surname;
		
    	try {
			user = userCon.findDBuser(username, password);
			String userType = user.getUserType();
			name = user.getName();
			surname = user.getSurname();
			if(userType.equals("Admin")) {
				Admin admin = new Admin(username,password,name,surname);
				request.setAttribute("admin", admin);
				session.setAttribute("admin", admin);
				RequestDispatcher rd = request.getRequestDispatcher("adminProfile");
				rd.forward(request, response);
			}
			else if(userType.equals("Doctor")) {
				Doctor doctor = doctorCon.findDBdoctor(user);
				request.setAttribute("doctor", doctor);
				session.setAttribute("doctor", doctor);
				RequestDispatcher rd = request.getRequestDispatcher("doctorProfile");
				rd.forward(request, response);
				
			}
			else {
				Patient patient = patientCon.findDBpatient(user);
				request.setAttribute("patient", patient);
				session.setAttribute("patient", patient);
				RequestDispatcher rd = request.getRequestDispatcher("patientProfile");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

	}

}
