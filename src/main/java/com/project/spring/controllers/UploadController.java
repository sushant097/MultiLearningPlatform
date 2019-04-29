package com.project.spring.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.UploadDao;
import com.project.spring.model.Upload;

// see this; https://www.youtube.com/watch?v=igEOUWb50l8&t=429s
@Controller
public class UploadController 
{
	String path ="E:\\MyProject\\now_third_second_part_Project\\OnlineTrainingPresentProject\\MultiLearningPlatformMyProject\\src\\main\\webapp\\upload\\";
	
	@Autowired
	private UploadDao uploadDao;
	
	
	@RequestMapping(value="/noteSearch", method=RequestMethod.GET)
	public void noteSearch(@RequestParam("searchKey")String searchKey, HttpServletResponse res) {
		PrintWriter out = null;
		try{
			 out = res.getWriter();
			if(searchKey.trim().length() == 0){
				// No any key enter
			}else{
				List<Upload>list = uploadDao.searchNote(searchKey);
				if(list.isEmpty()){
					out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert' style='font-size:10px;'>No Any Results Found!</span></li>");
				}else{
					for(Upload upload: list){
						out.write("<li class='list-group-item'>"
								+ "Title: <a href='discussionWithAnswer?disId="+(upload.getUploadId())+"'>"+upload.getTitle()+"</a> "
								+ " BY:<span class='glyphicon glyphicon-user'>"+upload.getName()+"</span>"+" Vote: "+upload.getVote()
								+ " </li>");
					}
				}
			}
		}catch(Exception e){
			out.print("<li class='list-group-item'><span  class='alert alert-danger' role='alert' style='font-size:10px;'>"+e.getMessage()+"</span></li>");
		}
	}
	@GetMapping("/uploadFile")
	public ModelAndView uploadFile(ModelAndView model,HttpSession session) {
		if(session.getAttribute("email")==null) {
			return new ModelAndView("login","error","<strong>Login First!</strong>");
		}
		Upload  upload = new Upload();
		model.addObject("uploadFile", upload);
		model.setViewName("uploadNote");
		
		return model;
	}
	@PostMapping("/saveUploadFile")
	public ModelAndView saveUploadFiles(@ModelAttribute Upload upload, @RequestParam("file")CommonsMultipartFile file,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
			
		if(upload.getRelatedTo().length>3) {
			
			model.addObject("uploadFile",new Upload());
			model.addObject("errorUpload","<div class='alert alert-danger' role='alert'><strong>Sorry! Only At Most 3 Tags!</strong></div>");
			model.setViewName("uploadNote");
			return model;

		}else {
			if(!file.getOriginalFilename().isEmpty()) {
				 System.out.println("FIle size: "+file.getSize());
				 String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				 System.out.println("File Extension: "+extension);
				 String uniqueFileName = createSalt();
				
				 File f1 = new File(path+uniqueFileName+"."+extension);
				 if(f1.exists() && !f1.isDirectory()) { 
					    // do something
					}else {
						upload.setFileName(uniqueFileName);
						upload.setExtension(extension);
						upload.setEmail((String)session.getAttribute("email"));
						upload.setType((String)session.getAttribute("type"));
						upload.setName((String)session.getAttribute("name"));
						int status = uploadDao.uploadNote(upload);
						if(status > 0) {
							file.transferTo(f1); // file is multipart file	
							// check file save or not or new file exist already or not
							return new ModelAndView("uploadFileDescription","upload",upload);
						}else {
							model.addObject("uploadFile",new Upload());
							model.addObject("error","<div class='alert alert-danger' role='alert'><strong>Sorry! File Upload Failed!</strong></div>");
							model.setViewName("uploadNote");
							return model;
						}
					}  
			 }
		}
		return null; 
		
	}
	
	@RequestMapping(value = "/noteList", method = RequestMethod.GET)
	public String noteListByPage(@RequestParam("pageId") int pageId, Model model) {
		int total =5;
		if(pageId==1){}
		else{
			pageId = (pageId - 1) *total +1;
		}
		
		List<Upload>list = uploadDao.getNoteByPage(pageId, total);
		model.addAttribute("listNote",list);
		return "listNotes";
	}
	
	@GetMapping("/describeNote")
	public String describeNote(Model model,@RequestParam("uploadId") int uploadId) {
		Upload upload = uploadDao.getDetail(uploadId);
		model.addAttribute("download",upload);
		return "downloadNote";
	}
	@GetMapping("downloadUploadNote")
	public void downloadNote(Model model, @RequestParam("uploadId") int uploadId,HttpServletResponse response) throws IOException {
		//out.write("<div class='alert alert-success' role='alert'>Starting file Download!</div>");
		Upload upload = uploadDao.getDetail(uploadId);
		
		
		String filePath = path+upload.getFileName()+"."+upload.getExtension();
		
		System.out.println("filePath"+filePath);
		File file = new File(filePath);
		if(!file.exists()) {
			System.out.println("FIle Not Exist");
		}else {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLengthLong((int) file.length());
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			
			FileCopyUtils.copy(inputStream, response.getOutputStream());
//			PrintWriter out = response.getWriter();
//			for(int i=0;i<100;i++) {
//			out.write("<div class=\"progress\">\r\n" + 
//					"  <div class=\"progress-bar progress-bar-success\" role=\"progressbar\" aria-valuenow=\"40\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+i+"%\">\r\n" + 
//					"    <span class=\"sr-only\">"+i+"% Complete (success)</span>\r\n" + 
//					"  </div>\r\n" + 
//					"</div>");
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//}
			
		}
		
	}
	/*@PostMapping("/getFiles")
	public ModelAndView getFiles( @RequestParam("file")CommonsMultipartFile[] files) {
System.out.println("Length: "+files.length);
		
			 for(MultipartFile multipartFile : files) {
					
				 if(!multipartFile.getOriginalFilename().isEmpty()) {
					 System.out.println("FIle size: "+multipartFile.getSize());
					 File f1 = new File(path+multipartFile.getOriginalFilename());
					 try {
						multipartFile.transferTo(f1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						 return new ModelAndView("redirect:/homePage","error",e.getMessage());
					}
					 System.out.println("Full Path: "+ path+multipartFile.getOriginalFilename());
					 
				 }
				
			 }
			 System.out.println("Upload Title: "+upload.getTitle());
			 System.out.println("Upload Description: "+upload.getDescription());
			 return null;
		}
	*/
	
	private String createSalt() {
	    String ts = String.valueOf(System.currentTimeMillis());
	    long date = 0;
	    System.out.println(""+new Date(date).getTime());
	    System.out.println("Date: "+date);
	   
	    String rand = UUID.randomUUID().toString();
	    
	    return (rand+ts);
		}
	}

	


