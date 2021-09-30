package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mainpackage.Admin;


@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	String surname = request.getParameter("surname");
    	
    	Admin admin = new Admin(username,password,name,surname);
    	admin.setUserType("Admin");
    	
    	
    	try {
			boolean register = admin.addadmin();
			PrintWriter out = response.getWriter();
			if(register) {
				out.println("Succesful registration");
				response.sendRedirect("/adminProfile");
			}
			else {
				out.println("Problem with the registration");
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
