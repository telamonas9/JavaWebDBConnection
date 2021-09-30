package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mainpackage.Patient;


@WebServlet("/patientProfile")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
   
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	
    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession(); 
    	Patient patient = (Patient) session.getAttribute("patient");
		
		out.println("<table align=\"center\">\r\n"
				+ "<tr>\r\n"
				+ "	<th>NAME:</th><th>"+patient.getName()+"</th>\r\n"
				+ "</tr>\r\n"
				+ "   <tr>\r\n"
				+ "	<th>SURNAME:</th><th>"+patient.getSurname()+"</th>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<th>AMKA :</th><th>"+patient.getAMKA()+"</th>\r\n"
				+ "</tr>\r\n"
				+ "</table> ");
			
		out.println("<section>\r\n"
				+ "	 <h1>FUNCTIONS OF THE APP</h1>\r\n"
				+ "	 <a  href=\"patientAppointmentH.jsp\"><button type=\"button\" >APPOINTMENT HISTORY</button></a>\r\n"
				+ "<form action=\"patientAvailableApp.jsp\">\r\n"
				+ "	<h3>CHOOSE DOCTOR SPECIALTY FOR APPOINTMENT BOOKING</h3>\r\n"
				+ "	<table>\r\n"
				+ "		<td>\r\n"
				+ "			 <select  name=\"specialty\">\r\n"
				+ "				  <option value=\"Pathologos\">Pathologist</option>\r\n"
				+ "				  <option value=\"Ofthalmiatros\">Ophthalmologist</option>\r\n"
				+ "				  <option value=\"Orthopedikos\">Orthopedic </option>\r\n"
				+ "				</select>\r\n"
				+ "		</td>\r\n"
				+ "		<td> <input type=\"submit\" value=\"submit\"></td>\r\n"
				+ "	</table>\r\n"
				+ "</form>	"
				+ "  <a  href=\"patientCancelApp.html\"><button type=\"button\">CANCEL AN APPOINTMENT</button></a>\r\n"
				+ "</section>");
    	
    }

    
	

}
