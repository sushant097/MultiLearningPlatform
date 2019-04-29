
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
</head>
<body>

	<%
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
				response.setHeader("Pragma", "no-cache"); //HTTP 1.0
				response.setHeader("Expires", "0");
	%>
	<% 
		if(session.getAttribute("email")==null){
			response.sendRedirect("login");
		}
	%> 
	 <nav class="navbar navbar-inverse">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Brand</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active"><a href="homePage">Home <span class="sr-only">(current)</span></a></li>
		        <li><a href="createDiscussion">Create Discussion</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="discussion">See discussion</a></li>
		            <li><a href="${liveCastUrl}">${liveCast}</a></li>
		            <li><a href="uploadFile">Upload File</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">Separated link</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="playVideo">PlayVideo</a></li>
		          </ul>
		        </li>
		      </ul>
		      <form class="navbar-form navbar-left">
		        <div class="form-group">
		          <input type="text" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" class="btn btn-default">Submit</button>
		      </form>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="#">Link</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span><%=(String)session.getAttribute("name")%> <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="login">Login</a></li>
		            <li><a href="logout">Logout</a></li>
		            <!-- <li><a href="#">Something else here</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">Separated link</a></li> -->
		          </ul>
		        </li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
	</nav>
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
	
		%>
			<div class="jumbotron">
				  <div class="container">
				  	<h1>Welcome To Engineering Note</h1>
				    <p>	This project "Multi-Learning Platform" specially designed for learning platform to the engineering students of TU.
				     This project will build in JAVA programming language.
				     This project main motive is to make live online training by one teacher to many students at a time. 
				     Besides this, it contains other feature such as adding disscussion in forum, upload or download materials (notes, videos), play video etc.
				      It contains live video streaming with websocket in which continuously images of canvas in host computer (teacher) is send to server and server multicast to the students in that particular session. 
				      By websocket we can configure for the video tranmission with low latency. We will introduce the concept of thread for the parallely video continuous transmission to the multiple students at a time. 
				      This project is very unique and choose to solve the real time problem. It can be useful to the students where they interact with teacher and able to learn different things in a single platform. 
				    </p>
				  </div>
			</div>
			
		
	
			
			<div class="container">
				<div ng-app="" ng-init="name='${name}'">
					<h1>Welcome To Home Page</h1>
					<h2>Welcome <b> {{name| uppercase}} </b>to login page</h2>
				</div>
			</div>
			
			<br>
			
			
	
		
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>

</body>
</html>