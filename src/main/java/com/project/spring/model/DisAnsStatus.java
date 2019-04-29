package com.project.spring.model;

public class DisAnsStatus {

	private int disAnsStatusId;

	private String email;
	private String category; // discussion or discussionAnswer
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private int status;

	public int getDisAnsStatusId() {
		return disAnsStatusId;
	}

	public void setDisAnsStatusId(int disAnsStatusId) {
		this.disAnsStatusId = disAnsStatusId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
