<%@page import="com.project.spring.model.Upload"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>

	
	<div class="container">
		<div id="message"></div>
		<%Upload upload =(Upload) request.getAttribute("download"); %>
		<h1>Title: <%=upload.getTitle() %></h1>By: <span class="glyphicon glyphicon-user"></span><%=upload.getName()%>(<%=upload.getType() %>)<br>
		<p>Description: <%=upload.getDescription() %></p>
		<br>
		<button class="btn btn-primary btn-lg" onclick="location.href='downloadUploadNote?uploadId=<%=upload.getUploadId()%>'">Download Note</button>
	</div>
	<!-- <script type="text/javascript">
		var request;
		function sendInfo(uploadId) {
			
			
			var url = "downloadNote?uploadId=" + uploadId;
			console.log(url);
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
				var ul = document.getElementById("message");
				ul.innerHTML = val;
			}else{
				
			}
		}
	</script> -->
<jsp:include page="../../resources/header/footer.jsp"></jsp:include>
</body>
</html>