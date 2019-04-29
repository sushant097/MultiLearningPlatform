<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="resources/static/css/bootstrap.min.css">


</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<div class="col-md-12">
				<h4>Admin Verification Table</h4>
				
				<div class="table-responsive">
					<table id="mytable" class="table table-bordered table-striped">
						
						
						<thead>
							<th><input type="checkbox" id="checkall" /></th>
							<th>Name</th>
							<th>Title</th>
							<th>Description</th>
							<th>Type</th>
							<!-- <th>Date</th> -->
							<th>Status</th>
							<th>Download</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Verify</th>
						</thead>
						<tbody>
							<c:forEach var="uploadNote" items="${uploadNotes}">
							<tr>
								<td><input type="checkbox" class="checkthis" /></td>
								<td class="col-md-2">${uploadNote.getName()}</td>
								<td class="col-md-2">${uploadNote.getTitle()}</td>
								<td class="col-md-4">${uploadNote.getDescription()}</td>
								<td class="col-md-1">${uploadNote.getType()}</td>
								<%-- <td class="col-md-1">${uploadNote.getDate()}</td> --%>
								<td class="col-md-1">
									<c:if test="${uploadNote.isValid() == true}">
										<span class="btn btn-xm"><label class="alert alert-success" style="font-size:10px;">Approved</label></span>
									</c:if>
									<c:if test="${uploadNote.isValid() == false}">
										<span class="btn btn-xm"><label class="alert alert-danger" style="font-size:10px;">Pending</label></span>
									</c:if>
								</td>
								<td><a href="downloadUploadNote?uploadId=${uploadNote.getUploadId()}">
								<span class="glyphicon glyphicon-download-alt"
									title="Download Materials"></span></a></td>
								<td><p data-placement="top" data-toggle="tooltip"
										title="Edit">
										<button id="editNote" class="btn btn-primary btn-xs" data-title="Edit"
										 onclick="location.href='editMaterial?uploadId=${uploadNote.getUploadId()}'">
											<span class="glyphicon glyphicon-pencil"></span>
										</button>
									</p></td>
									
								<td><p data-placement="top" data-toggle="tooltip"
										title="Delete">
										<button class="btn btn-danger btn-xs" data-title="Delete"
											data-toggle="modal" data-target="#delete">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</p></td>
								<td>
									<c:if test="${uploadNote.isValid() == false}">
										<a href="verifyNote?uploadId=${uploadNote.getUploadId()}" title="Verify Note">
											<button class="btn btn-primary btn-xs" >Verify</button>
										</a>
									</c:if>
									<c:if test="${uploadNote.isValid() == true}">
										<a href="disableNote?uploadId=${uploadNote.getUploadId()}" title="Disable Note">
											<button class="btn btn-danger btn-xs"  >Disable</button>
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>

					</table>

					<div class="clearfix"></div>
					<ul class="pagination pull-right">
						<li class="disabled"><a href="#"><span
								class="glyphicon glyphicon-chevron-left"></span></a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span></a></li>
					</ul>

				</div>

			</div>
		</div>
	</div>


	<div class="modal fade" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">Edit Your
						Detail</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control " type="text" placeholder="Mohsin">
					</div>
					<div class="form-group">

						<input class="form-control " type="text" placeholder="Irshad">
					</div>
					<div class="form-group">
						<textarea rows="2" class="form-control"
							placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


					</div>
				</div>
				<div class="modal-footer ">
					<button type="button" class="btn btn-warning btn-lg"
						style="width: 100%;">
						<span class="glyphicon glyphicon-ok-sign"></span> Update
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>



	<div class="modal fade" id="delete" tabindex="-1" role="dialog"
		aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">Delete this
						entry</h4>
				</div>
				<div class="modal-body">

					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-warning-sign"></span> Are you
						sure you want to delete this Record?
					</div>

				</div>
				<div class="modal-footer ">
					<button type="button" class="btn btn-success">
						<span class="glyphicon glyphicon-ok-sign"></span> Yes
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> No
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/static/js/angular.min.js"></script>
	<!-- Place bootstrap.min.js after jquery -->
	<script type="text/javascript"
		src="resources/static/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="edit.js"></script> -->
</body>
</html>