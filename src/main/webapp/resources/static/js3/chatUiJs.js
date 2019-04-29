/**
 * 
 */



	
	
	var websocket = new WebSocket("ws://localhost:8080/GroupVideoMulticast/liveChatMessage");
	
	websocket.onopen = function(){
		console.log("You are connected to chat!");
	}
	var check = true;
	websocket.onmessage = function processMessage(message){
		var jsonData = JSON.parse(message.data);
		if(jsonData.message != null) textArea.value += jsonData.message + "\n";
		document.getElementById("textArea").setAttribute('style', 'background-color:#D0D0D0;display:block;')

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
			
				document.getElementById("textArea").setAttribute('style', 'background-color:#F8F8F8;display:block;')
				
			
		}
	});

//	#F8F8F8