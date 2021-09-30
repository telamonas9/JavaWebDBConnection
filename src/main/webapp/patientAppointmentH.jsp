<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@	page import ="mainpackage.Patient,
    					java.util.ArrayList"
    	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Profile</title>
</head>
<body>
	<h1 align="center">APPOINTMENT HISTORY</h1>
	<table>
	<tr>
		<th>DATE</th>
		<th>TIME</th>
		<th>DOCTOR NAME</th>
		<th>DOCTOR SURNAME</th>
		<th>SPECIALTY</th>
	</tr>
	
	<%	
		Patient patient = (Patient) session.getAttribute("patient");
		ArrayList<String> appointments = patient.appointmentshistory();
		String line;
		out.println("<tr>");
		for(int i=0; i<appointments.size(); i++){
			line = "<td>";
			line += appointments.get(i);
			line +="</td>";
			out.println(line);
			if((i+1)%5 == 0){
				out.println("</tr>");
				if((i+1)!= appointments.size()){
					out.println("<tr>");
				}
			}
			
		}
		%>
	
	</table>
</body>
</html>