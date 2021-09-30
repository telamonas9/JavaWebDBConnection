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
import mainpackage.Admin;
import mainpackage.Doctor;


@WebServlet("/DoctorRegisterServlet")
public class DoctorRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	String surname = request.getParameter("surname");
    	String specialty = request.getParameter("specialty");
    	
    	Doctor doctor = new Doctor(username,password,name,surname,specialty);
    	doctor.setUserType("Doctor");
    	HttpSession session = request.getSession();
    	Admin admin = (Admin) session.getAttribute("admin");
    	
    	
    	try {
			boolean register = admin.addDoc(doctor);
			PrintWriter out = response.getWriter();
			if(register) {
				out.println("Succesful registration");
			}
			else {
				out.println("Problem with the registration");
				response.sendRedirect("/adminProfile");
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
