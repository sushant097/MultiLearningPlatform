<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Live Training Page</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	<div class="container">
		<%if(request.getAttribute("msg") !=null){ %>
			${msg}
		<%} %>
		<div class="row">
			<div class="col-md-4">
				//sidebar for list of training available
			</div>
			<div class="col-md-8">
				
				<form:form action="startLiveTraining" method="post" modelAttribute="liveTrain">
					<form:hidden path="broadcastId" />
					<div class="form-group">
						<label for="roomname">Input Room Name(You should create unique roomName)</label>
						<form:input type="text"  required="required" path="roomname" class="form-control" id="checkroom" />
						<span id="roomerror" style="color:red;font-size:20px;"></span>
					</div>
					<div class="form-group">
						<label for="title">Title</label>
						<form:input type="text"  required="required" class="form-control" path="title" placeholder="Title must be short and sweet as long as understandable"/>
					</div>
					<div class="form-group">
						<label for="relatedTo">Related Faculty</label>
						
						<div  id="showSelect" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<p>You can click Control and select multiple if necessary</p>
						</div>
				
						<form:select path="relatedTo" multiple="true"  required="required" class="form-control" id="select">
							
							<form:option value="BCE"></form:option>
							<form:option value="BEL"></form:option>
							<form:option value="BCT"></form:option>
							<form:option value="BEX"></form:option>
							<!-- Also to be add Industrial Engineering as Thapathali Campus -->
							<form:option value="BME"></form:option>
							<form:option value="B.Arch"></form:option>
						</form:select>
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<form:textarea path="description" required="required" class="form-control" row="10" col="200" placeholder="Include your time of training, about, topics to be covered and also next training etc." />
					</div>
					
					<button type="submit" class="btn btn-success btn-sm">Create LiveCast</button>
					
				</form:form>
			</div>
		</div>
		
		
	</div>
	
	
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
	<script>
		$("#select").click(function(){
			$("#showSelect").show();
			
		});
		$("#checkroom").keyup(function(){
			$.ajax({
				type:'POST',
				url:'checkroomname',
				cache: false,
				data: {roomname:$("#checkroom").val()},
				success: function(result){
					if(result==='valid'){
						$("#roomerror").html("");
					}else{
						$("#roomerror").html("Invalid RoomName! This is already Created!").show(2000);
					}
				},
				
				
			});
		});
	</script>
<!-- <script type="text/javascript" src="resources/static/js/angular.min.js"></script> -->
   <!-- Place bootstrap.min.js after jquery -->
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>
</body>
</html>