

	
	
	var websocket = new WebSocket("ws://"+document.location.host+"/MultiLearningPlatformMyProject/liveChatMessage/<%=session.getAttribute('roomname')%>");
	var check = true;
	websocket.onmessage = function processMessage(message){
		var jsonData = JSON.parse(message.data);
		if(jsonData.message != null) textArea.value += jsonData.message + "\n";
		
	}
	websocket.onclose = function(){
		alert("You are Disconnected To Live Chat!")
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
