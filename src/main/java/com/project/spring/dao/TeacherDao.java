package com.project.spring.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.project.spring.model.Teacher;

public interface TeacherDao 
{
	public ModelAndView save(Teacher teacher);
	
	public Teacher getTeacher(int teacherId);
	
	public List<Teacher> listTeacher();
	
	//public void upload();
	
	//public void download();
	
	
}
