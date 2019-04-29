package com.project.spring.dao;

import java.util.List;

import com.project.spring.model.BroadCast;
import com.project.spring.model.TrainVideo;

public interface BroadcastDao  
{
	public BroadCast getDetailTraining(int broadcastId);
	
	public List<BroadCast> list();
	
	public BroadCast checkRoomname(String roomName);
	
	public  int createorUpdate(BroadCast broadCast);
	
	public int deleteDetailTraining(int broadcastId);
	
	public int disableTraining(int broadcastId);
	
	public int returnBroadCastId(String roomname);
	
	public int saveTrainVideo(TrainVideo trainVideo);
	
	public TrainVideo getTrainVideoDetail(int videoId);
	public List<TrainVideo> listTrainVideo();
	
	

}
