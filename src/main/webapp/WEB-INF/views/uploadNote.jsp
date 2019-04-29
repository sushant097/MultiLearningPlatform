<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<!-- <link rel="stylesheet" type="text/css" href="resources/static/css/dropzone.css">
<script type="text/javascript" src="resources/static/js/dropzone.js"></script> -->
<style>

body{
	font-family: "Arial", sans-serif;
}


.form-control{
	width: 500px;
}
</style>
</head>
<body>
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	
	<div class="container">
	<p>${errorUpload}</p>
		<h1>Upload File</h1>
		
	<form:form action="./saveUploadFile" method="post" modelAttribute="uploadFile" enctype="multipart/form-data" >
		<form:hidden path="uploadId"/>
		<div class="form-group">
			<label>Title</label>
			<form:input path="title" class="form-control" required="true" placeholder="Short and appropriate Title"/>
		</div>
		<div class="form-group">		
						<label>Description</label>
					<form:textarea path="description" class="form-control" placeholder="Short And Sweet Description"/>
		</div>
		
		<div class="form-group">
			<label for="tagSelect">At most 3 tags</label>
							<form:select path="relatedTo" class="form-control" multiple="true">
								<option selected hidden>Tags</option>
								<form:option value="BCT"> </form:option>
								<form:option value="BEX"> </form:option>
								<form:option value="BCE"> </form:option>
								<form:option value="BEL"> </form:option>
								<form:option value="BArch"> </form:option>
							</form:select>
		</div>
		<div class="form-group">
			<label> Input File</label>
				<input type="file"  class="form-control" name="file">
		</div>
	
			<button type="submit" class="btn btn-success btn-lg" value="Upload File">Upload File</button>
			
		
		
		</form:form>
	</div>
	
	
<jsp:include page="../../resources/header/footer.jsp"></jsp:include>
</body>
</html>