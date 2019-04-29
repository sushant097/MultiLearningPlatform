package com.project.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.model.Login;
import com.project.spring.model.Teacher;

public class TeacherDaoImp implements TeacherDao {
	
	
	private JdbcTemplate jdbcTemplate;

	public TeacherDaoImp(DataSource dataSource) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ModelAndView save(Teacher teacher) {
		int status = 0;
		ModelAndView model = new ModelAndView();
		if(teacher.getId() >0) {
			//update
		}
		else {
			//insert
			String sql = "INSERT INTO teacher(name,email,password,type,uni_name," 
					+ "colz_name,faculty) values(?,?,?,?,?,?,?)"; // id is default
		String colz_name = Teacher.listToString(teacher.getColzName());
		String faculty = Teacher.listToString(teacher.getFaculty());
		
		Login checkValidity = findUser(teacher.getEmail());
		if(checkValidity == null) {
			status  = jdbcTemplate.update(sql,teacher.getName(),teacher.getEmail(),teacher.getPassword(),
					teacher.getType(),teacher.getUniName(),colz_name,faculty);
		}else {
			System.out.println("Invalid Email, this email is already used as Teacher Or Student!");
			model.setViewName("registerTeacher");
			model.addObject("error","<p class='bg-danger'><strong>This Email is Already registered.</strong> Duplicate Email are Not Allowed! Please Try to register with new Email.</p>");
			return model;
			}
		}
		
		if(status > 0) {
			System.out.println("Insert data Successfully");
			model.setViewName("redirect:/");
			model.addObject("error","<p class='bg-success'>Register Sucessfull with email "+teacher.getEmail()+" Please check your email for login!</p>");
			return model;
			//Also show the message in the page
		}else {
			model.setViewName("registerTeacher");
			model.addObject("error","<p class='bg-danger'>Register Failed with email "+teacher.getEmail()+" Please Try Again!</p>");
			return model;
			}
	}

	

	public Teacher getTeacher(int teacherId) {
		String sql = "Select * from teacher where id="+teacherId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Teacher>(){

			public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Teacher teacher = new Teacher();
					
					teacher.setId(rs.getInt("id"));
					teacher.setName(rs.getString("name"));
					teacher.setEmail(rs.getString("email"));
					teacher.setPassword(rs.getString("password"));
					teacher.setUniName(rs.getString("uni_name"));
					teacher.setColzName(Teacher.stringToList(rs.getString("colz_name")));
					teacher.setFaculty(Teacher.stringToList(rs.getString("faculty")));
					teacher.setDate(rs.getDate("entry_date"));
					
					return teacher;
				}
				System.out.println("No any Teacher found!");
				return null;
			}
			
		});
		
	}

	public List<Teacher> listTeacher() {
		String sql = "SELECT * from teacher";
		List<Teacher> listTeacher = jdbcTemplate.query(sql, new RowMapper<Teacher>() {
			
			
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setName(rs.getString("name"));
				teacher.setEmail(rs.getString("email"));
				teacher.setPassword(rs.getString("password"));
				teacher.setUniName(rs.getString("uni_name"));
				teacher.setColzName(Teacher.stringToList(rs.getString("colz_name")));
				teacher.setFaculty(Teacher.stringToList(rs.getString("faculty")));
				teacher.setDate(rs.getDate("entry_date"));
				
				return teacher;
			}
			
		});
		return listTeacher;
	}
	
	private Login findUser(String email) {
		String sql ="SELECT EMAIL,NAME,TYPE " + 
					"    FROM STUDENT" + 
					"    WHERE EMAIL='"+email+"'" + 
					"    UNION		" + 
					"    SELECT EMAIL,NAME,TYPE " + 
					"    FROM TEACHER" + 
					"     WHERE EMAIL='"+email+"';";
		
		return  jdbcTemplate.query(sql, new ResultSetExtractor<Login>() {

			@Override
			public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				if(rs.next()) {
					Login login = new Login();
					login.setEmail(rs.getString(email));
					return login;
				}
				return null;
			}
			
		});
		
	}
	

}
