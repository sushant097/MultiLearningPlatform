package com.project.spring.model;

import java.util.Date;

public class Teacher 
{
	private int id;
	private String name;
	private String email;
	private String password;
	private String type;
	private String uniName;
	private String[] colzName;
	private String[] faculty;
	private Date date; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUniName() {
		return uniName;
	}
	public void setUniName(String uniName) {
		this.uniName = uniName;
	}
	
	public String[] getColzName() {
		return colzName;
	}
	public void setColzName(String[] colzName) {
		this.colzName = colzName;
	}
	public String[] getFaculty() {
		return faculty;
	}
	public void setFaculty(String[] faculty) {
		this.faculty = faculty;
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
