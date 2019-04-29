package com.project.spring.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.dao.BroadcastDao;
import com.project.spring.model.BroadCast;
import com.project.spring.model.TrainVideo;

@Controller
public class BroadCastController {

	@Autowired
	BroadcastDao broadcastDao;
	
	final static String pathToUpload = "E:\\MyProject\\now_third_second_part_Project\\ProjectPractice\\MultiLearningPlatformMyProject\\src\\main\\webapp\\upload\\trainVideos\\";
	
	 List<Integer> ListbroadCastId = new ArrayList<Integer>();// to track the broadcastId
	 List<Integer> ListUploadId = new ArrayList<Integer>();//to track the video order
	 int counterTrain =0;
	 int counterUpload =0;
	@GetMapping("/attendLiveTraining")
	public String attendLiveTraining(Model model,HttpSession session) {
		if(session.getAttribute("email")==null){
			model.addAttribute("error","<strong>Invalid Access!</strong>Student only join the live Training! ");
			return "home";
		}
		List<BroadCast> list = broadcastDao.list();
		if(list.isEmpty()){
			model.addAttribute("error","<strong>Sorry!</strong> No any Live Training Found current!");
		}else{
			model.addAttribute("listTrain",list);
		}
		
		return "listTraining";
	}
	
	@GetMapping("/showDetailLiveTraining")
	public String showDetailLiveTraining(Model model,@RequestParam("broadCastId")int broadCastId,HttpSession session) {
		if(session.getAttribute("email")==null){
			model.addAttribute("error","<strong>Invalid Access!</strong>Student only join the live Training! ");
			return "home";
		}
		BroadCast broadCastDetail = broadcastDao.getDetailTraining(broadCastId);
		model.addAttribute("broadcastDetail", broadCastDetail);
		return "showDetailLiveTrain";
	}
	
	@PostMapping("/joinLiveTraining")
	public String joinLive(@RequestParam("broadCastId")int broadCastId , HttpSession session, Model model) {
		BroadCast broadCastDetail = broadcastDao.getDetailTraining(broadCastId);
		model.addAttribute("roomname",broadCastDetail.getRoomname());
		return "liveClient";
	}
	
	@PostMapping("/checkroomname")
	@ResponseBody
	public String checkRoom(@RequestParam("roomname")String roomname){
		BroadCast broadCast = broadcastDao.checkRoomname(roomname);
		if(broadCast !=null){
			return "invalid"; // roomname already exit
		}else{
			return "valid";
		}
		
	}
	@GetMapping("/createLiveTraining")
	public ModelAndView createLive(ModelAndView model,HttpSession session) {
		if(((String)session.getAttribute("type")).equalsIgnoreCase("student")) {
			return new ModelAndView("redirect:/homePage","error","<strong>Student can't create live training!</strong>");
		}
		
		
		BroadCast broadCast = new BroadCast();
		model.addObject("liveTrain",broadCast);
		model.setViewName("createLiveForm");
		
		return model;
		
	}
	
	@PostMapping("/startLiveTraining")
	public ModelAndView startLiveTraining(@ModelAttribute BroadCast broadCast,ModelAndView model,HttpSession session,HttpServletResponse response) throws IOException 
	{
			
			String teacherEmail = (String) session.getAttribute("email");
			String teacherName = (String) session.getAttribute("name");
			broadCast.setEmail(teacherEmail);
			broadCast.setTeacherName(teacherName);
			String roomname = broadCast.getRoomname();
			String check  = checkRoom(roomname);
			System.out.println("ROOMNAME: "+broadCast.getRoomname());
			if(check.equals("valid")){
				int status = broadcastDao.createorUpdate(broadCast);
				if(status>0) {
					//sucessfully saved
					int broadcastId = broadcastDao.returnBroadCastId(broadCast.getRoomname());
					BroadCast broadCastDetail = broadcastDao.getDetailTraining(broadcastId);
					ListbroadCastId.add(broadcastId);
					System.out.println("StartTrain: broadcastId: "+broadcastId);
					//counterTrain++;
					model.addObject("editTrain",broadCastDetail);
					model.addObject("roomname",broadCastDetail.getRoomname());
					model.addObject("broadCastId",broadcastId);
					model.addObject("msg","<div class='alert alert-success alert-dismissible' role='alert'>"
							+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
							+ "Sucessfully created Live Training!</div>");
					model.setViewName("liveHost");
				}else {
					model.addObject("msg","<div class='alert alert-danger alert-dismissible' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
						+ "Failed to create Live Training!</div>");
					model.setViewName("home");
					}
			}else{
				model.addObject("liveTrain",broadCast);
				model.addObject("msg","<div class='alert alert-danger alert-dismissible' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
						+ "Unique and Understandable roomname necessary for Live Training!</div>");
				model.setViewName("createLiveForm");
			}
		return model;
	}
	
	@PostMapping("editTrainingDetail")
	public void editTrainingDetail(@ModelAttribute BroadCast broadCast,HttpServletResponse res) throws IOException {
		int status =0;
		status = broadcastDao.createorUpdate(broadCast);
		PrintWriter out = res.getWriter();
		if(status >0) {
			
			out.write("success");
		}else {
			out.write("fail");
		}
		
	}
	@PostMapping("uploadTrainVideo")
	public @ResponseBody String uploadVideo(@RequestParam("file")CommonsMultipartFile file, Model model, HttpSession session) throws IllegalStateException, IOException{
		if(!file.getOriginalFilename().isEmpty()) {
			int status =0;
			 System.out.println("FIle size: "+file.getSize());
			 String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			 System.out.println("File Extension: "+extension);
			 String uniqueFileName = createSalt();
			 TrainVideo trainVideo = new TrainVideo();
			 File f1 = new File(pathToUpload+uniqueFileName+"."+extension);
			 int broadCastId = ListbroadCastId.get(counterUpload);counterUpload++;
			 System.out.println("UploadVideo: broadcastId: "+broadCastId);
			 BroadCast broadCast = broadcastDao.getDetailTraining(broadCastId);
			 if(f1.exists() && !f1.isDirectory()) { 
				    // do something
				}else {
					trainVideo.setFileName(uniqueFileName);
					trainVideo.setFileExtension("webm");
					trainVideo.setTrainId(broadCast.getBroadcastId());
					trainVideo.setTitle(broadCast.getTitle());
					trainVideo.setName(broadCast.getTeacherName());
					trainVideo.setEmail(broadCast.getEmail());
					trainVideo.setDescription(broadCast.getDescription());
					trainVideo.setRelatedTo(broadCast.getRelatedTo());
					trainVideo.setDate(broadCast.getDate());
					
				    status = broadcastDao.saveTrainVideo(trainVideo);
					 if(status>0){
						int status2 =  broadcastDao.deleteDetailTraining(broadCastId);
							if(status2>0){
								file.transferTo(f1);
								 model.addAttribute("msg","Sucessfully Upload file!!");
							}else{
								 model.addAttribute("msg","File Upload failed!!");

							}
						
						 return "createLiveForm";
					 }else{
						 model.addAttribute("msg"," Upload file Failed!!");
						 return "fail";
					 }
				}
		}else{
			System.out.println("file name is empty");
			return "fail";
		}
		return "fail";
		
	}
	
	/*@ResponseBody
	public String uploadRecordedVideo(HttpServletRequest request){
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		try {
			int counter = 0;
			List<FileItem> files =  sf.parseRequest(request);
			for(FileItem file: files){
				counter= counter + 1;
				if(counter==1){
					long size = file.getSize();
					String extension = file.getContentType();
					String fileTempName = file.getName();
					extension = extension.split("/")[1];
					System.out.println("size: "+size + " extension: "+extension);
					String filename = UUID.randomUUID().toString();
					file.write(new File(pathToUpload+filename+"."+extension));
					System.out.println("path: "+ pathToUpload+filename+"."+extension);
					System.out.println("sucessfully Uploaded!");
					return "success";
				}
				
			}
		} catch ( Exception e) {
			e.printStackTrace();
			return e.getMessage()+"";
		}
		return "fail";

	}*/
	

	@GetMapping("/listTrainVideo")
	public String listVideo(Model model){
		/*List<TrainVideo>list = broadcastDao.listTrainVideo();
		if(list.isEmpty()){
			model.addAttribute("error","<strong>Sorry!</strong>No any videos found");
		}else{
			model.addAttribute("listVideo",list);
		}*/
		
		return "videoPlay";
	}
	
	@GetMapping("/playTrainVideo")
	public String getDetailTrainVideo(@RequestParam("videoId")int videoId,Model model){
		TrainVideo trainVideo = broadcastDao.getTrainVideoDetail(videoId);
		List<TrainVideo>list = broadcastDao.listTrainVideo();
		int count =0;
		for(TrainVideo train:list){
			
			if(train.getVideoId()==videoId){
				list.remove(count);
			}
			count++;
		}
		model.addAttribute("videoDetail",trainVideo);
		model.addAttribute("videoList",list);
		return "videoPlay";
	}
	
	private String createSalt() {
	    String ts = String.valueOf(System.currentTimeMillis());
	    long date = 0;
	    System.out.println(""+new Date(date).getTime());
	    System.out.println("Date: "+date);
	   
	    String rand = UUID.randomUUID().toString();
	    
	    return (rand+ts);
	}
	
	
	
}
