package com.project.spring.model;

import java.sql.Date;

public class BroadCast 
{
	private int broadcastId;
	private String teacherName;
	private String email;
	private String[] relatedTo;
	private String description;
	private String roomname;
	private String title;
	private Date date;
	
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getBroadcastId() {
		return broadcastId;
	}
	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(String[] relatedTo) {
		this.relatedTo = relatedTo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static String listToString(String[] name) {
		String nameString = "";
		if(name.length>2) {
			int i;
			for(i=0;i<name.length-1;i++) {
				nameString +=name[i];
				nameString +=",";
				
			}
			nameString += name[i];
		}
		else if(name.length==2) {
			nameString += name[0] + "," + name[1];
		}
		else if(name.length ==1) {
			nameString = name[0];
		}
		
		System.out.println("listToString:as size: "+name.length+": "+nameString);
		return nameString;
		
	}
	
	public static String[] stringToList(String name) {
		System.out.println("name: "+name);
		String[] listOfNames = name.split(",");
		System.out.println("stringToList "+listOfNames);
		
		return listOfNames;
	}

}
