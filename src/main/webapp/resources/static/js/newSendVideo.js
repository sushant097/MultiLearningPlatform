

	const mediaSource = new MediaSource();
	mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
	let mediaRecorder;
	let sourceBuffer;
	let recordedBlobs;
	let tempBlob
	/////////
	var url = "ws://localhost:8080/MultiLearningPlatformMyProject/live/"; // 8080/application_name/value_given_in_annotation
	var socket = new WebSocket(url + roomname);
	/////////
	console.log("Live:"+url + roomname);
	socket.onopen = function(){
		console.log("Connected to LiveStream!!");
	}
	socket.onmessage = function(msg){
		console.log("Message come from server");
	}
	socket.onclose = function(){
		console.log("Disconnect to livestream");
		//alert("Disconnet To livestream!");
	}
	
	function handleSourceOpen(event) {
		  console.log('MediaSource opened');
		  sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
		  console.log('Source buffer: ', sourceBuffer);
	}
	function handleDataAvailable(event) {
		/*var tempBlob = [];
		  if (event.data && event.data.size > 0) {// it gives us blob data
			tempBlob.push(event.data);
			socket.send(tempBlob, {type:'video/webm'})
		    recordedBlobs.push(event.data);
			console.log("Size of Recorded Data: "+recordedBlobs.length);
			tempBlob = [];
		  }*/
	}
	function handleStop(event) {
		  console.log('Recorder stopped: ', event);
	}
	  function gotstream(stream) {
		  document.getElementById("downloadButton").disabled = true;
		  console.log('getUserMedia() got stream: ', stream);
		  window.stream = stream;
		  const gumVideo = document.querySelector('video');
		  gumVideo.srcObject = stream;
		  
	    recordedBlobs = [];
	    let options = {mimeType: 'video/webm;codecs=vp9'};
	    if (!MediaRecorder.isTypeSupported(options.mimeType)) {
	      console.log(options.mimeType + ' is not Supported');
	      options = {mimeType: 'video/webm;codecs=vp8'};
	      if (!MediaRecorder.isTypeSupported(options.mimeType)) {
	        console.log(options.mimeType + ' is not Supported');
	        options = {mimeType: 'video/webm'};
	        if (!MediaRecorder.isTypeSupported(options.mimeType)) {
	          console.log(options.mimeType + ' is not Supported');
	          options = {mimeType: ''}; 
	        }
	      }
	    }
	    try {
	        mediaRecorder = new MediaRecorder(window.stream, options);
	      } catch (e) {
	        console.error(`Exception while creating MediaRecorder: ${e}`);
	        alert(`Exception while creating MediaRecorder: ${e}. mimeType: ${options.mimeType}`);
	        return;
	      }
	    console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
	    document.getElementById("downloadButton").disabled = true;
	    mediaRecorder.onstop = handleStop;
	    mediaRecorder.ondataavailable = function(event){ 
	    	 tempBlob = [];
			  if (event.data && event.data.size > 0) {
				  	tempBlob.push(event.data);
				  	socket.send(new Blob(tempBlob,{type : 'video/webm'}));
			    	recordedBlobs.push(event.data);
			    	console.log("Size of Temp recorded Data: "+tempBlob.length);
			    	console.log("Size of Recorded Data: "+recordedBlobs.length);
			    	tempBlob = [];
			  }
	    };
	    mediaRecorder.start(10000);// 10s chunk of video
	    console.log('MediaRecorder started', mediaRecorder);
	  }
	
	function stopRecording(){
		mediaRecorder.stop();
		console.log('Recorded Blobs: ',recordedBlobs);
		playData();
		uploadVideo();
	}
	
	function playData(){
		document.getElementById('video1').style.display ='block';
		  var blob = new Blob(recordedBlobs,{type : 'video/webm'});
		  var videoURL  = window.URL.createObjectURL(blob);
		  video1.src = videoURL;
	}
	 // below function via: http://goo.gl/B3ae8c
    function bytesToSize(bytes) {
        var k = 1000;
        var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        if (bytes === 0) return '0 Bytes';
        var i = parseInt(Math.floor(Math.log(bytes) / Math.log(k)), 10);
        return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
    }
	
	function uploadVideo(){	
		var superBlob = new Blob(recordedBlobs,{type : 'video/webm'});
		//var message= "completeVideo";
		// generating a random file name
        var fileName = getFileName('webm');
        // we need to upload "File" --- not "Blob"
        var fileObject = new File([superBlob], fileName, {
            type: "video/webm"
        });
		var formData = new FormData();
		// recorded data
        formData.append('video-blob', fileObject);
        // file name
        formData.append('video-filename', fileObject.name);
        document.getElementById('header').innerHTML = 'Uploading to Server using jQuery.... file size: (' +  bytesToSize(fileObject.size) + ')';	
        console.log('Uploading to Server using jQuery.... file size: (' +  bytesToSize(fileObject.size) + ')');
       console.log("broadCastId:"+broadCastId);
        $.ajax({
            type: 'POST',
        	url: 'uploadTrainVideo', // replace with your own server URL
            data: {file:formData,broadCastId:broadCastId},
            success: function(response) {
                if (response === 'success') {
                    alert('successfully uploaded recorded blob');
                    document.getElementById('header').innerHTML ="successfully uploaded recorded blob size: "+ bytesToSize(fileObject.size);
                }else{
                	alert(response);//error
                }
            },
        	error: function(){
        		alert("Error Occured While Uploading!");	
        	}
        });
		document.getElementById("downloadButton").disabled = false;
		socket.close();
	}
	
	   // this function is used to generate random file name
    function getFileName(fileExtension) {
        var d = new Date();
        var year = d.getUTCFullYear();
        var month = d.getUTCMonth();
        var date = d.getUTCDate();
        return 'LiveTraining-' + year + month + date + '-' + getRandomString() + '.' + fileExtension;
    }
    function getRandomString() {
        if (window.crypto && window.crypto.getRandomValues && navigator.userAgent.indexOf('Safari') === -1) {
            var a = window.crypto.getRandomValues(new Uint32Array(3)),
                token = '';
            for (var i = 0, l = a.length; i < l; i++) {
                token += a[i].toString(36);
            }
            return token;
        } else {
            return (Math.random() * new Date().getTime()).toString(36).replace(/\./g, '');
        }
    }
	
	function downloadData(){

			  const blob = new Blob(recordedBlobs, {type: 'video/webm'});
			  const url = window.URL.createObjectURL(blob);
			  const a = document.createElement('a');
			  a.style.display = 'none';
			  a.href = url;
			  a.download = 'test.webm';
			  document.body.appendChild(a);
			  a.click();
			  setTimeout(() => {
			    document.body.removeChild(a);
			    window.URL.revokeObjectURL(url);
			  }, 100);
	}
	
	function handleSuccess(stream) {
	      document.getElementById("downloadButton").disabled = true;
		  console.log('getUserMedia() got stream: ', stream);
		  window.stream = stream;

		  const gumVideo = document.querySelector('video');
		  gumVideo.srcObject = stream;
		  
		 //startRecording();
	}

	function handleError(error) {
		  console.log('navigator.getUserMedia error: ', error);
	}
	
	navigator.getUserMedia  = navigator.getUserMedia || 
							    navigator.webkitGetUserMedia ||
							     navigator.mozGetUserMedia || 
							      navigator.msGetUserMedia;
	
	if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
	  
	  navigator.mediaDevices.getUserMedia({video: true , audio: true})
	      .then(gotstream)
	      .catch(handleError);
	  
	}else{
		console.log("getUserMedia is not supported on Your Browser!");
		alert("Sorry! getUserMedia is not supported on Your Browser!. Please Try it in Chrome or Firefox")
	}

	 // below function via: http://goo.gl/6QNDcI
    function getTimeLength(milliseconds) {
        var data = new Date(milliseconds);
        return data.getUTCHours() + " hours, " + data.getUTCMinutes() + " minutes and " + data.getUTCSeconds() + " second(s)";
    }