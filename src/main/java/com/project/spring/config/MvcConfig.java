package com.project.spring.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.project.spring.dao.BroadCastDaoImp;
import com.project.spring.dao.DiscussionAnswerDaoImp;
import com.project.spring.dao.DiscussionDaoImp;
import com.project.spring.dao.LoginDaoImp;
import com.project.spring.dao.StudentDaoImp;
import com.project.spring.dao.TeacherDaoImp;
import com.project.spring.dao.UploadDaoImp;
import com.project.spring.websocket.ChatEndpointNew;
import com.project.spring.websocket.LiveStreamEndpoint;

@Configuration 
@EnableWebMvc
@ComponentScan(basePackages={"com.project.spring.controllers"})//
public class MvcConfig implements WebMvcConfigurer
{
	@Bean
	public InternalResourceViewResolver resolver() {
		 
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
		
		registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");
		
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(20971520*10);   // 20MB
	    multipartResolver.setMaxInMemorySize(10485760);  // 10MB
	    return multipartResolver;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");//com.mysql.jdbc.Driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/project_db");//?allowPublicKeyRetrieval=true&useSSL=false 
		//allowPublicKeyRetrieval=true
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
    
//	@Bean(name = "mailProperties")
//	public PropertiesFactoryBean mapper() {
//	    PropertiesFactoryBean bean = new PropertiesFactoryBean();
//	    bean.setLocation(new ClassPathResource("mail.properties"));
//	    return bean;
//	}
	
	@Bean
	public StudentDaoImp getStudentDao() {
		return new StudentDaoImp(getDataSource());
	}
	@Bean
	public TeacherDaoImp getTeacherDao() {
		return new TeacherDaoImp(getDataSource());
	}
	@Bean
	public DiscussionDaoImp getDiscussionDao() {
		return new DiscussionDaoImp(getDataSource());
	}
	@Bean
	public DiscussionAnswerDaoImp getDisscussionAnswerDao() {
		return new DiscussionAnswerDaoImp(getDataSource());
	}
	@Bean 
	public LoginDaoImp getLoginDao() {
		return new LoginDaoImp(getDataSource());
	}
	@Bean
	public UploadDaoImp getuploadDaoImp() {
		return new UploadDaoImp(getDataSource());
	}
	
	@Bean 
	public BroadCastDaoImp getBroadCastDaoImp() {
		return new BroadCastDaoImp(getDataSource());
	}
	
	
}
