<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Teacher</title>
<link rel="stylesheet" href="resources/static/css/bootstrap.min.css">
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>

<style>
	.form-control{
		width: 500px;
	}
</style>
</head>
<body>
	<div class="container">
		<form:form action="registerTeacher" method="post"
			modelAttribute="teacher" id="myForm">

			<form:hidden path="id" />
				<div class="form-group">
					<label for="name">Name </label>
					<form:input id="name" path="name" placeholder="name" class="form-control" required="true" />
				
				</div>
				<div class="form-group">
					<label for="email" id="emaillabel">Email</label>
					 <form:input id="emailAddr" path="email" placeholder="Email" class="form-control" required="true"  />
				   <p id="emailError" style="display:none;"></p>
				</div>
				<div class="form-group">
					<label for="password">Password </label>
					<form:input id="password" path="password" type="password"
				placeholder="password" class="form-control" required="true" />
				<p id="passError" style="display:none;"></p>
				</div>
				<div class="form-group">
					<label for="conPassword">Confirm Password </label>
					<input id="conPassword" type="password"
				placeholder="password" class="form-control" required="true"/>
				</div>
				<div class="form-group">
					<label for="uniname">University Name </label>
					<form:input path="uniName" value="TU"
				 readonly="true" class="form-control" required="true" />
				
				</div>
				<div class="form-group">
					<label for="type">Type</label>
					 <form:input path="type"  value="teacher" readonly="true" class="form-control" required="true"  />
				
				</div>
				<div class="form-group">
					<label for="faculty">Faculty </label>
					
					
						<form:select id="faculty" path="faculty" multiple="true" class="form-control" required="true" >
				<option selected hidden>BE Faculty</option>
				<form:option value="BCE"></form:option>
				<form:option value="BEL"></form:option>
				<form:option value="BCT"></form:option>
				<form:option value="BEX"></form:option>
				<!-- Also to be add Industrial Engineering as Thapathali Campus -->
				<form:option value="BME"></form:option>
				<form:option value="B.Arch"></form:option>
			</form:select>
					
				</div>

			<div class="form-group">
				<label for="colzname">College Name </label>


				<form:select id="colzName" path="colzName" multiple="true"
					class="form-control" required="true">
					<option selected hidden>College Under TU</option>
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

				<!-- Only TU affiliated college are choosen for now -->

			</div>

			<button type="submit" class="btn btn-primary" onclick="validate()">SignUp</button>

		</form:form>
		
	</div>
	<script>
	
		$("#conPassword").keyup(function(){
			var pass1 = document.getElementById("conPassword").value;
			var pass2 = document.getElementById("password").value;
			if(pass1 == pass2){
				//$("passError").hide(1000);
				$("#passError").text("Password  Match!");
				$("#passError").css("color","green");
				$("#passError").show(1000);
			}
			else{
				$("#passError").text("Password Not Match!");
				$("#passError").css("color","red");
				$("#passError").css("font-size","15px");
				$("#passError").show(1000);
			}
			
		});
		$("#emailAddr").keyup(function(){
		
			var email = document.getElementById("emailAddr").value;
		 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
		  {
			 $("#emailError").text("Valid Email!");
			  $("#emailError").css("color","green");
			  $("#emailError").show(1000);
		    //return (true);
		  }else{
			  $("#emailError").show(1000);
			  $("#emailError").text("Invalid Email!");
			  $("#emailError").css("color","red");
			  
			 // return false;
			 
		  }
		    
		});
	</script>
	
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>