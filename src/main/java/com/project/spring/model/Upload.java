package com.project.spring.model;

public class Upload 
{
	private int uploadId;
	private String email; // can be student or teacher
	private String title; // title of upload Materials
	private String extension ;// extension of the file to be saved
	private String description;// description of the materials
	private int downloadTimes; // how many it is download
	private String type; // to find out the teacher or student
	private int vote;// like by the other
	private boolean valid;   // to verify by admin or not
	private String fileName;
	private String relatedTo[];
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUploadId() {
		return uploadId;
	}
	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDownloadTimes() {
		return downloadTimes;
	}
	public void setDownloadTimes(int downloadTimes) {
		this.downloadTimes = downloadTimes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		
		//System.out.println("listToString:as size: "+name.length+": "+nameString);
		return nameString;
		
	}
	
	public static String[] stringToList(String name) {
		//System.out.println("name: "+name);
		String[] listOfNames = name.split(",");
		//System.out.println("stringToList "+listOfNames);
		
		return listOfNames;
	}
	
	
}