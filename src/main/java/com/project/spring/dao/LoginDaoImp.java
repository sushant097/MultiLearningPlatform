package com.project.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.model.Login;

public class LoginDaoImp
{
	//how to validate user when sign up see this: 
	//https://stackoverflow.com/questions/10545507/how-to-verify-user-clicked-on-link-in-email-that-i-sent-him-her
	private static JdbcTemplate jdbcTemplate;
	
	public LoginDaoImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public static Login checkLogin(Login login) {
		String email=login.getEmail();
		String pass = login.getPassword();
		
		
		Login loginObject = findUser(email,pass);
		return loginObject;
		
	}
	
	// See this: https://www.youtube.com/watch?v=MQlcLrCrzNc&t=320s
	
	
	private static Login findUser(String email, String pass) {
		
		
		String sql = "SELECT EMAIL,NAME,TYPE,Password " + 
				"    FROM STUDENT" + 
				"    WHERE EMAIL='"+email+"' AND PASSWORD='"+pass+"'" + 
				"    UNION " + 
				"    SELECT EMAIL,NAME,TYPE,Password " + 
				"    FROM TEACHER" + 
				"     WHERE EMAIL='"+email+"' AND PASSWORD='"+pass+"';";
		// do not retrieve password from database and set to session
		return jdbcTemplate.query(sql, new ResultSetExtractor<Login>() {
	
			@Override
			public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Login login = new Login();
					String passwordTemp = rs.getString("password");
					if(pass.equals(passwordTemp)) {
						login.setEmail(rs.getString("email"));
						login.setName(rs.getString("name"));
						login.setType(rs.getString("type"));
						
						return login;
					}
					System.out.println("Password Not Match!");
					return null;
					
				}else {
					System.out.println("Enter Email or Password not match!!");
					return null;
				}
				
			}
			
		});
		
	}

	/*public String logout() {
		return null;
	}*/

}
