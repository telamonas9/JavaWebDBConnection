<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	page import ="mainpackage.Doctor,
    					java.util.ArrayList"
    	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Board of Appointments</title>
</head>
<body>
	<h1 align="center">BOARD OF APPOINTMENTS</h1>
	<table>
	<tr>
		<th>DATE</th>
		<th>TIME</th>
		<th>AMKA</th>
		<th>PATIENT NAME</th>
		<th>PATIENT SURNAME</th>
	</tr>
	
	<%	
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		ArrayList<String> appointments = doctor.scheduledappointmets();
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