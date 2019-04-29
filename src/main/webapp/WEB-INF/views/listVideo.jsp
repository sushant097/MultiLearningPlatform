<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Video</title>

<link href="resources/static/css/bootstrap4.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/static/css/videoDesignCss.css" rel="stylesheet">
<jsp:include page="../../resources/header/header.jsp"/>
</head>
<body>
<jsp:include page="../../resources/header/navbar.jsp"/>

		<%!final  String pathToUpload = "E:\\MyProject\\now_third_second_part_Project\\ProjectPractice\\MultiLearningPlatformMyProject\\src\\main\\webapp\\upload\\trainVideos\\";%>
	
		<div class="container">
			 <div class="row videos">
                <div class="col-md-12">
                    <div class="media media-video">
                        <a href="#" class="media-lef thumb">
                            <video   style="width:250px; height:200px;">
                                    <source src="resources/media/01.webm" type="video/webm">
                              </video>
                            <span class="duration">00:00:00</span>
                        </a>
                        <div class="media-body">
                            <a href="#" class="media-heading">Video Title Goes here</a>
                            <div class="meta"><a href="#" class="author">VideoUploaderUsername</a> • 808K views • 2 years ago</div>
                            <div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodtempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo</div>
                        </div>
                    </div>
                    <div class="media media-video">
                        <a href="#" class="media-lef thumb">
                            <video   style="width:250px; height:200px;">
                                    <source src="resources/media/02.webm" type="video/webm">
                              </video>
                            <span class="duration">00:00:00</span>
                        </a>
                        <div class="media-body">
                            <a href="#" class="media-heading">Video Title Goes here</a>
                            <div class="meta"><a href="#" class="author">VideoUploaderUsername</a> • 808K views • 2 years ago</div>
                            <div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodtempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo</div>
                        </div>
                    </div>
                    <div class="media media-video">
                        <a href="#" class="media-lef thumb">
                            <video   style="width:250px; height:200px;">
                                    <source src="resources/media/03.webm" type="video/webm">
                              </video>
                            <span class="duration">00:00:00</span>
                        </a>
                        <div class="media-body">
                            <a href="#" class="media-heading">Video Title Goes here</a>
                            <div class="meta"><a href="#" class="author">VideoUploaderUsername</a> • 808K views • 2 years ago</div>
                            <div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodtempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo</div>
                        </div>
                    </div>
                </div>
             </div>
		</div>
		<%-- <c:forEach var="videoItem" items="${listVideo}" var="status">
			
		</c:forEach> --%>
		
		<jsp:include page="../../resources/header/footer.jsp"/>
		<script type="text/javascript" src="resources/static/js/bootstrap4.min.js"></script>
		
</body>
</html>