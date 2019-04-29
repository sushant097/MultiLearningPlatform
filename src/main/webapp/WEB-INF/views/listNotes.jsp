<%@page import="com.project.spring.model.Upload"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Notes Available</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style>

 /* body {
  padding-top: 54px;
}

@media (min-width: 992px) {
  body {
    padding-top: 56px;
  }
} 
.pagination {
	margin-bottom: 15px;
} */
</style>
</head>
<body>
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>


	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-4">
			List Of Notes <small>Download Materials</small>
		</h1>

		<%
			if (request.getAttribute("listNote") != null) {
				List<Upload> list = (List<Upload>) request.getAttribute("listNote");
		%>

		<%
			for (Upload upload : list) {
		%>
		<div class="row">
			<div class="col-md-7">
				<a href="#"> <img class="thumbnail"
					src="images/background(500-300).png" alt="Engnotes.com">
				</a>
			</div>
			<div class="col-md-5">
				<h3><%=upload.getTitle()%></h3>
				<p>
					<%
						if (upload.getDescription().trim().length() > 20) {
									upload.getDescription().substring(0, 20);
								} else {
									upload.getDescription();
								}
					%>
				</p>
				<span class="glyphicon glyphicon-user"><%=upload.getName()%></span>
				<span>Related To: <%=Upload.listToString(upload.getRelatedTo())%></span><br>
				<a class="btn btn-primary"
					href="describeNote?uploadId=<%=upload.getUploadId()%>">Read
					More</a>
			</div>
		</div>
		<hr>
		<%
			}
			}
		%>
	</div>
	<div class="container">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="noteList/1">1</a></li>
				<li><a href="noteList/2">2</a></li>
				<li><a href="noteList/3">3</a></li>
				<li><a href="noteList/4">4</a></li>
				<li><a href="noteList/5">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<div class="footer">
		<div class="container">
			<p class="m-0 text-center text-white">
				Copyright &copy; <a href="homePage">Engnotes.com 2018</a>
			</p>
		</div>
		<!-- /.container -->
	</div>
	<jsp:include page="../../resources/header/footer.jsp"></jsp:include>
</body>
</html>