package com.project.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController 
{
	
	@GetMapping("/")
	public String loginPage() {
		return "redirect:/homePage";
	}
	
	@GetMapping("/homePage")
	public ModelAndView homePage(ModelAndView model,HttpSession session) {
		
		if(session.getAttribute("email") != null) {
			String type = (String)session.getAttribute("type");
			if(type.equalsIgnoreCase("teacher")) {
				model.addObject("liveCast","Create Live Training");
				model.addObject("liveCastUrl","createLiveTraining");
			}else if(type.equalsIgnoreCase("admin")) {
				model.addObject("adminUrl","checkMaterial");
				
			}else {
				model.addObject("liveCast","Attend Live Training");
				model.addObject("liveCastUrl","attendLiveTraining");
			}
		
		
			model.setViewName("home");
			
			return model;
		}else {
			return new ModelAndView("redirect:/login");
		}
		
		
	}
	
	@GetMapping("/playVideo")
	public String playVideo() {
		return "videoPlay";
	}
	
	
}
