package com.project.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.spring.model.Upload;

public class UploadDaoImp implements UploadDao
{
	private JdbcTemplate jdbcTemplate;
	
	public UploadDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int uploadNote(Upload upload) {
		int status = 0;
		
		if(upload.getUploadId()>0) {
			//update
		}else {
			// set the path here before upload
			String sql = "INSERT INTO UPLOADMATERIALS " + 
					"(email,extension,name,type,title,description,fileName,relatedTo) " + 
					"VALUES(?,?,?,?,?,?,?,?);";
			String relatedToString = Upload.listToString(upload.getRelatedTo());
			status = jdbcTemplate.update(sql,upload.getEmail(),upload.getExtension(),
					upload.getName(),upload.getType(),upload.getTitle(),
					upload.getDescription(),upload.getFileName(),relatedToString);
		}
		
		if(status > 0) {
			System.out.println("Sucessfully Upload Note");
			return status;
		}else 
			{System.out.println("Upload Failed!"); 
				return 0;
			}
		
		
	}

	@Override // only used by admin
	public List<Upload> list() {
		
		String sql = "SELECT * "
				+ 	 " FROM UPLOADMATERIALS";
		List<Upload> list = jdbcTemplate.query(sql, new RowMapper<Upload>() {

			@Override
			public Upload mapRow(ResultSet rs, int rowNum) throws SQLException {
				
					Upload upload = new Upload();
					upload.setUploadId(rs.getInt("upload_id"));
					upload.setEmail(rs.getString("email"));
					upload.setTitle(rs.getString("title"));
					upload.setName(rs.getString("name"));
					upload.setExtension(rs.getString("extension"));
					upload.setDescription(rs.getString("description"));
					upload.setDownloadTimes(rs.getInt("download_times"));
					upload.setType(rs.getString("type"));
					upload.setVote(rs.getInt("vote"));
					upload.setValid(rs.getBoolean("valid"));
					upload.setFileName(rs.getString("fileName"));
					upload.setRelatedTo(Upload.stringToList(rs.getString("relatedTo")));
					
					return upload;
			}
			
		});
		return list;
	}

	@Override
	public Upload getDetail(int upId) {
		String sql = "SELECT * "
				+ 	  " FROM uploadmaterials where upload_id="+upId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Upload>() {

			@Override
			public Upload extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Upload upload = new Upload();
					upload.setUploadId(rs.getInt("upload_id"));
					upload.setEmail(rs.getString("email"));
					upload.setTitle(rs.getString("title"));
					upload.setName(rs.getString("name"));
					upload.setExtension(rs.getString("extension"));
					upload.setDescription(rs.getString("description"));
					upload.setDownloadTimes(rs.getInt("download_times"));
					upload.setType(rs.getString("type"));
					upload.setVote(rs.getInt("vote"));
					upload.setValid(rs.getBoolean("valid"));
					upload.setFileName(rs.getString("fileName"));
					upload.setRelatedTo(Upload.stringToList(rs.getString("relatedTo")));
					
					return upload;
				}
				
				System.out.println("Not found any record of UploadId "+upId);
				return null;
				
			}
			
		});
		
	}


	@Override
	public List<Upload> getNoteByPage(int pageId, int total) {
		String sql = "SELECT * "
				+ 	 " FROM UPLOADMATERIALS  WHERE valid=true limit "+(pageId-1)+" , "+total;
		List<Upload> list = jdbcTemplate.query(sql, new RowMapper<Upload>() {
			@Override
			public Upload mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Upload upload = new Upload();
				upload.setUploadId(rs.getInt("upload_id"));
				upload.setEmail(rs.getString("email"));
				upload.setTitle(rs.getString("title"));
				upload.setName(rs.getString("name"));
				upload.setExtension(rs.getString("extension"));
				upload.setDescription(rs.getString("description"));
				upload.setDownloadTimes(rs.getInt("download_times"));
				upload.setType(rs.getString("type"));
				upload.setVote(rs.getInt("vote"));
				upload.setValid(rs.getBoolean("valid"));
				upload.setFileName(rs.getString("fileName"));
				upload.setRelatedTo(Upload.stringToList(rs.getString("relatedTo")));
				
				return upload;
			}
			
		});
		return list;
		
	}


	@Override
	public List<Upload> searchNote(String searchKey) {
		String sql = "SELECT * From uploadmaterials "
				+    "WHERE title LIKE '%"+searchKey+"' "
						+ " AND valid=true";
		List<Upload> list = jdbcTemplate.query(sql, new RowMapper<Upload>() {
			@Override
			public Upload mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Upload upload = new Upload();
				upload.setUploadId(rs.getInt("upload_id"));
				upload.setEmail(rs.getString("email"));
				upload.setTitle(rs.getString("title"));
				upload.setName(rs.getString("name"));
				upload.setExtension(rs.getString("extension"));
				upload.setDescription(rs.getString("description"));
				upload.setDownloadTimes(rs.getInt("download_times"));
				upload.setType(rs.getString("type"));
				upload.setVote(rs.getInt("vote"));
				upload.setValid(rs.getBoolean("valid"));
				upload.setFileName(rs.getString("fileName"));
				upload.setRelatedTo(Upload.stringToList(rs.getString("relatedTo")));
				
				return upload;
			}
			
		});
		return list;
	}
	
	@Override
	public int validateNote(int upId) { 
		int status = 0;
		String sql ="UPDATE uploadmaterials SET valid = true WHERE upload_id="+upId;
		status = jdbcTemplate.update(sql);
		
		if(status>0){
			System.out.println("Sucessfully valid note!");
		}else{System.out.println("Operation failed!");}
		return status;
	}
	public int disableNote(int upId){
		int status = 0;
		String sql ="UPDATE uploadmaterials SET valid=false WHERE upload_id="+upId;
		status = jdbcTemplate.update(sql);
		return status;
	}
	public int deleteNote(int upId){
		int status = 0;
		String sql ="UPDATE uploadmaterials SET valid=false WHERE upload_id="+upId;
		status = jdbcTemplate.update(sql);
		return status;
	}
	@Override
	public int countTotalUploadNote() {
		String sql = "SELECT count(*)  from uploadmaterials";
		int total = (Integer)jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
		
	}
	
	@Override
	public void incrementOneVote(int upId) {
	 
	}

	@Override
	public void decrementOneVote(int upId) {
		
	}



}
