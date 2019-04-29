package com.project.spring.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chatMessage/{room}")
public class ChatEndpointNew 
{
	
	private final Logger log = Logger.getLogger(getClass().getName());
	
	@OnOpen
	public void open(final Session session, @PathParam("room")final String room)
	{
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
		System.out.println("session openend and bound to room: " + room);
	}
	
	@OnMessage
	public void onMessage(final Session session, final String message) {
		String room = (String)session.getUserProperties().get("room");
		try{
			for (Session s : session.getOpenSessions()){
				if(s.isOpen()
						  && room.equals(s.getUserProperties().get("room"))){
					String username = (String) session.getUserProperties().get("username");
					if(username == null){
						s.getUserProperties().put("username", message);
						s.getBasicRemote().sendText(buildJsonData("System", "You are now connected as:"+message));
					}else{
						s.getBasicRemote().sendText(buildJsonData(username, message));		
					}
				}
			}
		}catch(IOException e) {
			log.log(Level.WARNING, "on Text Transfer failed", e);
		}
	}
	
	@OnClose
	public void onClose(final Session session){
		String room = (String)session.getUserProperties().get("room");
		session.getUserProperties().remove("room",room);
		log.info("session close and removed from room: " + room);
	}
	
	private String buildJsonData(String username, String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", "<tr><td class='user label label-info'style='font-size:20px;'>"+username+"</td>"+"<td class='message badge' style='font-size:15px;'> "+message+"</td></tr>").build();
		StringWriter stringWriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
			jsonWriter.write(jsonObject);
		}
				
		return stringWriter.toString();
	}
}
