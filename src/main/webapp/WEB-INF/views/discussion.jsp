<%@page import="com.project.spring.model.Discussion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Discussion</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="resources/static/css/navbar.css">

<style type="text/css">
#result{
	position: absolute;
	width: 100%;
	padding-top: 15px;
	max-width: 870px;
	cursor: pointer;
	overflow-y: auto;
	max-height: 400px;
	box-sizing: border-box;
	z-index: 1001;
	
} 
	 .panel{
		border:0;
		
		/* box-shadow: none; */
	} 
</style>
	<jsp:include page="../../resources/header/header.jsp"></jsp:include>

</head>

<body>
	
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	<div class="container">
	
		<%if(request.getAttribute("listDiscussion")!=null){	List<Discussion> listDiscussion1 = (List<Discussion>)request.getAttribute("listDiscussion");
			for(Discussion discussion:listDiscussion1){
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
				
		<%  }
			}else{%>
				<p>No content Found! </p>
			<%}%>
			
				<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="discussion?pageId=1">1</a></li>
				<li><a href="discussion?pageId=2">2</a></li>
				<li><a href="discussion?pageId=3">3</a></li>
				<li><a href="discussion?pageId=4">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	
	</div>
	
	<%-- <div class="panel-body">
					${discussion.question}
				</div>	
				<div class="" style="">
					Ask By<strong>Sushant</strong>> of <h5>National College Of Engineering</h5>
					On <strong>2018/6/30</strong>
				</div> --%>
	
	
	<jsp:include page="../../resources/header/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		$("#value").keyup(function(){
			$.ajax({
				type: 'POST',
				url: 'discussionSearch',
				data: 'searchName='+$("#value").val(),
				success:function(result){
					$("#result").html(result);
				},
				error: function(){
					alert("Error Occured while Request...");
				}
			});
		});
	</script>

</body>
</html>