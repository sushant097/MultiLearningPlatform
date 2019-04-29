<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Training Available</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style>
	.panel panel-info{
		border:0px;
	}
</style>
</head>
<body>
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	
	<div class="container">
		<div class="" style="width:800px; margin:0 auto;">
		
			
			<!-- Centered Content -->
			<%if(request.getAttribute("listTrain")!=null){ %>
			<h1> List of Training Available!</h1>
			<c:forEach var="detail" items="${listTrain}">
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<a href="showDetailLiveTraining?broadCastId=${detail.getBroadcastId()}" title="Click to See detail about Live Training">${detail.getTitle()}</a>
					</div>
				</div>
				<hr>
				<br>
			</c:forEach>
			<%}else{ %>
				<div class="alert alert-info">
				  <strong>OOPS!</strong> No any Live Training Available Yet.
				</div>
			
			<%} %>
		</div>
	</div>

</body>
</html>