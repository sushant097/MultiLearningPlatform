
	
	var url = "ws://localhost:8080/MultiLearningPlatformMyProject/chatMessage/";
	var websocket = new WebSocket(url + roomname);
	var table = document.getElementById("tablemess");

	websocket.onopen = function(){
		console.log("You are connected to chat!");
		console.log(url + roomname);
	}
	var check = true;
	websocket.onmessage = function processMessage(message){
		var jsonData = JSON.parse(message.data);
		if(jsonData.message != null) $("#tablemess").append(jsonData.message);
	}
	websocket.onclose = function(){
		alert("You are Disconnected To Live Chat!");
	}
	function sendMessage(){
		websocket.send(messageText.value);
		messageText.value = "";
	}
	// get the input field
	var input = document.getElementById("messageText");
	
	input.addEventListener("keyup", function(event){
		
		//cancel the default action, if needed
		event.preventDefault();
		// Number 13 is the "Enter" key on the keyboard
		if(event.keyCode === 13){
			// Trigger the button button element with a click
			document.getElementById("sendBtn").click();
							
			
		}
	});