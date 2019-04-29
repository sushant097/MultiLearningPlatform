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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.DiscussionAnswerDao;
import com.project.spring.dao.DiscussionDao;
import com.project.spring.model.DisAnsStatus;
import com.project.spring.model.Discussion;
import com.project.spring.model.DiscussionAnswer;

@Controller
public class DiscussionAnswerController {

	
	@Autowired
	private DiscussionAnswerDao discussionAnswerDao;
	@Autowired
	private DiscussionDao discussionDao;
	
	@GetMapping("/discussionWithAnswer")
	public ModelAndView getAnswer(@RequestParam("disId") int disId,ModelAndView model) {
		disId = disId - 1000;
		System.out.println("DisId= "+disId);
		Discussion discussion = discussionDao.getDiscussion(disId);
		List<DiscussionAnswer> listAnswer = discussionAnswerDao.list(disId);
		DiscussionAnswer disAns = new DiscussionAnswer();
		disAns.setDisId(disId);
		model.addObject("discussionAnswer",listAnswer);
		model.addObject("discussion",discussion);
		model.addObject("answerDiscussion",disAns);
		model.setViewName("discussionWithAnswer");
		System.out.println("discussionWithAnswer");
		return model;
	}
	
	@PostMapping("/saveAnswer")
	public ModelAndView saveAnswer(@ModelAttribute DiscussionAnswer disAnswer,@RequestParam("disId") int disId,HttpSession session) {
		if(session.getAttribute("email") == null) {
			return new ModelAndView("redirect:/","error","<strong>Sorry! You should login first!");
		}
		
		disAnswer.setDisId(disId);
		String email = (String)session.getAttribute("email");
		disAnswer.setEmail(email);
		disAnswer.setName((String)session.getAttribute("name"));
		disAnswer.setType((String)session.getAttribute("type"));
		DiscussionAnswer disObj =discussionAnswerDao.checkUniqueAnswerDiscussion(disId, email);
		if(disObj == null) {
			discussionAnswerDao.createUpdateanswerDiscussion(disAnswer);
			
		}else {
			
			return new ModelAndView("redirect:/discussionWithAnswer?disId="+(disId+1000),"error","<strong>Error! </strong>Multiple Answer by same user on Same Discussion can't be done!");
			
			
		}
		
		return new ModelAndView("redirect:/discussionWithAnswer?disId="+(disAnswer.getDisId()+1000));
	}
	
	@PostMapping("/editAnswerDiscussion")
	public ModelAndView editAnswer(@RequestParam("ansId")int ansId,HttpSession session,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ModelAndView model = new ModelAndView();
		
		DiscussionAnswer disAnswer = discussionAnswerDao.getAnswer(ansId);
		String sessionEmail = (String)session.getAttribute("email");
		String userEmail = disAnswer.getEmail();
		if(sessionEmail.equals(userEmail)) {
			
			model.addObject("discussionAnswer",null);
			model.addObject("discussion",null);
			
			model.addObject("answerDiscussion",disAnswer);
			model.setViewName("discussionWithAnswer"); 
			out.print("<div class='bg-success'>You Click Edit Answer</div>");
			return model;
			
		}else {
			out.print("<div class='bg-danger'><strong>Invalid Access!</strong>You can't Edit Answer</div>");
			return null;
		}
		
	}
	
	@PostMapping("/discussionAnswerUpVote")
	@ResponseBody
	public  String  discussionAnswerUpVote(@RequestParam("ansId") int ansId,HttpSession session, HttpServletResponse response) {
		int status  = 0;
		String emailSession = (String)session.getAttribute("email");
		DiscussionAnswer disAnswer = discussionAnswerDao.getAnswer(ansId);
		String emailUser = disAnswer.getEmail();
		
		if(emailSession.equals(emailUser)) {
			// you can't vote your own content
			return "You can't vote your own post";
		}else {
			DisAnsStatus disAnsStatus= new DisAnsStatus();
			disAnsStatus.setEmail(emailSession);
			disAnsStatus.setDisAnsStatusId(ansId);
			disAnsStatus.setCategory("discussionAnswer");
			 status = discussionDao.upVote(disAnsStatus);
		}
		if(status>0){
			return "success";
		}else{
			return "fail";
		}

	}
	
	@PostMapping("/discussionAnswerdownVote")
	@ResponseBody
	public String discussionAnswerdownVote(@RequestParam("ansId") int ansId,HttpSession session, HttpServletResponse response) {
		int status =0;
		String emailSession = (String)session.getAttribute("email");
		DiscussionAnswer disAnswer = discussionAnswerDao.getAnswer(ansId);
		String emailUser = disAnswer.getEmail();
		
		if(emailSession.equals(emailUser)) {
			// you can't vote your own content
			return "You can't vote your own post";
		}else {
			DisAnsStatus disAnsStatus= new DisAnsStatus();
			disAnsStatus.setEmail(emailSession);
			disAnsStatus.setDisAnsStatusId(ansId);
			disAnsStatus.setCategory("discussionAnswer");
			status  = discussionDao.downVote(disAnsStatus);
		}
		if(status>0){
			return "success";
		}else{
			return "fail";
		}

	}
}
