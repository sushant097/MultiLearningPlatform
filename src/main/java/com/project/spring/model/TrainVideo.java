package com.project.spring.model;

import java.sql.Date;

public class TrainVideo 
{
	private int videoId;
	private int trainId;// broadcast Id
	private String name;
	private String email;
	private String title;
	private String description;
	private String relatedTo[];
	private int videoLike;
	private int videoDislike;
	private String fileName;
	private String fileExtension;
	
	private Date date;



	
	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(String[] relatedTo) {
		this.relatedTo = relatedTo;
	}

	public int getVideoLike() {
		return videoLike;
	}

	public void setVideoLike(int videoLike) {
		this.videoLike = videoLike;
	}

	public int getVideoDislike() {
		return videoDislike;
	}

	public void setVideoDislike(int videoDislike) {
		this.videoDislike = videoDislike;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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
