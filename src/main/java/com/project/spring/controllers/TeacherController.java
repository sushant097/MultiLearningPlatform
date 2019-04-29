package com.project.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.TeacherDao;
import com.project.spring.model.Teacher;

@Controller
public class TeacherController 
{
	
	@Autowired
	private TeacherDao teacherDao;
	
	@RequestMapping(value="/signupTeacher",method=RequestMethod.GET)
	public ModelAndView listTeacher(ModelAndView model) {
		//List<Teacher> list = teacherDao.listTeacher();
		Teacher teacher = new  Teacher();
		model.addObject("teacher", teacher);
		model.setViewName("registerTeacher");
		return model;
	}
	
	@RequestMapping(value="/getTeacher")
	public void getTeacher(@RequestParam("id") int teacherId) {
		Teacher  teacher = teacherDao.getTeacher(teacherId);
		System.out.println("ID: "+teacher.getId());
		System.out.println("Name: "+teacher.getName());
		System.out.println("Colleges: "+teacher.getColzName());
		System.out.println("Faculty:"+ teacher.getFaculty());
	}
	
	@RequestMapping(value="/registerTeacher",method=RequestMethod.POST)
	public ModelAndView saveTeacher(@ModelAttribute Teacher teacher,
			BindingResult result, ModelMap model,ModelAndView mv ) {
		if(result.hasErrors()) {
			return new ModelAndView("error");
		}
		mv = teacherDao.save(teacher);
		return mv;
		
		
	}
	
}
