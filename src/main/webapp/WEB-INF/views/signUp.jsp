<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp</title>
<link rel="stylesheet" type="text/css" href="/resources/static/css/bootstrap.min.css">
</head>
<body>

<div class="container">

	<div class="alert alert-success" role="alert">
              <strong>Well Done!</strong>You sucessfully register your data.
     </div>
     <div class="row">
     		<div class="col-md-6 col-sm-6 col-xs-6">
				<form:form action="registerStudent" method="post" modelAttribute="student">
						<table>
							<form:hidden path="id"/>
						<tr>
							<td>Name: </td><td><input  path="name"  placeholder="name"></td>
						</tr>
						<tr>
							<td>Email: </td><td><input path="email"  placeholder="Email"></td>
						</tr>
						<tr>
							<td>Password </td><td><input  path="password"  placeholder="password"></td>
						</tr>
						<tr>
							<td>University Name </td><td><input  path="uniName"  value="TU" readonly="readonly"></td>
						</tr>
						<tr>
							<td>Type</td><td><input  path="type" value="student"  readonly="readonly"></td>
						</tr>
						<tr>
							<td>Faculty </td>
							<td>
								<form:select id="faculty" path="faculty">
									<option selected hidden>BE Faculty</option>
									<option>BCE</option>
									<option>BEL</option>
									<option>BCT</option>
									<option>BEX</option>
									<option>BME</option>
									<option>B.Arch</option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>Semester </td>
							<td>
								<select id="semester" path="semester">
									<option selected hidden>BE Semester</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>College Name </td>
							<td>
								<select id="colzName" path="colzName">
									<option selected hidden>College Under TU</option>
									<option>Central Campus-Pulchowk</option>
									<option>Thapathali Campus</option>
									<option>Kathmandu Engineering College</option>
									<option>Kantipur Engineering College</option>
									<option>Advanced College of Engineering & Management</option>
									<option>National College of Engineering</option>
									<option>Himalaya Engineering College</option>
									<option>Kathford College of Engineering & Management</option>
									<option>Khwopa College of Engineering</option>
									<option>Sagarmatha Engineering College</option>
									<option>Lalitpur Engineering College</option>
									<option>Janakpur Engineering College</option>
								</select>
							</td>
					<!-- Only TU affiliated college are choosen for now -->
						</tr>
						<tr>
							<td colspan="2"><button type="submit">SignUp</button></td>
						</tr>
						
					</table>
				</form:form>
			</div>
			
			<div class="col-md-6 col-sm-6 col-xs-6">
				<table>
					<form:form action="registerTeacher" method="post" modelAttribute="teacher">
						<tr>	
							<td>Name: </td><td><input id="name" path="name" type="text" placeholder="name"></td>
						</tr>
						<tr>
							<td>Email: </td><td><input id="email" path="email" type="text" placeholder="Email"></td>
						</tr>
						<tr>
							<td>:Password </td><td><input id="password" path="password" type="password" placeholder="password"></td>
						</tr>
						<tr>
							<td>:University Name </td><td><input  path="uniName" type="text" value="TU" readonly="readonly"></td>
						</tr>
						<tr>
							<td>: Type</td><td><input  path="type" value="teacher" type="text"  readonly="readonly"></td>
						</tr>
						<tr>
							<td>:Faculty </td>
							<td>
								<select id="faculty" path="faculty" multiple>
									<option selected hidden>BE Faculty</option>
									<option>BCE</option>
									<option>BEL</option>
									<option>BCT</option>
									<option>BEX</option>
									<!-- Also to be add Industrial Engineering as Thapathali Campus -->
									<option>BME</option>
									<option>B.Arch</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td>College Name </td>
							<td>
								<select id="colzName" path="colzName" multiple>
									<option selected hidden>College Under TU</option>
									<option>Central Campus-Pulchowk</option>
									<option>Thapathali Campus</option>
									<option>Kathmandu Engineering College</option>
									<option>Kantipur Engineering College</option>
									<option>Advanced College of Engineering & Management</option>
									<option>National College of Engineering</option>
									<option>Himalaya Engineering College</option>
									<option>Kathford College of Engineering & Management</option>
									<option>Khwopa College of Engineering</option>
									<option>Sagarmatha Engineering College</option>
									<option>Lalitpur Engineering College</option>
									<option>Janakpur Engineering College</option>
								</select>
							</td>
					<!-- Only TU affiliated college are choosen for now -->
						</tr>
						
						<tr>
							<td colspan="2"><button type="submit">SignUp</button></td>
						</tr>
					</form:form>
					</table>
			 </div>
     </div>

</div>
</body>
</html>