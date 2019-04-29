package com.project.spring.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.web.socket.server.standard.SpringConfigurator;

public class ModifiedServerEndpointConfigurator extends SpringConfigurator
{
//	 public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
//	        HttpSession httpSession = (HttpSession) request.getHttpSession();
//	        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
//	        super.modifyHandshake(sec, request, response);
//	    }

}
