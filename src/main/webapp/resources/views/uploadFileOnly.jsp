<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Your File</title>

<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/dropzone.css">
<script type="text/javascript" src="resources/static/js/dropzone.js"></script>
</head>
<body>
		<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	<div class="container">
		
		
		
  		
		  <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		  Upload File
		</button>
		  		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Upload File</h4>
		      </div>
		      <div class="modal-body">
		      
		        <form action="getFiles" method="post" class="dropzone"></form>
				 <div class="fallback">
		    		<input name="file" type="file" multiple="multiple" />
		    		
		  		</div>
		  		
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='homePage'">Cancel</button>
		        <button type="button" class="btn btn-primary" onclick="show();" >Save changes</button>
		        <p class="bg-danger">If you cancel then, previous record lost Unexpectedly!</p>
		      </div>
		    </div>
		  </div>
		</div>
		<p id="showMessage"></p>
		<script>
			function show(){
				document.getElementById("showMessage").innerText = "<span class='bg-success' style='font-size:30px;'>Sucessfully Uploaad File!</span>";
			}
		</script>
	</div>
	
	
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>