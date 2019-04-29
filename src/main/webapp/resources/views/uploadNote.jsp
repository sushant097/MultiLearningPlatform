<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/dropzone.css">
<script type="text/javascript" src="resources/static/js/dropzone.js"></script>
<style>

body{
	font-family: "Arial", sans-serif;
}

/* .dropzone{
	width: 300px;
	height: 300px;
	border: 2px dashed #ccc;
	color: #ccc;
	line-height: 300px;
	text-align : center;
}

.dropzone.dragover{
	border-color: #000;
	color: #000;/* black 
	background-color: #F3FFFF;
}  */

.form-control{
	width: 500px;
}
</style>
</head>
<body>
	<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	<div class="container">
		<h1>Upload File</h1>
		
	<form:form action="./saveUploadFile" method="post" modelAttribute="uploadFile" enctype="multipart/form-data">
		<form:hidden path="uploadId"/>
		<div class="form-group">
			<label>Title</label>
			<form:input path="title" class="form-control" required="true" placeholder="Short and appropriate Title"/>
		</div>
		<div class="form-group">
					<label>Category</label><br>
						<label class="radio-inline">
							<form:radiobutton path="category" name="category" value="video"  active="active"/> Video
						</label>
					
						<label class="radio-inline">
							<form:radiobutton path="category" name="category" value="note"  active="active"/>Note
						</label>	
		</div>
		<div class="form-group">		
						<label>Description</label>
					<form:textarea path="description" class="form-control" placeholder="Short And Sweet Description"/>
		</div>
		
			
			<button type="submit" class="btn btn-success btn-lg" value="Upload File">Upload File</button>
			
		
		
		</form:form>
	</div>
	
	
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>