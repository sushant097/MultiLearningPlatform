

	
	var url = "ws://"+document.location.host+"/MultiLearningPlatformMyProject/liveStreamMulticast/${roomname}"; // 8080/application_name/value_given_in_annotation
	
	var socket = new WebSocket(url);
	
		var recordedChunks = [];
		var recordedTemp = []; 
		var recorder = null;
		var webcamstream = null;
		var video = document.querySelector('video');
		
	socket.onopen = function(){
		
		console.log("Connected to Server!!");
		
	}
	socket.onmessage = function(msg){
		console.log("Message come from server");
		
	}
	/////////////////////////////////
	var recordedChunks = [];
	var mediaRecorder;
	var chunks = [];
	var options;
	//////////////////////////////////////
	
	  function gotMedia(stream) {
	    video.srcObject = stream;
	    
	    
	    if (MediaRecorder.isTypeSupported('video/webm;codecs=vp9')) {
	    	  options = {mimeType: 'video/webm; codecs=vp9'};
	    	} else if (MediaRecorder.isTypeSupported('video/webm;codecs=vp8')) {
	    	   options = {mimeType: 'video/webm; codecs=vp8'};
	    	} else {
	    	  console.log("Not supported vp8 or vp9");
	    	  options = {mimeType: 'video/webm'};
	    	}
	    
	    
	    console.log("mediaRecorderCalled");
	    mediaRecorder = new MediaStreamRecorder(stream, options);
	    document.getElementById("downloadButton").disabled = true;
	    mediaRecorder.start(10000);// 1hour recording
	    console.log("recorder started");
	    
	    /* mediaRecorder.ondataavailable = (event) =>{
	    	chunks.push(event.data);
	    	console.log("push  B");
	    	wholeVideo.push(event.data);
	    	console.log("WholeVideo Size:");
	    	setTimeout(sendData(),5010);
	    } */
	    mediaRecorder.ondataavailable = function(blob){ // it gives us blob data;
	    	socket.send(blob, {type: options});
	    	recordedChunks.push(blob.data);
	    	console.log("Size of Recorded Data: "+recordedChunks.length);
	    };
	    
	    
	    
	  }
	
	function stop(){
		mediaRecorder.stop();
		var superBuffer = new Blob(recordedChunks);
		var message= "completeVideo";
		socket.send(superBuffer, {type: options}, message);
		document.getElementById("downloadButton").disabled = false;
	}
		
	function downloadData(){
		
			  var blob = new Blob(recordedChunks, {
			    type: 'video/webm'
			  });
			  var url = URL.createObjectURL(blob);
			  var a = document.createElement('a');
			  document.body.appendChild(a);
			  a.style = 'display: none';
			  a.href = url;
			  a.download = 'videoNew.webm';
			  a.click();
			  window.URL.revokeObjectURL(url);
	}
	 /*  var buffer = new ArrayBuffer(500);
	  function sendData(){ 
		//var byteArray = new Uint8Array(recordedTemp);
		
		const superBuffer =  new Blob(chunks, {
			type: 'video/webm'
			});
		
	     socket.send(superBuffer);
	     console.log("Send Data");
	      console.table(superBuffer);
	      chunks = [];
	      
	  } */
		
	 
	  navigator.getUserMedia  = navigator.getUserMedia || 
			                             navigator.webkitGetUserMedia ||
			                              navigator.mozGetUserMedia || 
			                               navigator.msGetUserMedia;
	
	  navigator.mediaDevices.getUserMedia({video: true , audio: true})
	      .then(gotMedia)
	      .catch(e => { console.error('getUserMedia() failed: ' + e); });
