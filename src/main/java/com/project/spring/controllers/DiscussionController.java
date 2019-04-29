package com.project.spring.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.DiscussionDao;
import com.project.spring.model.DisAnsStatus;
import com.project.spring.model.Discussion;
import com.project.spring.model.Login;

@Controller
public class DiscussionController 
{
	@Autowired
	private DiscussionDao discussionDao;
	
	@RequestMapping(value = "/discussion", method = RequestMethod.GET)
	public ModelAndView listDiscussionByPage(@RequestParam("pageId") int pageId) {
		int total = 10;
		if(pageId==1){}
		else{
			pageId = (pageId - 1) * total +1;
		}
		List<Discussion> listDiscussion = discussionDao.getDiscussionByPage(pageId, total);
		ModelAndView model = new ModelAndView();
		model.addObject("listDiscussion", listDiscussion);
		
		model.setViewName("discussion"); //discussion.sjsp
		return model;
	}
	
	@RequestMapping(value = "/discussionTitleSearch", method=RequestMethod.POST)
	public void discussionTitleSearch(@RequestParam("searchName")String searchKey,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(searchKey.trim().equals("")) {
				//out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert'>Please Enter Text!</span></li>");
			}else {
				List<Discussion> list = discussionDao.searchDiscussionTitle(searchKey);
				if(list.isEmpty()) {
					
				// show nothing

				}else {
					for(Discussion discussion: list) {
						out.write("<li class='list-group-item'>"
								+ "Title: <a href='discussionWithAnswer?disId="+(discussion.getDisId()+1000)+"'style:'font-size:20px;'>"+discussion.getTitle()+"</a> "
								+ " BY:<span class='glyphicon glyphicon-user'> "+discussion.getName()+"</span>"
								+ " Related To: "+Discussion.listToString(discussion.getRelatedTo())
								+ " </li>");
					}
				}
			}
			
		}catch(Exception e){}
	}
	@RequestMapping(value = "/discussionSearch", method=RequestMethod.POST)
	public void discussionSearch(@RequestParam("searchName")String searchKey,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(searchKey.trim().equals("")) {
				//out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert'>Please Enter Text!</span></li>");
			}else {
				List<Discussion> list = discussionDao.searchDiscussion(searchKey);
				if(list.isEmpty()) {
					
				out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert' style='font-size:10px;'>No Any Results Found!</span></li>");

				}else {
					for(Discussion discussion: list) {
						out.write("<li class='list-group-item'>"
								+ "Title: <a href='discussionWithAnswer?disId="+(discussion.getDisId()+1000)+"'>"+discussion.getTitle()+"</a> "
								+ " BY:<span class='glyphicon glyphicon-user'>"+discussion.getName()+"</span>"+" Vote: "+discussion.getVote()
								+ " </li>");
					}
				}
			}
			
		} catch (IOException e) {
			
			out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert' style='font-size:10px;'>"+e.getMessage()+"</span></li>");
		}
		
	}
	/*@GetMapping("/showDiscussion")
	public ModelAndView showDiscussion(ModelAndView model, @RequestParam("disId") int id) {
		id = id-1000; // for safety purpose
		Discussion discussion = discussionDao.getDiscussion(id);
		model.addObject("discussion",discussion);
		model.setViewName("discussionWithAnswer");
		return model;
	}*/
	
	@GetMapping("/createDiscussion")
	public ModelAndView createDiscussion(ModelAndView model,HttpSession session) {
		if(session.getAttribute("email") == null) {
			model.addObject("error","<strong>Sorry!</strong> You should Login First");
			model.addObject("login",new Login());
			model.setViewName("login");
			return model;
		}
		
		String type = (String)session.getAttribute("type");
		if(type.equalsIgnoreCase("teacher")) {
			model.addObject("error", "<strong>Invalid Acess!</strong> Teacher can't create Discussion.");
			model.setViewName("home");//redirect:/homePage
		}else {
			Discussion discussion = new Discussion();
			model.addObject("discussionObj",discussion);
			model.setViewName("createDiscussion");
			
		}
		return model;
	}
	
	@PostMapping("/saveDiscussion")
	public ModelAndView saveDiscussion(@ModelAttribute Discussion discussion,HttpSession session, ModelAndView model,HttpServletResponse response) {
		if(session.getAttribute("email")==null) {
			model.addObject("error","<strong>Sorry!</strong> You should Login First");
			model.addObject("login",new Login());
			model.setViewName("login");
			return model;
		}
		System.out.println("Discussion:  "+discussion.getRelatedTo()+" length: temp "+ discussion.getRelatedTo().length);
		if(discussion.getRelatedTo().length > 4) {
			
			PrintWriter out = null;
			try {
					out = response.getWriter();
					model.addObject("discussionObj", discussion);
					model.addObject("error","<strong>Sorry!</strong> At Most 3 Tags only");
					model.setViewName("createDiscussion");
					return model;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.write(e+"");
			}
			
		}else {
			
			String email = (String)session.getAttribute("email");
			String type = (String)session.getAttribute("type");
			String name = (String) session.getAttribute("name");
			System.out.println("Name: "+name+"Email: Taken From Session "+ email+" type: "+type);
			discussion.setEmail(email);
			discussion.setName(name);
			
			discussionDao.createOrUpdateDiscussion(discussion);
			// SHOW THE ONLY ONE RECENTLY PLACE DISCUSSION TO THE onediscussion.jsp
			return new ModelAndView("redirect:/discussion?pageId=1"); // Send To Only Show current discussion
		}
		return null;
		
		
	}
	
	// can be done as above
	@PostMapping("/editDiscussion")
	public ModelAndView editDiscussion(@RequestParam("disId") int discussionId,HttpSession session,HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		String emailSession = (String)session.getAttribute("email");
		
		Discussion discussion = discussionDao.getDiscussion(discussionId);
		String emailUser = discussion.getEmail();
		if(emailSession.equals(emailUser)) {
			
			if(discussion.getNoOfAnswer()>0) {
				try {
					PrintWriter out = response.getWriter();
					out.write("<div class='bg-danger'><strong>Invalid Access!</strong> You can't edit discussion as answer is already added!</div>");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}else {
				model.addObject("discussionObj",discussion);
				model.setViewName("createDiscussion");
				return model;
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("<div class='bg-danger'><strong>Invalid Access!</strong> You can't edit discussion!</div>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	@PostMapping("/upVoteDiscussion")
	public @ResponseBody String upVoteDiscussion(@RequestParam("disId") int disId, HttpServletResponse response, HttpSession session) throws IOException   {
		
		String emailSession = (String)session.getAttribute("email");
		Discussion discussion = discussionDao.getDiscussion(disId);
		String emailUser = discussion.getEmail();
		int status =0;
		if(emailSession.equals(emailUser)) {
			//you can't self up or down your own post
			return "You can't vote your own post";
		}else {
			
			DisAnsStatus disAnsStatus= new DisAnsStatus();
			disAnsStatus.setEmail(emailSession);
			disAnsStatus.setDisAnsStatusId(disId);
			disAnsStatus.setCategory("discussion");
			status = discussionDao.upVote(disAnsStatus);
			
		}
		if(status>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	@PostMapping("/downVoteDiscussion")
	public @ResponseBody String downVoteDiscussion(@RequestParam("disId")int disId, HttpServletResponse response, HttpSession session) throws IOException   {
		
		String emailSession = (String)session.getAttribute("email");
		Discussion discussion = discussionDao.getDiscussion(disId);
		String emailUser = discussion.getEmail();
		int status = 0;
		
		if(emailSession.equals(emailUser)) {
			// you can't vote your own content
		}else {
			DisAnsStatus disAnsStatus= new DisAnsStatus();
			disAnsStatus.setEmail(emailSession);
			disAnsStatus.setDisAnsStatusId(disId);
			disAnsStatus.setCategory("discussion");
			status = discussionDao.downVote(disAnsStatus);
		}
		if(status>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	

	
}

