package com.project.spring.model;

import java.util.Date;

public class Student
{
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String uniName;
	private String colzName;
	private String type;// i think it is not necessary since all student as type student
	private String faculty;
	private int semester;
	private Date date;
	
	// total ten attributes
	
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
	public String getUniName() {
		return uniName;
	}
	public void setUniName(String uniName) {
		this.uniName = uniName;
	}
	public String getColzName() {
		return colzName;
	}
	public void setColzName(String colzName) {
		this.colzName = colzName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", uniName="
				+ uniName + ", colzName=" + colzName + ", type=" + type + ", faculty=" + faculty + ", semester="
				+ semester + ", date=" + date + "]";
	}
}
	