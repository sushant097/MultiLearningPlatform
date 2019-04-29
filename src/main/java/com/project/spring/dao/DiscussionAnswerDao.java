package com.project.spring.dao;

import java.util.List;

import com.project.spring.model.DiscussionAnswer;

public interface DiscussionAnswerDao 
{
	public void createUpdateanswerDiscussion(DiscussionAnswer discussionAns);
	
	public DiscussionAnswer getAnswer(int ansId); // for editing
	
	public List<DiscussionAnswer> list(int disid); // list all the answer on 
	// 									the basis  of disId
	
	public DiscussionAnswer checkUniqueAnswerDiscussion(int disId,String email);
	public void upDownAnsVote(int ansId);
	
	
}
