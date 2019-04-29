

	var appname = window.navigator.appName;
	console.log(appname);
	
	const mediaSource = new MediaSource();
	mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
	let sourceBuffer;
	
	/////////
	var url = "ws://localhost:8080/MultiLearningPlatformMyProject/live/"; // 8080/application_name/value_given_in_annotation
	var socket = new WebSocket(url + roomname);
	
		var recordedChunks = [];
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
	var recBlob;
	//////////////////////////////////////
	
	function handleSourceOpen(event) {
		  console.log('MediaSource opened');
		  sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
		  console.log('Source buffer: ', sourceBuffer);
	}
	  function gotMedia(stream) {
	    video.srcObject = stream;
	    
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
	    
	    mediaRecorder = new MediaStreamRecorder(stream, options);
	    console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
	    //mediaRecorder.recorderType = MediaRecorderWrapper;// force MediaRecorder in Chrome.
	    document.getElementById("downloadButton").disabled = true;
	    mediaRecorder.start(10000);// 10s chunk of video
	    console.log("recorder started");
	    
	
	    mediaRecorder.ondataavailable = function(e){ // it gives us blob data;
	    	socket.send(e, {type: 'video/webm'});
	    	recordedChunks.push(e.data);
	    	console.log("Size of Recorded Data: "+recordedChunks.length);
	    };
	    mediaRecorder.onstop = handleStop;
	  }
	  function handleStop(event) {
		  console.log('Recorder stopped: ', event);
		}
	
	function stop(){
		mediaRecorder.stop();
		var superBlob = new Blob(recordedChunks,{type : 'video/webm'});
		
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
        $.ajax({
        	url: 'fileUpload', // replace with your own server URL
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function(response) {
                if (response === 'success') {
                    alert('successfully uploaded recorded blob');
                    document.getElementById('header').innerHTML ="successfully uploaded recorded blob";
                }else{
                	alert(response);//error
                }
            }
        });
		document.getElementById("downloadButton").disabled = false;
		socket.close();
		playData();
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
	function playData(){
	
		var clipName = prompt('Enter a name for your video clip');

		  var clipContainer = document.createElement('article');
		  var clipLabel = document.createElement('p');
		  var video = document.createElement('video');
		  var deleteButton = document.createElement('button');
		  
		  clipContainer.classList.add('clip');
		  video.setAttribute('controls', '');
		  deleteButton.innerHTML = "Delete";
		  clipLabel.innerHTML = clipName;
		  
		  clipContainer.appendChild(video);
		  clipContainer.appendChild(clipLabel);
		  clipContainer.appendChild(deleteButton);
		  soundClips.appendChild(clipContainer);
		  
		  var blob = new Blob(recordedChunks,{'type' : 'video/webm;codecs=vp9'});
		  var videoURL  = window.URL.createObjectURL(blob);
		  recordedChunks = [];
		  video.src = videoURL;
		  
		  deleteButton.onclick = function(e) {
			    var evtTgt = e.target;
			    evtTgt.parentNode.parentNode.removeChild(evtTgt.parentNode);
		  }
	}
	function downloadData(){
			  var blob = new Blob(recordedChunks, {type: options });
			  var blobURL = URL.createObjectURL(blob);
			  document.write('<a href="' + blobURL + '">' + 'SEE Video' + '</a>');
			  var a = document.createElement('a');
			  document.body.appendChild(a);
			  a.style = 'display: none';
			  a.href = blobURL;
			  a.download = 'videoNew.webm';
			  a.click();
			 window.URL.revokeObjectURL(blobURL);
	}
	
		
	if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
	  navigator.getUserMedia  = navigator.getUserMedia || 
			                             navigator.webkitGetUserMedia ||
			                              navigator.mozGetUserMedia || 
			                               navigator.msGetUserMedia;
	
	  navigator.mediaDevices.getUserMedia({video: true , audio: true})
	      .then(gotMedia)
	      .catch(e => { console.error('getUserMedia() failed: ' + e); });
	  
	}else{
		console.log("getUserMedia is not supported on Your Browser!");
		alert("Sorry! getUserMedia is not supported on Your Browser!. Please Try it in Chrome or Firefox")
	}
