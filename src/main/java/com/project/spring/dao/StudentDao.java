package com.project.spring.dao;



import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.project.spring.model.Student;

public interface StudentDao 
{
	//public void upload();
	
	public ModelAndView save(Student student);
	
	//public void addDiscussion();
	
	//public void download();
	
	public Student get(int studentId);
	
	public List<Student> listStudent();

}
