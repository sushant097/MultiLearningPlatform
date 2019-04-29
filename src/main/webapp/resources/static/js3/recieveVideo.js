
		var url = "ws://"+document.location.host+"/MultiLearningPlatformMyProject/liveStreamMulticast/<%=session.getAttribute('roomname')%>"; // 8080/application_name/value_given_in_annotation
		var check = true;
		var socket = new WebSocket(url);
		var videoData = [];
		var superBuffer = null;
		//var videoUrl;

		//socket.binaryType = 'blob';//arraybuffer
		
		socket.onopen = function() {
			console.log("Connected!!");

		}

		var check = true;
		//var video = document.createElement('video');
		socket.onmessage = function(videoStream) {
			var video = document.querySelector('video');
			video.src = window.URL.createObjectURL(videoStream.data);
			
			//document.body.appendChild(video);
			video.load();
			var playPromise = video.play();
			video.onloadeddata = function() {
				URL.revokeObjectURL(video.src);
				
			}
			// In browsers that don’t yet support this functionality,
			// playPromise won’t be defined.
			if (playPromise !== undefined) {
			  playPromise.then(function() {
			    // Automatic playback started!
			  }).catch(function(error) {
			    // Automatic playback failed.
			    // Show a UI element to let the user manually start playback.
			    console.log(error);
			  });
			}
			//video.srcObject

			//video.play();

			console.table(videoStream);

		}
		socket.onerror = function(err) {
			console.log("Error: " + err);
		}
	
	