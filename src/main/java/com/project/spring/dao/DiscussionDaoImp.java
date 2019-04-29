package com.project.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.spring.model.DisAnsStatus;
import com.project.spring.model.Discussion;


public class DiscussionDaoImp implements DiscussionDao
{
	private JdbcTemplate jdbcTemplate;
	
	public DiscussionDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createOrUpdateDiscussion(Discussion discussion){
		
		int status =0;
		String relatedToDiscussion = Discussion.listToString(discussion.getRelatedTo());
		if(discussion.getDisId() >0) {
			//update discussion
						String sql = "UPDATE discussion SET  "
								+ "title= ?,question=?,relatedTo=?"
								+"WHERE disId=?";
						status = jdbcTemplate.update(sql,discussion.getTitle(),discussion.getQuestion(),
								relatedToDiscussion,discussion.getDisId());
						System.out.println("RelatedTo In String: "+relatedToDiscussion);
						if(status >0) {
							System.out.println("Record sucessfully Updated!");
						}
			
			
		}else {
			// create discussion
			String sql = "INSERT INTO discussion (name,email,title,question,relatedTo)"
					+ "VALUES(?,?,?,?,?)";
				
			
			status = jdbcTemplate.update(sql,discussion.getName(),discussion.getEmail(),discussion.getTitle(),discussion.getQuestion(),relatedToDiscussion);
			System.out.println("Record sucessfully saved!");
		}
		
		
	}

    @Override
	public Discussion getDiscussion(int disId) {
		String sql = "Select * from discussion where disId="+disId;
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Discussion>() {

			@Override
			public Discussion extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Discussion discussion = new Discussion();
					discussion.setDisId(rs.getInt("disId"));
					discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
					discussion.setEmail(rs.getString("email")); // only id of student
					discussion.setName(rs.getString("name"));
					discussion.setTitle(rs.getString("title"));
					discussion.setQuestion(rs.getString("question"));
					discussion.setRelatedTo(Discussion.stringToList(rs.getString("relatedTo")));
					discussion.setVote(rs.getInt("vote"));
					discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
					discussion.setDate(rs.getDate("quesDate"));
					return discussion;
				}
				System.out.println("No any Result found! of "+disId);
				return null;
			}
			
		});
		
	}



	@Override
	public List<Discussion> list() {
		String sql = "SELECT * FROM discussion ";
		
		List<Discussion> list =  jdbcTemplate.query(sql, new RowMapper<Discussion>() {

			@Override
			public Discussion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Discussion discussion = new Discussion();
				
				discussion.setDisId(rs.getInt("disId"));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setEmail(rs.getString("email")); // only id of student				
				discussion.setName(rs.getString("name"));
				discussion.setTitle(rs.getString("title"));
				discussion.setQuestion(rs.getString("question"));
				discussion.setRelatedTo(Discussion.stringToList(rs.getString("relatedTo")));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setVote(rs.getInt("vote"));
				discussion.setDate(rs.getDate("quesDate"));
				
				return discussion;
			}
		});
		return list;
		
	}
	
	
	public List<Discussion> searchDiscussion(String searchKey) {
		String sql = "SELECT * FROM discussion "
				+ 	  "WHERE title LIKE '%"+searchKey+"%'";
		
		List<Discussion> list =  jdbcTemplate.query(sql, new RowMapper<Discussion>() {

			@Override
			public Discussion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Discussion discussion = new Discussion();
				
				discussion.setDisId(rs.getInt("disId"));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setEmail(rs.getString("email")); // only id of student				
				discussion.setName(rs.getString("name"));
				discussion.setTitle(rs.getString("title"));
				discussion.setQuestion(rs.getString("question"));
				discussion.setRelatedTo(Discussion.stringToList(rs.getString("relatedTo")));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setVote(rs.getInt("vote"));
				discussion.setDate(rs.getDate("quesDate"));
				
				return discussion;
			}
			
			
		});
		return list;
	}
	
	public List<Discussion>searchDiscussionTitle(String searchKey){
		String sql = "SELECT * FROM discussion "
				+ 	  "WHERE title LIKE '%"+searchKey+"%'";
		
		List<Discussion> list =  jdbcTemplate.query(sql, new RowMapper<Discussion>() {

			@Override
			public Discussion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Discussion discussion = new Discussion();
				
				discussion.setDisId(rs.getInt("disId"));		
				discussion.setName(rs.getString("name"));
				discussion.setTitle(rs.getString("title"));
				discussion.setRelatedTo(Discussion.stringToList(rs.getString("relatedTo")));
				return discussion;
			}
			
			
		});
		return list;
	}

	
	private DisAnsStatus getStatus(int id) {
		String sql = "SELECT disAnsStatusId, "
				+ "email, status FROM discussionAnswerStatus WHERE disAnsStatusId="+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DisAnsStatus>() {

			@Override
			public DisAnsStatus extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					DisAnsStatus disAnsStatus = new DisAnsStatus();
					disAnsStatus.setDisAnsStatusId(rs.getInt("disAnsStatusId"));
					disAnsStatus.setEmail(rs.getString("email"));
					disAnsStatus.setStatus(rs.getInt("status"));
					return disAnsStatus;
				}
				return null;
			}
			
		});
		
	}

	@Override
	public int upVote(DisAnsStatus disAnsStatus) {
		String sql = null;
		int status =0;
		int status2 =0;
		int id = disAnsStatus.getDisAnsStatusId();
		int statusId = getStatus(id).getDisAnsStatusId();
		String toDo = "";
		if(getStatus(id)==null) {
			//insert
			sql = "INSERT INTO discussionAnswerStatus (disAnsStatusId, email,status) VALUES(?,?,?)";
			 status = jdbcTemplate.update(sql,disAnsStatus.getDisAnsStatusId()
									,disAnsStatus.getEmail(),1);// 1 for upVote for that id
			
		}else {
			if(statusId==1){
				//upvote
				sql = "UPDATE  discussionAnswerStatus SET status = "+0
						+ " WHERE disAnsStatusId="+id;
				toDo="-"; //vote--
				
			}else if(statusId==2){
				//downvote
				sql = "UPDATE  discussionAnswerStatus SET status = "+0
						+ " WHERE disAnsStatusId="+id;
				toDo="+";//vote++
			}else{
				sql = "UPDATE  discussionAnswerStatus SET status = "+1
						+ " WHERE disAnsStatusId="+id;  // only statusId==0 then SetTO UpVoted
				toDo="+";
			}
			status  = jdbcTemplate.update(sql);
		}
		//after insert or update in discussionAnswerStatus
		if(status>0) {
			//update
			
			if(disAnsStatus.getCategory().equals("discussion")) {
				String sqlT = "";
				if(toDo.equals("+")){
					sqlT = "UPDATE discussion SET vote = vote +1"
							+ " WHERE disId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}else if(toDo.equals("-")){
					sqlT = "UPDATE discussion SET vote = vote - 1"
							+ " WHERE disId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}
				
				 
				if(status2 >0 && toDo.equals("-")) {System.out.println("Upvote discussion sucessfull with decrement vote!");}
				if(status2 >0 && toDo.equals("+")) {System.out.println("Upvote discussion sucessfull with increment vote!");}
				
			}else if(disAnsStatus.getCategory().equals("discussionAnswer")) {
				String sqlT ="";
				if(toDo.equals("+")){
					sqlT = "UPDATE discussionanswer SET vote = vote +1"
							+ " WHERE ansId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}else if(toDo.equals("-")){
					sqlT = "UPDATE discussionanswer SET vote = vote - 1"
							+ " WHERE ansId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}
				
				 
				if(status2 >0 && toDo.equals("-")) {System.out.println("Upvote discussionAnswer successfull with decrement vote!");}
				if(status2 >0 && toDo.equals("+")) {System.out.println("Upvote discussionAnswer successfull with increment vote!");}

			}
		}
		return status2;
	}

	
	@Override
	public int downVote(DisAnsStatus disAnsStatus) {
		String sql = null;
		int status =0;
		int status2 = 0;
		int id = disAnsStatus.getDisAnsStatusId();// discussionId or discussionAnswerId
		int statusId = getStatus(id).getDisAnsStatusId();
		String toDo = "";
		if(getStatus(id)==null) {
			//insert
			sql = "INSERT INTO discussionAnswerStatus (disAnsStatusId, email,status) VALUES(?,?,?)";
			 status = jdbcTemplate.update(sql,disAnsStatus.getDisAnsStatusId()
									,disAnsStatus.getEmail(),1);// 1 for upVote for that id
			
		}else {
			if(statusId==1){
				//upvote
				sql = "UPDATE  discussionAnswerStatus SET status = "+0
						+ " WHERE disAnsStatusId="+id;
				toDo="-"; //vote--
				
			}else if(statusId==2){
				//downvote
				sql = "UPDATE  discussionAnswerStatus SET status = "+0
						+ " WHERE disAnsStatusId="+id;
				toDo="+";//vote++
			}else{
				sql = "UPDATE  discussionAnswerStatus SET status = "+1
						+ " WHERE disAnsStatusId="+id;  // only statusId==0 then SetTO UpVoted
				toDo="-";
			}
			status  = jdbcTemplate.update(sql);
		}
		//after insert or update in discussionAnswerStatus
		if(status>0) {
			//update
			
			if(disAnsStatus.getCategory().equals("discussion")) {
				String sqlT = "";
				if(toDo.equals("+")){
					sqlT = "UPDATE discussion SET vote = vote +1"
							+ " WHERE disId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}else if(toDo.equals("-")){
					sqlT = "UPDATE discussion SET vote = vote - 1"
							+ " WHERE disId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}
				
				 
				if(status2 >0 && toDo.equals("-")) {System.out.println("Downvote discussion sucessfull with decrement vote!");}
				if(status2 >0 && toDo.equals("+")) {System.out.println("Downvote discussion sucessfull with increment vote!");}
				
			}else if(disAnsStatus.getCategory().equals("discussionAnswer")) {
				String sqlT ="";
				if(toDo.equals("+")){
					sqlT = "UPDATE discussionanswer SET vote = vote +1"
							+ " WHERE ansId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}else if(toDo.equals("-")){
					sqlT = "UPDATE discussionanswer SET vote = vote - 1"
							+ " WHERE ansId="+id;
					status2 = jdbcTemplate.update(sqlT);
				}
				
				 
				if(status2 >0 && toDo.equals("-")) {System.out.println("Downvote discussionAnswer successfull with decrement vote!");}
				if(status2 >0 && toDo.equals("+")) {System.out.println("Downvote discussionAnswer successfull with increment vote!");}

			}
		}
		return status2;
		
	}

	@Override
	public List<Discussion> getDiscussionByPage(int pageId, int total) {
		String sql = "SELECT * FROM discussion "
				+    "limit "+(pageId-1)+" , "+ total ;
		
		List<Discussion> list =  jdbcTemplate.query(sql, new RowMapper<Discussion>() {

			@Override
			public Discussion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Discussion discussion = new Discussion();
				
				discussion.setDisId(rs.getInt("disId"));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setEmail(rs.getString("email")); // only id of student				
				discussion.setName(rs.getString("name"));
				discussion.setTitle(rs.getString("title"));
				discussion.setQuestion(rs.getString("question"));
				discussion.setRelatedTo(Discussion.stringToList(rs.getString("relatedTo")));
				discussion.setNoOfAnswer(rs.getInt("noOfAnswer"));
				discussion.setVote(rs.getInt("vote"));
				discussion.setDate(rs.getDate("quesDate"));
				
				return discussion;
			}
			
			
		});
		return list;
	}
	

}
