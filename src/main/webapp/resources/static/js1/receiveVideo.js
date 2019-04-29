
	
		
		
		var url = "ws://localhost:8080/MultiLearningPlatformMyProject/live/"; // 8080/application_name/value_given_in_annotation
		var check = true;
		var socket = new WebSocket(url + roomname);
		var videoData = [];
		var superBuffer = null;
		
		let sourceBuffer;
		socket.onopen = function() {
			console.log("Live Training Coonected!!");

		}
		socket.onclose = function(){
			console.log("Live Training Disconnect!");
		}

		//var video = document.createElement('video');
		socket.onmessage = function(videoStream) {
			var video = document.querySelector('video');// document.querySelector('video');
			video.src = window.URL.createObjectURL(videoStream.data);
			
			/*document.body.appendChild(video);
			video.load();*/
			var playPromise = video.play();
			video.onloadeddata = function() {
				URL.revokeObjectURL(video.src);
				
			}
			
			if (playPromise !== undefined) {
			  playPromise.then(function() {
			    // Automatic playback started!
			  }).catch(function(error) {
			    // Automatic playback failed.
			    // Show a UI element to let the user manually start playback.
			    console.log(error);
			  });
			}
			console.log(videoStream)
			//console.table(videoStream);

		}
		socket.onerror = function(err) {
			console.log("Error: " + err);
		}
	
	