<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Account</title>
	<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<!-- Custom CSS -->

	<style type="text/css">
	
		body{
			background-color:87cefa;
			align:center;
			margin:auto;
		}
		.form-control{
			width:450px;
		}
	</style>
	
</head>
<body >

		<%
			if(null !=request.getAttribute("error"))
			{
		%>		<div class="container">
					<div class="alert alert-warning alert-dismissible" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${error}
					</div>
				</div>
				
				
	    <%
			}
			if(session.getAttribute("email")!=null){
				response.sendRedirect("homePage");
			}
		%>
		
		<%if(request.getAttribute("login")!=null){ %>	
			   <div class="container" style="margin:auto;">
		   		
			   		 <form:form action="userLogin" method="post" modelAttribute="login" >
			    	
			    		<div class="form-group">
			    			<label for="Email">Email</label>
			    			<form:input path="email" type="text" placeholder="example:john@gmail.com" class="form-control" required="required"/>
			    		</div>	
			    		<div class="form-group">
			    			<label for="Password">Password</label>
			    			<form:input path="password" type="password" class="form-control" required="required"/>
			    			
			    		</div>
			    		<div class="checkbox">
			    			<label>
			    				<input type="checkbox"> Remember Me
			    			</label>
			    		</div>
			    		
			    		<button type="submit" value="login" class="btn btn-primary"> Login</button>	
			    </form:form>
			 <%} %>
			 	<p class="bg-warning" style="width:450px;font-size:20px;"><strong>Register! First</strong> before Login</p>
			 		<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">SignUp</button>

				<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				  <div class="modal-dialog modal-sm" role="document">
				    <div class="modal-content">
				      <p class="bg-success"><a href="signupStudent">Register Student</a></p>
				      <p class="bg-success"><a href="signupTeacher">Register Teacher</a></p>
				    </div>
				  </div>
				</div>
		   </div>
   <script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
   <script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   
   <!-- Place bootstrap.min.js after jquery -->
    <script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
   
</body>

</html>