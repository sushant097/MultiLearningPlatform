<%@page import="com.project.spring.model.Discussion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CreateDiscussion</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

<style type="text/css">
	 .panel{
		border:0;
		
		/* box-shadow: none; */
	} 
</style>
</head>

<body>
	
	<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	<div class="container">
	
		<%	List<Discussion> listDiscussion = (List<Discussion>)request.getAttribute("listDiscussion");
			for(Discussion discussion:listDiscussion){
		%>
			<div class="panel panel-info">	
					<h3><a href="discussionWithAnswer?disId=<%=discussion.getDisId()+1000%>"><%=discussion.getTitle() %></a> </h3>
				
				<hr/>
				<span style="font-size:20px;" class="glyphicon glyphicon-user" ></span><strong style="font-size:25px;"><%=discussion.getName() %></strong>	
				<strong style="font-size:25px;float:right;clear:right;"><span style="font-size:20px;" class="glyphicon glyphicon-calendar" ></span>  <%=discussion.getDate() %></strong>	
				
				<br>
						
				 <span style="font-size:20px;color:green">Answers: <%=discussion.getNoOfAnswer() %></span>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<span style="text-align:center;background-color:#b8adb1;font-size:15px;font-color:white;"><%=Discussion.listToString(discussion.getRelatedTo())%></span>	
				<span style="font-size:20px;color:green;float:right;clear:right;">Vote: <%=discussion.getVote() %></span>
					
				
				<hr>
			</div>
				
		<% }%>
	
	</div>
	
	<%-- <div class="panel-body">
					${discussion.question}
				</div>	
				<div class="" style="">
					Ask By<strong>Sushant</strong>> of <h5>National College Of Engineering</h5>
					On <strong>2018/6/30</strong>
				</div> --%>
	
	
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>