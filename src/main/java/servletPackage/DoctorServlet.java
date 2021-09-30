package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Doctor;


@WebServlet("/doctorProfile")
public class DoctorServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	
	    	PrintWriter out = response.getWriter();

	    	HttpSession session = request.getSession(); 
	    	Doctor doctor = (Doctor) session.getAttribute("doctor");
			
			out.println("<table align=\"center\">\r\n"
					+ "<tr>\r\n"
					+ "	<th>NAME:</th><th>"+doctor.getName()+"</th>\r\n"
					+ "</tr>\r\n"
					+ "   <tr>\r\n"
					+ "	<th>SURNAME:</th><th>"+doctor.getSurname()+"</th>\r\n"
					+ "</tr>\r\n"
					+ "<tr>\r\n"
					+ "	<th>Specialty:</th><th>"+doctor.getspecialty()+"</th>\r\n"
					+ "</tr>\r\n"
					+ "</table> ");
			
			out.println("<section>\r\n"
					+ "<form action=\"doctorCreateApp.jsp\">\r\n"
					+ "	<h3>CREATE APPOINTMENTS FOR THE NEXT MONTH</h3>\r\n"
					+ "	<table>\r\n"
					+ "		<td>\r\n"
					+ "			 <select  name=\"month\">\r\n"
					+ "				  <option value=\"1\">January</option>\r\n"
					+ "				  <option value=\"2\">February</option>\r\n"
					+ "				  <option value=\"3\">March </option>\r\n"
					+ "				  <option value=\"4\">April</option>\r\n"
					+ "				  <option value=\"5\">May </option>\r\n"
					+ "				  <option value=\"6\">June</option>\r\n"
					+ "				  <option value=\"7\">July</option>\r\n"
					+ "				  <option value=\"8\">August</option>\r\n"
					+ "				  <option value=\"9\">September</option>\r\n"
					+ "				  <option value=\"10\">October </option>\r\n"
					+ "				  <option value=\"11\">November</option>\r\n"
					+ "				  <option value=\"12\">December</option>\r\n"
					+ "				</select>\r\n"
					+ "		</td>\r\n"
					+ "		<td> <input type=\"submit\" value=\"submit\"></td>\r\n"
					+ "	</table>\r\n"
					+ "</form>	"
					+ "  <a  href=\"doctorBofAppointments.jsp\"><button type=\"button\">BOARD OF ALL YOUR APPOINTMENTS</button></a>\r\n"
					+ "  <a  href=\"doctorCancelApp.html\"><button type=\"button\">CANCEL AN APPOINTMENT</button></a>\r\n"
					+ "</section>");
	    	
	    }
}
