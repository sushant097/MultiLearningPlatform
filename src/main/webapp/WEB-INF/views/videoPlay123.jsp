<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>VideoPlay</title>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style>
      .col-xs-12 video{
        width:355px; height:200px;
      }  
      .col-sm-6 video{
        width:355px; height:200px;
      }
      .col-md-8 video{
        width:800px; height:500px;
      }
      .col-sm-4 video{
        width:355px; height:200px;
      }
     /*  .col-md-8 {
            border-right: 6px solid gray;
            height: 100%;
      } */
    </style> 
</head>
<body>
<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>

	<%!final  String pathToUpload = "E:\\MyProject\\now_third_second_part_Project\\ProjectPractice\\MultiLearningPlatformMyProject\\src\\main\\webapp\\upload\\trainVideos\\";%>
    <div class="container-fluid">
            <div class="row">
            <%if(request.getAttribute("videoDetail")!=null){ %>
                  <div class="col-xs-12 col-sm-6 col-md-8">
                               <h3>Column 1</h3>
                               <video  type="video/webm" controls style="">
                                    <source src="resources/media/01.mp4">
                                </video>
                                <hr>
                                <div class="well">${videoDetail.getDescription()}</div>
                        
                </div> 
              <%}%>
              <%if(request.getAttribute("videoList")!=null){ %>
                <div class="col-xs-6 col-sm-4 col-md-4">
                        <h3>Column 2</h3>
                        <c:forEach var="videoDetail" items="${videoList}">
                                <video  type="video/webm" style="width:250px; height:200px;">
                                    <source src="resources/media/02.mp4">
                                </video>
                                <div class="pull-right" >
                                        <h2><a href="">${videoDetail.getTitle()}</a></h2><br>
                                        <b>Description</b>
                                </div>    
                                
                                <hr>
                         </c:forEach>
                </div>
                 <%}else{%>
                   		<div class="alert alert-info">
                   			<strong>No!</strong> Any Other Video TO Show.
                   		</div>
                <%} %>
             <!-- Optional: clear the XS cols if their content doesn't match in height -->
            </div>
        </div>
        
<jsp:include page="../../resources/header/footer.jsp"></jsp:include>

</body>
</html>