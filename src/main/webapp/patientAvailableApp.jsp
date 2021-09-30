<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@	page import ="mainpackage.Patient,
    					java.util.ArrayList"
    	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available appointments</title>
</head>
<body>
	<h1 align="center">APPOINTMENTS AVAILABLE FOR BOOKING</h1>
	<table>
	<tr>
		<th>DATE</th>
		<th>TIME</th>
		<th>DOCTOR NAME</th>
		<th>DOCTOR SURNAME</th>
	</tr>
	
	<%	

		String specialty = request.getParameter("specialty");
		Patient patient = (Patient) session.getAttribute("patient");
		ArrayList<String> availableApp = patient.availableApp(specialty);
		String[][] appointments = new String[availableApp.size()/5][5];
		
		int z=0;
		for(int i=0; i< availableApp.size()/5; i++){
			for(int j=0; j<5; j++){
				appointments[i][j] = availableApp.get(z);
				z++;
			}
		}
		
		String box;
		String line;
		
		for(int i=0; i< availableApp.size()/5; i++){
			line = "<tr><form action=\"PatientCreateAppServlet\">";
			for(int j=0; j<5; j++){
				if(j==0){
					box = "<td><input type=\"hidden\" name=\"date\" value=\""+appointments[i][j]+"\">";
				}
				else if(j==1){
					box = "<td><input type=\"hidden\" name=\"time\" value=\""+appointments[i][j]+"\">";
				}
				else if(j==2){
					box = "<td><input type=\"hidden\" name=\"doctor\" value=\""+appointments[i][j+2]+"\">";
				}
				else if(j==3){
					box = "<td>";
				}
				else{
					box = "<td><input type=\"submit\" value=\"BOOK\">";
					line += box;
					break;
				}

				box += appointments[i][j];
				box += "</td>";
				line += box;
				
			}
			line += "</form></tr>";
			out.println(line);
		}
		
	
	%>
	<%		
		
		if(request.getAttribute("createdApp") != null){
			boolean createdApp = (boolean) request.getAttribute("createdApp");
			if(createdApp){
				out.println("<h3>Your appointment was created</h3>");
			}
			else{
				out.println("<h3>Problem with the creation of the appointment</h3>");
			}
		}
		
		
		
	%>

	
	
</body>
</html>