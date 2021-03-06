<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Discussion</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<!-- <script type="text/javascript" src="resources/static/js/ckeditor.js"></script> -->
<script type="text/javascript" src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>
</head>
<body>

<jsp:include page="../../resources/header/header.jsp"/>
	
	<%
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
				response.setHeader("Pragma", "no-cache"); //HTTP 1.0
				response.setHeader("Expires", "0");
	%>
	
	 <div class="container">
	 	
	 	<h1>Create Discussion</h1>
		<form:form action="saveDiscussion" method="post" modelAttribute="discussionObj">
				
				
				
					<div class="form-group">
						<label for="inputTitle">Title</label>
						<form:input path="title" type="text" style="width:700px;" class="form-control" placeholder="Question Title. Must be Short" focus="true" required="required"/>
					</div>
						
					<div class="form-group">
						<label for="Question">Question</label>
						<form:textarea path="question"  cols="80" id="editor1" name="editor1" rows="10" class="form-control" placeholder="Be specific to ask Question. With Brief Explanation." required="required" onkeypress="showtext()"/>
							
							<script>
								// Replace the <textarea id="editor1"> with a CKEditor
								CKEDITOR.replace('editor1',{
									height:260,
									width:700
								});
								
								
							</script>
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
					<br>
					<button type="submit" class="btn btn-primary">Post Question</button>
		</form:form>
	 	
	 </div>
    
 
 <script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>