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
import com.project.spring.model.Student;

public class StudentDaoImp implements StudentDao
{
	private JdbcTemplate jdbcTemplate;
	
	public StudentDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ModelAndView save(Student student) {
		int status = 0;
		System.out.println(student.toString());
		if(student.getId()>0) {
			//update
		}else {
			String sql = "insert into student (name,email,password,uni_name,"
					+ "colz_name,type,faculty,semester)"
					+ "values(?,?,?,?,?,?,?,?)";
			
			Login login = findUser(student.getEmail());
			
			if(login == null) {
				status  = jdbcTemplate.update(sql, student.getName(),student.getEmail(),student.getPassword(),
						student.getUniName(), student.getColzName(), student.getType(),
						student.getFaculty(),student.getSemester());
			}else {
				System.out.println("This email is either used as Teacher or Student first");
				return new ModelAndView("redirect:/signupStudent","error","<p class='bg-danger'><strong>This Email is Already registered.</strong> Duplicate Email are Not Allowed! Please Try to register with new Email.</p>");
			}

		}
		
		if(status > 0) {
			System.out.println("Insert data Sucessfully");
			return new ModelAndView("redirect:/","error","<p class='bg-success'>Register Sucessfull with email "+student.getEmail()+" Please check your email for login!</p>");
		}else {return new ModelAndView("redirect:/signupStudent","error","<p class='bg-danger'>Register Failed with email "+student.getEmail()+" Please Try Again!</p>");}
		
		
	}

	public Student get(int studentId) {
		String sql = "SELECT * from student where id="+studentId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Student>() {

			public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				if(rs.next()) {
					Student stu = new Student();
					stu.setId(rs.getInt("id"));
					stu.setName(rs.getString("name"));
					stu.setEmail(rs.getString("email"));
					stu.setPassword(rs.getString("password"));
					stu.setUniName(rs.getString("uni_name"));
					stu.setColzName(rs.getString("colz_name"));
					stu.setType(rs.getString("type")); // i think not necessary
					stu.setFaculty(rs.getString("faculty"));
					stu.setSemester(rs.getInt("semester"));
					stu.setDate(rs.getDate("entry_date"));
					return stu;
					
				}
				System.out.println("No any student found of that: ");
				return null;
				
			}
			
		});
		
	}

	public List<Student> listStudent() {
		String sql = "SELECT * FROM STUDENT";
		
		List<Student> listStudent = jdbcTemplate.query(sql, new RowMapper<Student>() {
			
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setEmail(rs.getString("email"));
				stu.setPassword(rs.getString("password"));
				stu.setUniName(rs.getString("uni_name"));
				stu.setColzName(rs.getString("colz_name"));
				stu.setType(rs.getString("type")); // i think not necessary
				stu.setFaculty(rs.getString("faculty"));
				stu.setSemester(rs.getInt("semester"));
				stu.setDate(rs.getDate("entry_date"));
				
				return stu;
			}
			
		});
		
		return listStudent;
		
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
