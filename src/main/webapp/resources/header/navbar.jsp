
	

	<%
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
				response.setHeader("Pragma", "no-cache"); //HTTP 1.0
				response.setHeader("Expires", "0");
	%>
    
    
 <div id="flipkart-navbar">
	    <div class="container">
	        <div class="row row1">
	            <ul class="largenav pull-right">
	                <li class="upper-links"><a class="links" href="homePage">Home</a></li>
	                <li class="upper-links dropdown">
	                	<a class="links" href="">Notes</a>
	                	<ul class="dropdown-menu">
	                		<li class="upper-links"><a class="links" href="uploadFile">Upload Notes</a></li>
	                		<li class="upper-links"><a class="links" href="noteList/?pageId=1">Download Notes</a></li>	                		
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
	                <li class="upper-links"><a class="links" href="playVideo">Video</a></li>
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
	                        <li class="profile-li"><a class="profile-links" href="#">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="#">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="#">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="#">Link</a></li>
	                        <li class="profile-li"><a class="profile-links" href="#">Link</a></li>
	                    </ul>
	                </li>
	            </ul>
	        </div>
	        <div class="row row2">
	            <div class="col-sm-2">
	                <h2 style="margin:0px;"><span class="smallnav menu" onclick="openNav()"> ☰ Brand</span></h2>
	                <span class="largenav"  style="margin:0px;"><img alt="homePage" src="images/engnotelogo.png"></span>
	            </div>
	            <div class="flipkart-navbar-search smallsearch col-sm-8 col-xs-11">
	                <div class="row form-group">
	                	
	                    <input class="flipkart-navbar-input col-xs-11 form-control" type="text" placeholder="Search for Discussion, Notes, Videos ..." id="value" ">
	     
	                    <button class="flipkart-navbar-button col-xs-1" onclick="location.href=''">
								<span class="glyphicon glyphicon-search"></span>    		            
	                    </button>
	                    <br>
	                    <ul class="list-group" id="result"></ul>
	                    
	                </div>
	            </div>
	           
	        </div>
	    </div>
	</div>
	<div id="mySidenav" class="sidenav">
	    <div class="container" style="background-color: #2874f0; padding-top: 10px;">
	        <span class="sidenav-heading">Home</span>
	        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
	    </div>
	    <a href="#">Home</a>
	    <a href="#">Link</a>
	    <a href="#">Link</a>
	    <a href="#">Link</a>
	</div>
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
			