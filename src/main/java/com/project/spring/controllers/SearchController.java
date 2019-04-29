package com.project.spring.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.spring.dao.BroadcastDao;
import com.project.spring.dao.DiscussionDao;
import com.project.spring.dao.UploadDao;
import com.project.spring.model.Discussion;


@Controller
public class SearchController 
{
	@Autowired
	private DiscussionDao discussionDao;
	@Autowired
	private UploadDao uploadDoa;  // for search materials uploaded
	@Autowired
	private BroadcastDao broadcastDao;// for searching broadcast video detail
	

	
}
