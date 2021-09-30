package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dbConnectionPackage.UserConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Admin;
import mainpackage.Doctor;


@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");

    	HttpSession session = request.getSession();
    	Admin admin = (Admin) session.getAttribute("admin");
    	
    	try {
			boolean register = admin.deleteuser(username,password);
			PrintWriter out = response.getWriter();
			if(register) {
				out.println("Succesful deletion");
			}
			else {
				out.println("Problem with the deletion");
				response.sendRedirect("/adminProfile");
			}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
