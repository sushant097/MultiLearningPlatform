<%@page import="com.project.spring.model.BroadCast"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail Live Training</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style>
	.center-block {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
</style>
</head>
<body>
	
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	<div class="container">
		<%if(request.getAttribute("broadcastDetail") != null){
			BroadCast broadDetail = (BroadCast)request.getAttribute("broadcastDetail");
	     %>
		<div class="center-block">
			<dl class="dl-horizontal">
				<dt>Title</dt>
					<dd><span style="font-size:20px;"  class="bg-primary"><%=broadDetail.getTitle()%></span></dd>
				<br>
				<dt>Description</dt>
					<dd><div class="well"><%=broadDetail.getDescription()%></div></dd>
				<br><dt>RelatedTo</dt>
					<dd><span style="font-size:25px;" class="bg-info"><%=BroadCast.listToString(broadDetail.getRelatedTo()) %></span></dd>
				<dt>By</dt>
					<dd><span class="glyphicon glyphicon-user" style="font-size:25px;"><%=broadDetail.getTeacherName()%></span></dd>
					<br>
				
			</dl>
			<form action="joinLiveTraining" method="post">
				<input type="hidden" name="broadCastId" value="<%=broadDetail.getBroadcastId()%>"/>
				<button type="submit" class="btn btn-success">Join Live Training</button>			
			</form>
		</div>
		<%} %>
	</div>
	
	
	
	<jsp:include page="../../resources/header/footer.jsp"></jsp:include>
</body>
</html>