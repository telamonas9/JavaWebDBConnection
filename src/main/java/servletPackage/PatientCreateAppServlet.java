package servletPackage;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Patient;


@WebServlet("/PatientCreateAppServlet")
public class PatientCreateAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
    	String time = request.getParameter("time");
    	String doctor = request.getParameter("doctor");
    	
    	HttpSession session = request.getSession(); 
    	Patient patient = (Patient) session.getAttribute("patient");
    	
    	
	
	    try {
			boolean createdApp = patient.createApp(date,time,doctor);
			request.setAttribute("createdApp", createdApp);
			RequestDispatcher rd = request.getRequestDispatcher("patientAvailableApp.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
  
}
