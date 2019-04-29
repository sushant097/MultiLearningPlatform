package com.project.spring.dao;

import java.util.List;

import com.project.spring.model.Upload;

public interface UploadDao 
{
	public int uploadNote(Upload upload);
	

	
	
	public List<Upload> list();
	
	public List<Upload> getNoteByPage(int pageId, int total);
	
	public int countTotalUploadNote();
	
	public Upload getDetail(int upId);
	
	public List<Upload> searchNote(String searchKey);
	public void incrementOneVote(int upId);
	
	public void decrementOneVote(int upId);
	
	public int validateNote(int upId);
	
	public int disableNote(int upId);
	public int deleteNote(int upId);
	

}
