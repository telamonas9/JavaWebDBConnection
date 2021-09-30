<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@	page import ="mainpackage.Doctor, 
					java.util.ArrayList,
					java.util.Date,
					java.text.SimpleDateFormat,
					java.util.Calendar,
					java.io.PrintWriter"
    	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Appointments</title>
</head>
<body>
	<h1 align="center">CREATE APPOINTMENT ENTRIES FOR THE NEXT MONTH</h1>
	
	
	<%	
		int month;
		if(request.getParameter("month") != null){
			month = Integer.parseInt(request.getParameter("month"));
		}
		else{
			month = (int) session.getAttribute("month");
		}
		session.setAttribute("month",month);
	
		Date date = new Date();
		date.setMonth(month -1);
		date.setDate(1);
		
		Calendar c = Calendar.getInstance();
		
		if(date.getMonth() <= c.get(Calendar.MONTH)){
			date = new Date(date.getYear() + 1,month - 1,1);
			
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		}

		
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String startMonth = formater.format(date);
		
		c.set(Calendar.MONTH, month + 1);
		date.setDate(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String endMonth = formater.format(date);
	
	%>
	<section> 
	    <form  action="DoctorMonthlyAvail">   
	    	<table align="center">
		    	<tr>
		    		<th>DATE:</th><th><input type="date" min="<% out.print(startMonth); %>" max="<% out.print(endMonth); %>"name="date"/></th>
		    	</tr>
		        <tr>
		    		<th>TIME:</th><th> <input type="time" name="time"/></th>
		    	</tr>
    			<tr>
    				<th></th><th><input type="submit" value="submit"> </th>
    			</tr>
	    	</table>  
	    </form>  
	</section>
	
	<section>
	
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
	</section>
</body>
</html>