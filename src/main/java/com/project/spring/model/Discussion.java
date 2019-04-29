package com.project.spring.model;

import java.sql.Date;

public class Discussion 
{
	// Total 10 fields
	private int disId;
	private String email;
	private String name;
	private String title;
	private String question;
	private int disStatus; // for validation of vote 0- nothing, 1- upvote, 2- downvote
	private int vote;
	private String relatedTo[];
	private int noOfAnswer; // directly display the number of answer by counting from database; Remove it
	private Date date;
	
	public int getDisId() {
		return disId;
	}
	public void setDisId(int disId) {
		this.disId = disId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDisStatus() {
		return disStatus;
	}
	public void setDisStatus(int disStatus) {
		this.disStatus = disStatus;
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
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getNoOfAnswer() {
		return noOfAnswer;
	}
	public void setNoOfAnswer(int noOfAnswer) {
		this.noOfAnswer = noOfAnswer;
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
