package com.project.spring.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.UploadDao;
import com.project.spring.model.Upload;

@Controller
public class AdminController
{
	@Autowired
	private UploadDao uploadDao;
	
	@GetMapping("verifyNote")
	public String verifyNote(@RequestParam("uploadId")int uploadId,Model model, HttpSession session){
		if(!session.getAttribute("type").toString().equalsIgnoreCase("admin")){
			model.addAttribute("error","Invalid Access!You are not admin");
			return "home";
		}
		int status = uploadDao.validateNote(uploadId);
		List<Upload> list = uploadDao.list();
		model.addAttribute("uploadNotes",list);
		if(status>0){
			model.addAttribute("msg","Sucessfully Verified Note!");
		}else{
			model.addAttribute("msg", "Operation failed! Please Try again");
		}
		return "verifyNoteAdmin";
	}
	@GetMapping("disableNote")
	public String disableNote(@RequestParam("uploadId")int uploadId,Model model, HttpSession session){
		if(!session.getAttribute("type").toString().equalsIgnoreCase("admin")){
			model.addAttribute("error","Invalid Access!You are not admin");
			return "home";
		}
		int status = uploadDao.disableNote(uploadId);
		List<Upload> list = uploadDao.list();
		model.addAttribute("uploadNotes",list);
		if(status>0){
			model.addAttribute("msg","Sucessfully Disable Note!");
		}else{
			model.addAttribute("msg", "Operation failed! Please Try again");
		}
		return "verifyNoteAdmin";
	}
	
	@GetMapping("editMaterial")
	public String editMaterial(@RequestParam("uploadId")int uploadId, Model model,HttpSession session){
		if(session.getAttribute("email")==null) {
			model.addAttribute("error","<strong>Login First!</strong>");
			return "login";
		}
		Upload  upload = uploadDao.getDetail(uploadId);
		model.addAttribute("uploadFile", upload);
		return "uploadNote";
	}
	@GetMapping("checkMaterial")
	public ModelAndView checkMaterial(ModelAndView mv,HttpSession session){
		if(!session.getAttribute("type").toString().equalsIgnoreCase("admin")){
			mv.addObject("error","Invalid Access!You are not admin");
			mv.setViewName("home");
		}else{
			List<Upload> list = uploadDao.list();
			mv.addObject("uploadNotes",list);
			mv.setViewName("verifyNoteAdmin");
		}
		
		return mv;
	}
}
