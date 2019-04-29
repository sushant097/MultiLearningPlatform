<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1px">
			<tr>
				<th>No</th>
				<th>Name</th>
				<th>Email</th>
				<th>Password</th>
				<th>University Name</th>
				<th>College Name</th>
				
				<th>Faculty</th>
				<th>Semester</th> 
				<th>Entry Date</th>
			</tr>
			<c:forEach var="temp" items="${list}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${temp.name}</td>
					<td>${temp.email}</td>
					<td>${temp.password}</td>
					<td>${temp.uniName}</td>
					<td>${temp.colzName}</td>
					<td>${temp.faculty}</td>
					<td>${temp.semester}</td>
					<td>${temp.date}</td>
				
				</tr>
			
			</c:forEach>
		</table>
	
</body>
</html>