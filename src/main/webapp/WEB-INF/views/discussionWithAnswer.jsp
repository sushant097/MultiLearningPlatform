<%@page import="com.project.spring.model.Discussion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discussion With Answer</title>
<link rel="stylesheet" type="text/css"
	href="resources/static/css/bootstrap.min.css">

<script type="text/javascript"
	src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
	
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style>
.panel {
	border: 0px;
	box-shadow: none;
}

.glyphicon glyphicon-triangle-top {
	
}
a { color: inherit; }
a:hover{cursor:pointer;cursor:hand;}
</style>
</head>
<body>

	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>

	<div class="container">
		${error}
		<h1 id="Internalerror"></h1>
		<%
			Discussion discussion = (Discussion) request.getAttribute("discussion");
		%>
		<div class="row">
			<div class="col-md-1">
				<br> <br> <br> <br> <br>
					<a title="Question is Clear and  Useful"><span  id="disUp" class="glyphicon glyphicon-triangle-top" aria-hidden="true" style="font-size: 30px;" ></span></a><br>
					&nbsp;&nbsp;&nbsp;<span   id="disVote"><%=discussion.getVote()%></span><br> 
					<a title="Question is unclear and not Useful"><span  id="disDown" class="glyphicon glyphicon-triangle-bottom" aria-hidden="true" style="font-size: 30px;" ></span></a>
					
					
				
			</div>
			<div class="col-md-11">
				<div class="panel panel-info">

					<h3><%=discussion.getTitle()%>
					</h3>
					<strong style="font-size: 20px; float: right; clear: right;"><button
							class="btn btn-warning btn-xs"
							onclick="editDiscussion('editDiscussion?disId=<%=discussion.getDisId()%>');">Edit</button></strong>

					<hr />
					<%=discussion.getQuestion()%>
					<span style="font-size: 20px;" class="glyphicon glyphicon-user"></span><strong
						style="font-size: 25px;"><%=discussion.getName()%></strong> 
						<strong style="font-size: 20px; float: right; clear: right;">
						 <span style="font-size: 20px;" class="glyphicon glyphicon-calendar"></span>
						<%=discussion.getDate()%></strong> <br>
					
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <span
						style="text-align: center; background-color: #b8adb1; font-size: 15px; font-color: white;"><%=Discussion.listToString(discussion.getRelatedTo())%></span>
					<br>
					<hr>
				</div>
				<br>
				<span style="font-size: 20px; color: green; float: left; clear: left;">Answers:
						<%=discussion.getNoOfAnswer()%></span>
						<br>
					
			</div>
		</div>
		<hr>
		<br>
		<div class="row">
			<c:forEach var="listAnswer" items="${discussionAnswer}">
				<div class="col-md-1">
					<br> <br>
					<div class="pull-left">
						
						<a title="Answer is Clear and  Useful" onclick="clickDisAns(${listAnswer.getDisId()})"><span  id="disAnsUp" class="glyphicon glyphicon-triangle-top" aria-hidden="true" style="font-size: 30px;" ></span></a><br>
					&nbsp;&nbsp;&nbsp;<span   id="disAnsVote${listAnswer.getDisId()}">${listAnswer.getVote()}</span><br> 
					<a title="Answer is unclear and not Useful" onclick="clickDisAns(${listAnswer.getDisId()})"><span  id="disAnsDown" class="glyphicon glyphicon-triangle-bottom" aria-hidden="true" style="font-size: 30px;" ></span></a>
					</div>
				</div>


				<div class="col-md-11">

					<hr>

					<div class="panel panel-info">
						<strong style="font-size: 20px; float: right; clear: right;"><button
								class="btn btn-warning btn-xs"
								onclick="editAnswer('editAnswerDiscussion?ansId=${listAnswer.getAnsId()}');">Edit</button></strong>

						<p>${listAnswer.answer}</p>

						<span style="font-size: 20px;" class="glyphicon glyphicon-user"></span><strong
							style="font-size: 25px;">${listAnswer.getName()}</strong> <strong
							style="font-size: 25px; float: right; clear: right;"><span
							style="font-size: 20px;" class="glyphicon glyphicon-calendar"></span>
							${listAnswer.getDate()}</strong>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<hr>
			<div class="col-md-1"></div>
			<div class="col-md-11">
				<form:form action="saveAnswer" method="post"
					modelAttribute="answerDiscussion">
					<div class="answer">
						<form:hidden path="disId" />
						<form:hidden path="ansId" />
						<div class="form-group">
							<label for="answerQuestion"
								style="color: green; font-size: 20px;width : 700;">Your Answer</label>
							<form:textarea id="ans1" class="form-control" path="answer"
								required="required" focus="true"></form:textarea>
							<script>
								CKEDITOR.replace('ans1', {
									height : 260,
									width : 700
								});
							</script>
						</div>
						<br>
						<button type="submit" class="btn btn-info btn-sm">Post
							Answer</button>
					</div>
				</form:form>
			</div>
		</div>


	</div>

	<script>
		

/* 		var url = "";
		var request;
		function editAnswer(url1) {
			console.log(url1);
			url = url1;
			sendInfo();
		}
		function editDiscussion(url1) {
			console.log(url1);
			url = url1
			sendInfo();
		}

		function sendInfo() {

			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}

			try {
				request.onreadystatechange = getInfo;
				request.open("POST", url, true); // open get method , asynchronous is true
				request.send();

			} catch (e) {
				alert("Unable to connect to server");
			}
		}

		function getInfo() {
			if (request.readyState == 4 && (request.status == 200)) { // if the operation is completed fully
				var val = request.responseText;
				var para = document.getElementById("Internalerror");
				para.innerHTML = val;
				console.log(val);
			}
		}
		function sendDisInfo() {

			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}

			try {
				request.onreadystatechange = getInfo;
				request.open("POST", url, true); // open get method , asynchronous is true
				request.send();

			} catch (e) {
				alert("Unable to connect to server");
			}
		} */
		$(document).ready(function(){
			<% String disemailUser = discussion.getEmail();
			   String disemailSession = (String)session.getAttribute("email");
			 %>
			$("#disUp").click(function(event){
				event.preventDefault();
				var disId =	 <%=discussion.getDisId()%>;
				<%  if(disemailUser.equals(disemailSession)){   %>
			           $("#Internalerror").show(1000).text("You can't vote your own post!").css("color","red").hide(5000);
				<%}else{%>
				$.ajax({
					type:'POST',
					url: 'upVoteDiscussion',
					data: 'disId='+disId,
					success: function(result){
						if(result == 'success'){ // if result is up or down
							$("#disUp").css("color","orange");
							$("#disDown").css("color","#999999");
							 var vote = parseInt($("#disVote").text());
							vote = vote+1;
							$("#disVote").text("");
							$("#disVote").text(vote);
							
						} else{
							 $("#Internalerror").text(1000).text("Operation can't done!").css("color","red").hide(5000);
						}
						
					},
					error: function(){
						alert("Error occured while request!");
					}
				});
				
				<%}%>
			});
			$("#disDown").click(function(event){
				    event.preventDefault();
					var disId =	 <%=discussion.getDisId()%>;
					<% if(disemailUser.equals(disemailSession)){%>
			           $("#Internalerror").text(1000).text("You can't vote your own post!").css("color","red").hide(5000);
				<%}else{%>
					$.ajax({
						type:'POST',
						url: 'downVoteDiscussion',
						data: 'disId='+disId,
						success: function(result){ 
							if(result == 'success'){ // if result is up or down
								$("#disDown").css("color","orange");
								$("#disUp").css("color","#999999");
								var vote = parseInt($("#disVote").text());
								vote = vote - 1;
								$("#disVote").text("");
								$("#disVote").text(vote);
							} else{
								 $("#Internalerror").text(1000).text("Operation can't done!").css("color","red").hide(5000);
							}
							
						},
						error: function(){
							alert("Error occured while request!");
						}
					});

				<%}%>
			});
		});
	</script>
	<script type="text/javascript">
	$(document).ready(function(){
	  var ansId;
	  function clickDisAns(ansIdValue){
		  ansId = parseInt(ansIdValue);
	  }
	  
	$("#disAnsUp").click(function(event){
		event.preventDefault();
		
		$.ajax({
			type:'POST',
			url: 'discussionAnswerUpVote',
			data: 'ansId='+ansId,
			success: function(result){
				if(result == 'success'){ // if result is up or down
					$("#disAnsUp").css("color","orange");
					$("#disAnsDown").css("color","#999999");
					 var vote = parseInt($("#disVote"+ansId).text());
					vote = vote+1;
					$("#disVote"+ansId).text("");
					$("#disVote"+ansId).text(vote);
					
				} else{
					 $("#Internalerror").text(1000).text("Operation can't done!").css("color","red").hide(5000);
				}
				
			},
			error: function(){
				alert("Error occured while request!");
			}
		});
		
		
	});
	
	$("#disAnsDown").click(function(event){
		event.preventDefault();
		
		$.ajax({
			type:'POST',
			url: 'discussionAnswerdownVote',
			data: 'ansId='+ansId,
			success: function(result){
				if(result == 'success'){ // if result is up or down
					$("#disAnsUp").css("color","orange");
					$("#disAnsDown").css("color","#999999");
					 var vote = parseInt($("#disVote"+ansId).text());
					vote = vote-1;
					$("#disVote"+ansId).text("");
					$("#disVote"+ansId).text(vote);
					
				} else{
					 $("#Internalerror").text(1000).text("Operation can't done!").css("color","red").hide(5000);
				}
				
			},
			error: function(){
				alert("Error occured while request!");
			}
		});	
	});
	});
	</script>
	<jsp:include page="../../resources/header/footer.jsp"/>
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