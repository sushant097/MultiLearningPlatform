<html>
<head>
<meta charset="ISO-8859-1">
<title>Demo</title>
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.min.css">
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/static/js/bootstrap.min.js"></script>

<script type="text/javascript" src="resources/static/js/mediastreamrecorder.js"></script>
<script src="https://cdn.webrtc-experiment.com/MediaStreamRecorder.js"> </script>
</head>
<body>
	
		<jsp:include page="../../resources/header/header.jsp"></jsp:include>
	
	 <div class="container">
	    <div class="row">
	    	<div class="col-md-8 col-sm-8 col-xs-8">
	    		
					<video id="video" autoplay controls style="height:800px;width:800px;padding:0px'">
						<source src="" type="video/webm">
					</video>
	    	</div>
	    	
	    	<script type="text/javascript" src="resources/static/js1/sendVideo.js"></script>
	    
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
	       <div class="row">
	       	<button id="stopButton" onclick="stop()">Stop</button>
				<button id="downloadButton" onclick="downloadData()">DownloadVideo</button>
	       </div>
     </div>
<!-- 	// new Websocket for the send to whole video at the stop button to server-->
	<script type="text/javascript" src="resources/static/js1/chatMessage.js"></script>
</body>
</html>