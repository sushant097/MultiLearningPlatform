<%@page import="com.project.spring.model.Discussion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discussion With Answer</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">

<script type="text/javascript" src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

	<style>
		.panel{
			border:0px;
			box-shadow:none;
		}
	</style>
</head>
<body>
	
	<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="panel panel-info">
				<%Discussion discussion = (Discussion)request.getAttribute("discussion"); %>	
					<h3><%=discussion.getTitle() %> </h3>
				
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
		
			<c:forEach var="listAnswer" items="${discussionAnswer}">
			<hr>
				<div class="panel panel-info">
					<p>${listAnswer.answer}</p>
					<span style="font-size:20px;" class="glyphicon glyphicon-user" ></span><strong style="font-size:25px;">${listAnswer.getName()}</strong>
					<strong style="font-size:25px;float:right;clear:right;"><span style="font-size:20px;" class="glyphicon glyphicon-calendar" ></span>  ${listAnswer.getDate()}</strong>	
				</div>
				
			</c:forEach>
			<hr>
			
			<form:form action="saveAnswer" method="post" modelAttribute="answerDiscussion">
				<div class="answer">
					<form:hidden path="disId"/>
					<div class="form-group">
						<label for="answerQuestion" style="color:green;font-size:20px;">Your Answer</label>		
							<form:textarea id="ans1" class="form-control" path="answer" required="required" focus="true"></form:textarea>
							
							<script>
								CKEDITOR.replace('ans1',{
									height:260,
									width:700
								});
							</script>			
					</div>
					<br>
					<button type="submit" class="btn btn-info btn-sm">Post Answer</button>
				</div>
			</form:form>
			
	</div>			
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>