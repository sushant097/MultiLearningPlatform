package com.project.spring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.StudentDao;
import com.project.spring.model.Student;

@Controller
public class StudentController
{
	@Autowired
	private StudentDao studentDao ;
	
	
	
	@RequestMapping(value="/signupStudent")
	public ModelAndView getRequest(ModelAndView model) {
		Student student  = new Student();
		model.addObject("student",student);
		model.setViewName("registerStudent");
		return model;
		
	}
	@RequestMapping(value="/registerStudent", method= RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Student student,
			BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			return new ModelAndView("error");
		}
		System.out.println(student.toString());
		 mv = studentDao.save(student); 
		System.out.println("Sucessfully Saved!");
		
		return mv;
	}
	
	@RequestMapping(value="/editStudent",method=RequestMethod.GET)
	public ModelAndView editStudent(@RequestParam("id") int studentId) {
		Student student = studentDao.get(studentId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registerStudent");
		mv.addObject("student", student);
		return mv;
		
	}
}
