package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Doctor;


@WebServlet("/DoctorMonthlyAvail")
public class DoctorMonthlyAvail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
    	String time = request.getParameter("time");
    	
    	HttpSession session = request.getSession(); 
    	Doctor doctor = (Doctor) session.getAttribute("doctor");
    	
    	
	
	    try {
			boolean createdApp = doctor.createAppMonth(date,time);
			request.setAttribute("createdApp", createdApp);
			RequestDispatcher rd = request.getRequestDispatcher("doctorCreateApp.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
   

}
