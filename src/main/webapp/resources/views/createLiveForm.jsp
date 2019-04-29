<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Live Training Page</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	<div class="container">
		<form action="startLiveTraining" method="post">
			<div class="form-group">
				<label for="roomname">Input Room Name(You should create unique roomName)</label>
				<input type="text" class="form-control" id="roomname" name="roomname" value="${roomId}" disabled="disabled">
			</div>
			
			<button type="submit" class="btn btn-success btn-sm">Create LiveCast</button>
			
		</form>
	</div>
	
	
	
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>