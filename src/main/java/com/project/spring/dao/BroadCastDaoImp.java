package com.project.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.spring.model.BroadCast;
import com.project.spring.model.TrainVideo;

public class BroadCastDaoImp implements BroadcastDao {

	private JdbcTemplate jdbcTemplate;
	
	public BroadCastDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public BroadCast getDetailTraining(int broadCastId) {
		String sql = "SELECT * from broadCastDetail WHERE broadcastId = "+broadCastId;
		return jdbcTemplate.query(sql,new ResultSetExtractor<BroadCast>() {

			@Override
			public BroadCast extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next() && rs.getBoolean("status") == true) {
					BroadCast broadCast = new BroadCast();
					broadCast.setBroadcastId(rs.getInt("broadcastId"));
					broadCast.setEmail(rs.getString("email"));
					broadCast.setRoomname(rs.getString("roomname"));
					broadCast.setTeacherName(rs.getString("teacherName"));
					broadCast.setTitle(rs.getString("title"));
					broadCast.setDescription(rs.getString("description"));
					broadCast.setStatus(rs.getBoolean("status"));
					broadCast.setDate(rs.getDate("liveDatetime"));
					broadCast.setRelatedTo(BroadCast.stringToList(rs.getString("relatedTo")));
					return broadCast;
				}
				System.out.println("No record found!!");
				return null;
			}
		});

	}

	@Override
	public List<BroadCast> list() {
		String sql = "SELECT * from broadCastDetail where status = true";
		List<BroadCast> list = jdbcTemplate.query(sql, new RowMapper<BroadCast>() {

			@Override
			public BroadCast mapRow(ResultSet rs, int rowNum) throws SQLException {
					BroadCast broadCast = new BroadCast();
					broadCast.setBroadcastId(rs.getInt("broadcastId"));
					broadCast.setEmail(rs.getString("email"));
					broadCast.setRoomname(rs.getString("roomname"));
					broadCast.setTeacherName(rs.getString("teacherName"));
					broadCast.setTitle(rs.getString("title"));
					broadCast.setDescription(rs.getString("description"));
					broadCast.setStatus(rs.getBoolean("status"));
					broadCast.setDate(rs.getDate("liveDatetime"));
					broadCast.setRelatedTo(BroadCast.stringToList(rs.getString("relatedTo")));
					return broadCast;
				
			}
			
		});
		return list;
	}

	@Override
	public int createorUpdate(BroadCast broadCast) {
		int status = 0;
		if(broadCast.getBroadcastId()>0) {
			//update
			String sql ="UPDATE  broadCastDetail"
					+ " SET roomname=?, teachername=?,"
					+ " email=?, relatedTo=?,"
					+ " title=?, description=? where broadcastId=?";
			status = jdbcTemplate.update(sql, broadCast.getRoomname(), broadCast.getTeacherName(),
	                 broadCast.getEmail(), BroadCast.listToString(broadCast.getRelatedTo()),
	                 broadCast.getTitle(), broadCast.getDescription(),broadCast.getBroadcastId());
			
		}else {
			//create
			String sql ="INSERT INTO broadCastDetail"
					+ " (roomname, teachername, email, relatedTo, title, description) VALUES(?,?,?,?,?,?)";
			
			status = jdbcTemplate.update(sql, broadCast.getRoomname(), broadCast.getTeacherName(),
					                 broadCast.getEmail(), BroadCast.listToString(broadCast.getRelatedTo()),
					                 broadCast.getTitle(), broadCast.getDescription());
		}
		return status;
	}
	
	public int returnBroadCastId(String roomname){
		String sql = "SELECT broadcastId from broadCastDetail where roomname='"+roomname+"' AND status=true";
		int broadcastId = jdbcTemplate.queryForObject(sql, Integer.class);
		return broadcastId;
	}
	@Override
	public int disableTraining(int broadcastId){
		int status = 0;
		String sql = "UPDATE broadCastDetail set status=false where broadcastId="+broadcastId;
		status = jdbcTemplate.update(sql);
		return status;
	}

	@Override
	public int deleteDetailTraining(int broadcastId) {
		// make status = false
		int status = 0;
		String sql = "DELETE FROM `broadcastdetail` WHERE `broadcastdetail`.`broadcastId`= "+ broadcastId;
		status  = jdbcTemplate.update(sql);
		
		return status;
	}
	@Override
	public BroadCast checkRoomname(String roomname) {
		
		String sql = "SELECT roomname FROM "
				+   "broadcastdetail where roomname ='"+roomname+"'";
		return jdbcTemplate.query(sql,new ResultSetExtractor<BroadCast>() {

			@Override
			public BroadCast extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					BroadCast broadCast = new BroadCast();
					broadCast.setRoomname(rs.getString("roomname"));
					return broadCast;
				}else{
					return null;
				}
				
			}
			
		});
		
		
	}
	
	@Override
	public int saveTrainVideo(TrainVideo trainVideo){
		int status = 0;
		String sql ="INSERT INTO trainvideos"
				+ " (trainId,name,email,title,description,relatedTo,filename)"
				+ " VALUES (?,?,?,?,?,?,?)";
		status = jdbcTemplate.update(sql, trainVideo.getTrainId(),trainVideo.getName(),trainVideo.getEmail(),
				                trainVideo.getTitle(), trainVideo.getDescription(),
				                TrainVideo.listToString(trainVideo.getRelatedTo()),
				                trainVideo.getFileName());
		return status;
	}
	public TrainVideo getTrainVideoDetail(int videoId){
		String sql ="SELECT * from trainvideos where trainId="+videoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<TrainVideo>(){

			@Override
			public TrainVideo extractData(ResultSet rs) throws SQLException, DataAccessException {
				TrainVideo trainVideo = new TrainVideo();
				trainVideo.setVideoId(rs.getInt("videoId"));
				trainVideo.setTrainId(rs.getInt("trainId"));
				trainVideo.setName(rs.getString("name"));
				trainVideo.setEmail(rs.getString("email"));
				trainVideo.setTitle(rs.getString("title"));
				trainVideo.setDescription("description");
				trainVideo.setRelatedTo(TrainVideo.stringToList(rs.getString("relatedTo")));
				trainVideo.setVideoLike(rs.getInt("videoLike"));
				trainVideo.setVideoDislike(rs.getInt("videoDislike"));
				trainVideo.setFileName(rs.getString("filename"));
				trainVideo.setFileExtension(rs.getString("extension"));
				trainVideo.setDate(rs.getDate("date"));
				return trainVideo;
			}
			
		});
	}
	public List<TrainVideo> listTrainVideo(){
		String sql ="SELECT * FROM trainvideos";
		List<TrainVideo>list = jdbcTemplate.query(sql, new RowMapper<TrainVideo>(){

			@Override
			public TrainVideo mapRow(ResultSet rs, int rowNum) throws SQLException {
				TrainVideo trainVideo = new TrainVideo();
				trainVideo.setVideoId(rs.getInt("videoId"));
				trainVideo.setTrainId(rs.getInt("trainId"));
				trainVideo.setName(rs.getString("name"));
				trainVideo.setEmail(rs.getString("email"));
				trainVideo.setTitle(rs.getString("title"));
				trainVideo.setDescription("description");
				trainVideo.setRelatedTo(TrainVideo.stringToList(rs.getString("relatedTo")));
				trainVideo.setVideoLike(rs.getInt("videoLike"));
				trainVideo.setVideoDislike(rs.getInt("videoDislike"));
				trainVideo.setFileName(rs.getString("filename"));
				trainVideo.setFileExtension(rs.getString("extension"));
				trainVideo.setDate(rs.getDate("date"));
				return trainVideo;
			}
			
		});
		return list;
	}

}
