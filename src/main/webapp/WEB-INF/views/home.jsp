<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="assets/images/logo3.png"
	type="image/x-icon">
<meta name="description" content="Eng Notes">
<title>Multi-Learning Platform</title>
<jsp:include page="../../resources/header/templateHeader.jsp"></jsp:include>
<!-- <link rel="stylesheet" href="resources/static/css/bootstrap.min.css">
 -->
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
.smallnav menu img{
	width:32px;
	height:32px;
	padding: 0px;
}
.largenav img
{
	width:100px;
	height:100px;
	padding: 0px;
}
</style> 
<link rel="stylesheet" type="text/css" href="resources/static/css/navbar.css">

</head>
<body>

	<div id="flipkart-navbar" >
	    <div class="container">
	        <div class="row row1">
	            <ul class="largenav pull-right">
	                <li class="upper-links"><a class="links" href="homePage">Home</a></li>
	                <li class="upper-links dropdown">
	                	<a class="links" href="">Notes</a>
	                	<ul class="dropdown-menu">
	                		<li class="upper-links"><a class="links" href="uploadFile">Upload Notes</a></li>
	                		<li class="upper-links"><a class="links" href="noteList?pageId=1">Download Notes</a></li>	                		
	                	</ul>
	                </li>
	                <li class="upper-links dropdown">
		                <a class="links" href="">Discussion</a>
		                	<ul class="dropdown-menu">
		                		<li class="upper-links"><a class="links" href="createDiscussion">Create Discussion</a></li>
		                		<li class="upper-links"><a class="links" href="discussion?pageId=1">Answer Discussion</a></li>
		                	</ul>	
	                </li>
	                <li class="upper-links"><a class="links" href="${liveCastUrl}">${liveCast}</a></li>
	                <li class="upper-links"><a class="links" href="listTrainVideo">Video</a></li>
	                <li class="upper-links"><a class="links" href="#">Special Content</a></li>
	                <li class="upper-links">
	                    <a class="links" href="">
	                        <span class="glyphicon glyphicon-bell"  style="overflow: visible;font-size:15px;"></span>
	                    </a>
	                </li>
	                <li class="upper-links dropdown"><a class="links" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span> <%=(String)session.getAttribute("name")%></a>
	                    <ul class="dropdown-menu">
	                        <li class="profile-li"><a class="profile-links" href="login">Login</a></li>
	                        <li class="profile-li"><a class="profile-links" href="logout">Logout</a></li>
	                        <li class="profile-li"><a class="profile-links" href="${adminUrl} }">Verify Note</a></li>
	                        <li class="profile-li"><a class="profile-links" href="http://clashhacks.in/">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="http://clashhacks.in/">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="http://clashhacks.in/">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="http://clashhacks.in/">Link</a></li>
	                    </ul>
	                </li>
	            </ul>
	        </div>
	        <div class="row row2">
	            <div class="col-sm-2">
	                <h2 style="margin:0px;"><span class="smallnav menu" onclick="openNav()"> ☰ EngNotes</span></h2>
	                <span class="largenav"  style="margin:0px;"><img alt="homePage" src="images/engnotelogo.png"></span>
	            </div>
	            <div class="flipkart-navbar-search smallsearch col-sm-8 col-xs-11">
	                <div class="row form-group">
	                	
	                    <input class="flipkart-navbar-input col-xs-11 form-control" type="text" placeholder="Search for Discussion, Notes, Videos ..." id="value" onkeyup="sendInfo();">
	     				<br><br>
	                    <button class="flipkart-navbar-button col-xs-1" onclick="location.href=''">
								<i class="glyphicon glyphicon-search"></i>    		            
	                    </button>
	                </div>
	                <ul class="list-group" id="result"></ul>
	            </div>
	           <!--  <div class="cart largenav col-sm-2">
	                <a class="cart-button">
	                    <svg class="cart-svg " width="12 " height="16 " viewBox="0 0 16 16 ">
	                        <path d="M15.32 2.405H4.887C3 2.405 2.46.805 2.46.805L2.257.21C2.208.085 2.083 0 1.946 0H.336C.1 0-.064.24.024.46l.644 1.945L3.11 9.767c.047.137.175.23.32.23h8.418l-.493 1.958H3.768l.002.003c-.017 0-.033-.003-.05-.003-1.06 0-1.92.86-1.92 1.92s.86 1.92 1.92 1.92c.99 0 1.805-.75 1.91-1.712l5.55.076c.12.922.91 1.636 1.867 1.636 1.04 0 1.885-.844 1.885-1.885 0-.866-.584-1.593-1.38-1.814l2.423-8.832c.12-.433-.206-.86-.655-.86 " fill="#fff "></path>
	                    </svg>Link
	                    <span class="item-number ">0</span>
	                </a>
	            </div> -->
	        </div>
	    </div>
	</div>
	<div id="mySidenav" class="sidenav">
	    <div class="container" style="background-color: #2874f0; padding-top: 10px;">
	        <span class="sidenav-heading">Home</span>
	        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
	    </div>
	    <a href="http://clashhacks.in/">Link</a>
	    <a href="http://clashhacks.in/">Link</a>
	    <a href="http://clashhacks.in/">Link</a>
	    <a href="http://clashhacks.in/">Link</a>
	</div>
	
		
		<%
			if(null !=request.getAttribute("error"))
			{
		%>		<div class="container">
					<div class="alert alert-warning alert-dismissible" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${error}
					</div>
				</div>
				
	    <%
			}
	
		%>
		
<!-- 		////////////////////////////////Template Body////////////////////// -->	
	
		<jsp:include page="../../resources/header/templateBody.jsp"></jsp:include>
		
<!-- 		////////////////////////////////Template Body////////////////////// -->		

		
		
			
			<a href="checkMaterial"> Check Material Admin</a>
	
			
			<div class="container" ng-app="">
				<div  ng-init="name='${name}'">
					<h1>Welcome To Home Page</h1>
					<h2>Welcome <b> {{name| uppercase}} </b>to login page</h2>
				</div>
			</div>
			
			<br>
			
			
	<script type="text/javascript">
		var request;
		function sendInfo() {
			var v = document.getElementById("value").value;
			
			var url = "discussionSearch?searchName=" + v;

			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}

			try {
				request.onreadystatechange = getInfo;
				request.open("GET", url, true); // open get method , asynchronous is true
				request.send();
				
			} catch (e) {
				alert("Unable to connect to server");
			}
		}

		function getInfo() {
			if (request.readyState == 4 && (request.status == 200)) { // if the operation is completed fully
				var val = request.responseText;
				var ul = document.getElementById("result");
				ul.innerHTML = val;
			}else{
				
			}
		}
	</script>
		
<!-- ////////////////////////////////Template Footer//////////////////////-->
	<script src="assets/web/assets/jquery/jquery.min.js"></script>
	<script src="assets/popper/popper.min.js"></script>
	<script src="assets/tether/tether.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/typed/typed.min.js"></script>
	<script src="assets/touch-swipe/jquery.touch-swipe.min.js"></script>
	<script src="assets/smooth-scroll/smooth-scroll.js"></script>
	<script src="assets/viewport-checker/jquery.viewportchecker.js"></script>
	<script src="assets/social-likes/social-likes.js"></script>
	<script src="assets/dropdown/js/script.min.js"></script>
	<script src="assets/jarallax/jarallax.min.js"></script>
	<script src="assets/theme/js/script.js"></script>
	
<!-- ////////////////////////////////////////////////////////////////////////////-->
<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
<script type="text/javascript" src="resources/static/js/navbar.js"></script>

</body>
</html>