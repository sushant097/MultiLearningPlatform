package com.project.spring.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.spring.model.Discussion;
import com.project.spring.model.DiscussionAnswer;


public class DiscussionAnswerDaoImp implements DiscussionAnswerDao {

	private JdbcTemplate jdbcTemplate;
	
	public  DiscussionAnswerDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void createUpdateanswerDiscussion(DiscussionAnswer  discussionAns) {
		int status =0;
		
		if(discussionAns.getAnsId()>0) {
			String sql = "UPDATE discussionAnswer"
					     + " SET answer=?"
					     + " WHERE ansId=?";
			status = jdbcTemplate.update(sql,discussionAns.getAnswer(),discussionAns.getAnsId());
		}else {
			
			// how to check that id of discussionAnswer is valid 
			String sql = "INSERT INTO discussionAnswer"
						+ " (disId, email, name , answer, type)"
						+ " VALUES (?,?,?,?,?)";
			
			String increaseAnswerOfDscussion = "UPDATE discussion"
											  + " SET noOfAnswer = noOfAnswer + 1"
											  + " WHERE disID=?";
			status = jdbcTemplate.update(sql,discussionAns.getDisId(),discussionAns.getEmail(),
					discussionAns.getName(), discussionAns.getAnswer(), discussionAns.getType());
			
			if(status>0) {
				//disId must be set 
				int status2= jdbcTemplate.update(increaseAnswerOfDscussion,
						  discussionAns.getDisId());
				if(status2>0) System.out.println("NoOfAnswer increase of disId Sucessfull!");
				else System.out.println("NoOfAnswer increase Failed!");
			}
			
		}
		if(status>0) {
			System.out.println("Record Sucessfully processed!");
		}else {
			System.out.println("Failed to perform operations");
		}
		
	}

	@Override
	public DiscussionAnswer getAnswer(int ansId) {
		String sql = "SELECT * FROM discussionAnswer where ansId="+ansId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DiscussionAnswer>() {

			@Override
			public DiscussionAnswer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					DiscussionAnswer disAnswer = new DiscussionAnswer();
					disAnswer.setAnsId(rs.getInt("ansId"));
					disAnswer.setDisId(rs.getInt("disId"));
					disAnswer.setEmail(rs.getString("email"));
					disAnswer.setName(rs.getString("name"));
					disAnswer.setType(rs.getString("type"));
					disAnswer.setAnswer(rs.getString("answer"));
					disAnswer.setVote(rs.getInt("vote"));
					disAnswer.setDate(rs.getDate("ansDate"));
					return disAnswer;
				}else {
					System.out.println("No any record Found!");
					return null;
				}
				
			}
			
		});

	}

	@Override
	public List<DiscussionAnswer> list(int disId) {
		String sql = "SELECT * FROM discussionAnswer"
					+ " where disId ="+disId;
		
		List<DiscussionAnswer> list = jdbcTemplate.query(sql, new RowMapper<DiscussionAnswer>() {
		
		
			@Override
			public DiscussionAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
				DiscussionAnswer disAnswer = new DiscussionAnswer();
				disAnswer.setAnsId(rs.getInt("ansId"));
				disAnswer.setDisId(rs.getInt("disId"));
				disAnswer.setEmail(rs.getString("email"));
				disAnswer.setName(rs.getString("name"));
				disAnswer.setType(rs.getString("type"));
				disAnswer.setAnswer(rs.getString("answer"));
				disAnswer.setVote(rs.getInt("vote"));
				disAnswer.setDate(rs.getDate("ansDate"));
				return disAnswer;
				
			}
		});
		return list;
	}
	@Override
	public void upDownAnsVote(int ansId) {
		String sql ="SELECT ansStatus FROM discussionAnswer"
				+ " where  	ansId="+ansId;
		
	}
	/*@Override
	public String encodeDiscussionId(int disId) {
		
		String tempEn = disId + "";
		String encryptNum ="";
		for(int i=0;i<tempEn.length();i++) {
			int a = (int)tempEn.charAt(i);
			a+=1481;
			encryptNum +=a;
		}
		return encryptNum;
	}*/
	@Override
	public DiscussionAnswer checkUniqueAnswerDiscussion(int disId, String email) {
		
		String sql = "SELECT email,name,ansId "
				+ 	 " FROM discussionAnswer WHERE disId="+disId+" and email='"+email+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<DiscussionAnswer>() {

			@Override
			public DiscussionAnswer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					DiscussionAnswer disAnswer = new DiscussionAnswer();
					disAnswer.setName(rs.getString("name"));
					disAnswer.setEmail(rs.getString("email"));
					System.out.println(rs.getString("email"));
					System.out.println(rs.getString("ansId"));
					System.out.println(rs.getString("name"));
					return disAnswer;
					
				}
				return null;
			}
			
		});
		
		
	}
	
	

	

}
