package com.project.spring.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/live/{room}")
public class LiveStreamEndpoint 
{
	
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	private final Logger log = Logger.getLogger(getClass().getName());
	private Session hostSession; 
	int counter=0;
	@OnOpen
	public void open(final Session session, @PathParam("room")final String room)
	{
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
		sessions.add(session);
		if(++counter==1){
			hostSession = session; 
			System.out.println("Host Session!");
		}
	}
	
	@OnMessage
	public void onMessage(final Session session, final byte[] videoData) {
		System.out.println("Message Received!");
		String room = (String)session.getUserProperties().get("room");
		Iterator<Session> iterator = sessions.iterator();
		Session tempSession = null;
		try{
			while (iterator.hasNext()){
				Session s = iterator.next();
				tempSession = s;
				if((tempSession != session)
						  && room.equals(s.getUserProperties().get("room"))){
					s.getBasicRemote().sendBinary(ByteBuffer.wrap(videoData));
					System.out.println("send To client!");
				}
			}
		}catch(IOException e) {
			log.log(Level.WARNING, "onVideo Transfer failed", e);
		}
	}
	
	@OnClose
	public void onClose(final Session session){
		String room = (String)session.getUserProperties().get("room");
		if(session.equals(hostSession)){
			for (Session s : session.getOpenSessions()){
				if(s.isOpen()
						  && room.equals(s.getUserProperties().get("room"))){
					session.getUserProperties().remove("room",room);
					sessions.remove(session);
					System.out.println("close Connection!");
				}
			}
		}else{
			session.getUserProperties().remove("room",room);
			log.info("session closed and removed from room: " + room);
			
			sessions.remove(session);
		}
		
	}
}
