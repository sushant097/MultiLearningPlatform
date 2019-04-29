<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Student</title>
</head>
<body>
		<div>
			<form:form action="registerStudent" method="post" modelAttribute="student">
				<table>
					<form:hidden path="id"/>
				<tr>
					<td>Name: </td><td><form:input id="name" path="name" type="text" placeholder="name"/></td>
				</tr>
				<tr>
					<td>Email: </td><td><form:input id="email" path="email" type="text" placeholder="Email"/></td>
				</tr>
				<tr>
					<td>Password </td><td><form:input id="password" path="password" type="password" placeholder="password"/></td>
				</tr>
				<tr>
					<td>University Name </td><td><form:input  path="uniName" type="text" value="TU" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Type</td><td><form:input  path="type" value="student" type="text"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Faculty </td>
					<td>
						<form:select id="faculty" path="faculty">
							<option selected hidden>BE Faculty</option>
							<form:option value="BCE"></form:option>
							<form:option value="BEL"></form:option>
							<form:option value="BCT"></form:option>
							<form:option value="BEX"></form:option>
							<form:option value="BME"></form:option>
							<form:option value="B.Arch"></form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Semester </td>
					<td>
						<form:select id="semester" path="semester">
							<option selected hidden>BE Semester</option>
							<form:option value="1"></form:option>
							<form:option value="2"></form:option>
							<form:option value="3"></form:option>
							<form:option value="4"></form:option>
							<form:option value="5"></form:option>
							<form:option value="6"></form:option>
							<form:option value="7"></form:option>
							<form:option value="8"></form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>College Name </td>
					<td>
						<form:select id="colzName" path="colzName">
							<option selected hidden >College Under TU</option>
							<form:option value="Central Campus-Pulchowk"></form:option>
							<form:option value="Thapathali Campus"></form:option>
							<form:option value="Kathmandu Engineering College"></form:option>
							<form:option value="Kantipur Engineering College"></form:option>
							<form:option value="Advanced College of Engineering & Management"></form:option>
							<form:option value="National College of Engineering"></form:option>
							<form:option value="Himalaya Engineering College"></form:option>
							<form:option value="Kathford College of Engineering & Management"></form:option>
							<form:option value="Khwopa College of Engineering"></form:option>
							<form:option value="Sagarmatha Engineering College"></form:option>
							<form:option value="Lalitpur Engineering College"></form:option>
							<form:option value="Janakpur Engineering College"></form:option>
						</form:select>
					</td>
			<!-- Only TU affiliated college are choosen for now -->
				</tr>
				<tr>
					<td colspan="2"><button type="submit">SignUp</button></td>
				</tr>
				
			</table>
		</form:form>
			
		</div>
</body>
</html>