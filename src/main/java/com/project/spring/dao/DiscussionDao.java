package com.project.spring.dao;

import java.util.List;

import com.project.spring.model.DisAnsStatus;
import com.project.spring.model.Discussion;


public interface DiscussionDao 
{
	public void createOrUpdateDiscussion(Discussion discussion);
	
	public Discussion getDiscussion(int id);
	
	public List<Discussion> list();
	
	public List<Discussion>getDiscussionByPage(int pageId, int total);
	
	public List<Discussion>searchDiscussion(String searchKey);
	
	public List<Discussion>searchDiscussionTitle(String searchKey);
	
	public int upVote(DisAnsStatus disAnsStatus);
	
	public int downVote(DisAnsStatus disAnsStatus);
	

}
