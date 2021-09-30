package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import mainpackage.Doctor;


@WebServlet("/DoctorCancelAppServlet")
public class DoctorCancelAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
    	String time = request.getParameter("time");
    	
    	HttpSession session = request.getSession(); 
    	Doctor doctor = (Doctor) session.getAttribute("doctor");
    	
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DATE, 2);
    	Date afterThreeDays = c.getTime();
    	
    	Date appDate;
		try {
			appDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			
			if(appDate.after(afterThreeDays)) {	
	    		try {
	    			boolean cancel = doctor.cancelAppointment(date,time);
	    			PrintWriter out = response.getWriter();
	    			if(cancel) {
	    				out.println("Succesful cancelation");
	    			}
	    			else {
	    				out.println("Problem with the cancelation");
	    				//response.sendRedirect("/index.html");
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
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
    	
    	PrintWriter out = response.getWriter();
    	out.println(date);
    	out.println(time);
	}

}
