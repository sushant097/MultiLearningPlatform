package com.project.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.project.spring.websocket.ChatEndpointNew;
import com.project.spring.websocket.LiveStreamEndpoint;

@Configuration
public class EndpointConfig 
{
	@Bean
    public ChatEndpointNew chatEndpointNew(){
    	return new ChatEndpointNew();
    }
	
    @Bean
    public LiveStreamEndpoint liveStreamEndPoint(){
    	return new LiveStreamEndpoint();
    }
    
  @Bean
	public ServerEndpointExporter endpointExporter(){
		return new ServerEndpointExporter();
	}
	
}
