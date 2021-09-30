package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Patient;


@WebServlet("/PatientRegisterServlet")
public class PatientRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	String surname = request.getParameter("surname");
    	int amka = Integer.parseInt(request.getParameter("AMKA"));
    	
    	Patient patient = new Patient(username,password,name,surname,amka);
    	patient.setUserType("Patient");
    	
    	
    	try {
			boolean register = patient.registration();
			PrintWriter out = response.getWriter();
			if(register) {
				out.println("Succesful registration");
			}
			else {
				out.println("Problem with the registration");
				response.sendRedirect("/index.html");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
	}

}
