package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mainpackage.Admin;


@WebServlet("/adminProfile")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	
    	PrintWriter out = response.getWriter();
		
    	Admin admin = (Admin) request.getAttribute("admin");
		
		out.println("<table align=\"center\">\r\n"
				+ "<tr>\r\n"
				+ "	<th>NAME:</th><th>"+admin.getName()+"</th>\r\n"
				+ "</tr>\r\n"
				+ "   <tr>\r\n"
				+ "	<th>SURNAME:</th><th>"+admin.getSurname()+"</th>\r\n"
				+ "</tr>\r\n"
				+ "</table> ");
		
		out.print("<section>\r\n"
				+ "	 <h1>ADMIN</h1>\r\n"
				+ "	 <a  href=\"DoctorRegister.html\"><button type=\"button\" >ADD DOCTOR</button></a>\r\n"
				+ "  <a  href=\"AdminRegister.html\"><button type=\"button\">ADD ADMIN</button></a>\r\n"
				+ "  <a  href=\"UserDeletion.html\"><button type=\"button\">DELETE USER</button></a>\r\n"
				+ "</section>");
    	
    }
}
