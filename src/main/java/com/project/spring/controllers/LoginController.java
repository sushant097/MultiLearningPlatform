package com.project.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.LoginDaoImp;
import com.project.spring.model.Login;

@Controller
public class LoginController 
{
//	@Autowired
//	private LoginDaoImp loginDao;
	
	@GetMapping("/login")
	public ModelAndView loginFirst() {
		
		Login loginObject = new Login();
		return new ModelAndView("login","login",loginObject);
	}
	
	@PostMapping("/userLogin")
	public ModelAndView checkLoginData(@ModelAttribute("login") Login login,
			BindingResult result,ModelAndView model,  HttpSession session) {
		
		if(result.hasErrors()) {
			//return new ModelAndView("loginError");
		}
		Login loginObject = LoginDaoImp.checkLogin(login);
		
		if(loginObject == null) {
			return new ModelAndView("login","error","<p class='bg-danger'><strong>Incorrect Email or Password.</strong> Please Try Again!.</p>");// return to login
		}else {
			session.setAttribute("name", loginObject.getName());
			session.setAttribute("email", loginObject.getEmail());
			session.setAttribute("type", loginObject.getType());
			
			return new ModelAndView("redirect:/homePage");
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("type");
		session.removeAttribute("username");
		session.removeAttribute("roomname");
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/signup")
	public String signup(@RequestParam("type") String type) {
		
		return null;
	}

}
