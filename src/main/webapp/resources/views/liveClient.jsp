<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play Video</title>

<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>

<style type="text/css">
textarea{
	resize:none;
	font: normal 1em Verdana, sans-serif;
 	 border:1px solid #eee;
  	height:700px;
 	 display:block;
}
/* :nth-child(even) { background-color: #F5F5F5; } */
</style>

<script type="text/javascript" src="resources/static/js1/receiveVideo.js"></script>

</head>
<body>

	<jsp:include page="../../resources/header/header.jsp"></jsp:include>

  <div class="container">
	    <div class="row">
	    	<div class="col-md-8 col-sm-8 col-xs-8">
	    		
					<video id="video" autoplay style="padding:0px;"><!-- height:800px;width:800px; -->
						<source src="" type="video/webm">
					</video>
				
	    	</div>
	    
	    	<div class="col-md-4 col-sm-4 col-xs-4">
		        <div class="panel panel-primary">
		          <div class="panel-heading" style="text-align:center;">Live Chat Window</div>
		          <div class="panel-body">          
			                <div class="form-group">
			                    <textarea id="textArea" class="form-control" readonly="readonly" rows="15" cols="55"></textarea>
			                </div>
		                
		            </div>
		            <div class="panel-footer">
		                 <div class="input-group">
		                  <input type="text" id="messageText" class="form-control">
		                  <span class="input-group-btn">
		                    <button class="btn btn-primary btn-sm" type="button" id="sendBtn" onclick="sendMessage();">Send</button>
		                  </span>
		                </div>
		         	</div>
	          </div>
	        </div>  
	       </div>
     </div>
	<script type="text/javascript" src="resources/static/js1/chatMessage.js"></script>
</body>
</html>	