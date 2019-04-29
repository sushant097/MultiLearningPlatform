<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Demo</title>
<script type="text/javascript" src="resources/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/static/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="resources/static/js/mediastreamrecorder.js"></script>
<script src="https://cdn.webrtc-experiment.com/MediaStreamRecorder.js">
	
</script>
<jsp:include page="../../resources/header/header.jsp"></jsp:include>
<style type="text/css">
video {
	border: 5px solid yellow;
	border-radius: 9px;
}

body {
	background: black;
}

h1 {
	color: yellow;
}


</style>
</head>
<body>


	<jsp:include page="../../resources/header/navbar.jsp"></jsp:include>
	${msg}
	<div class="container-fluid">
		<h1 id="header"></h1>
		<button class="btn btn-info btn-xs" id="showModal" data-toggle="modal"
			data-target=".bs-edit-modal-lg">EDIT</button>
		<div class="modal fade bs-edit-modal-lg" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="model-header">
						<h2 class="modal-title">Edit your Broadcast Detail If
							necessary</h2>
					</div>
					<form:form id="editForm" action="startLiveTraining" method="post"
						modelAttribute="editTrain">
						<form:hidden path="broadcastId" />
						<div class="form-group">
							<label for="roomname">Input Room Name(You should create
								unique roomName)</label>
							<form:input type="text" required="required" path="roomname"
								class="form-control" value="${roomname}" readonly="true" />
						</div>
						<div class="form-group">
							<label for="title">Title</label>
							<form:input type="text" required="required" class="form-control"
								path="title"
								placeholder="Title must be short and sweet as long as understandable" />
						</div>
						<div class="form-group">
							<label for="relatedTo">Related Faculty</label>

							<div id="showSelect"
								class="alert alert-warning alert-dismissible" role="alert"
								style="display: none">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<p>You can click Control and select multiple if necessary</p>
							</div>

							<form:select path="relatedTo" multiple="true" required="required"
								class="form-control" id="select">

								<form:option value="BCE"></form:option>
								<form:option value="BEL"></form:option>
								<form:option value="BCT"></form:option>
								<form:option value="BEX"></form:option>
								<!-- Also to be add Industrial Engineering as Thapathali Campus -->
								<form:option value="BME"></form:option>
								<form:option value="B.Arch"></form:option>
							</form:select>
						</div>
						<div class="form-group">
							<label for="description">Description</label>
							<form:textarea path="description" required="required"
								class="form-control" row="10" col="200"
								placeholder="Include your time of training, about, topics to be covered and also next training etc." />
						</div>

						<button type="submit" class="btn btn-success btn-sm">Edit
							Detail</button>
					</form:form>
					<div class="model-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							id="closeDetail">Close</button>
						<button type="button" class="btn btn-info"
							onclick="sendInfo('editTrainingDetail')" id="editDetail">Edit</button>
					</div>
				</div>
			</div>
		</div>
		<div class="showEdit" style="display: none"></div>

		<script>
			$("#select").click(function() {
				$("#showSelect").show();
			});
			$("#showModal").click(function() {
				$("#showModal").remove();
			});
			/* $("#closeDetail").click(function(){
				$("#showModal").remove();
			});
			$("#editDetail").click(function(){
				$("#showModal").remove();
			}); */
			// Ajax post call
			$("#editForm").submit(function(e) {

				var form = $(this);
				var url = form.attr('action');

				$.ajax({
					type : "POST",
					url : url,
					data : form.serialize(), // serializes the form's elements.
					success : function(response) {
						if (response == 200) {
							alert("Sucessfully EDIT record!");
						} else {
							alert("EDIT Record Failed!");
						}
						// alert(data); // show response from the php script.
					}
				});

				e.preventDefault(); // avoid to execute the actual submit of the form.
			});
		</script>
		<div class="row">
			<br>
			<br>
			<div class="col-md-8 col-sm-12">

				<video id="video" autoplay controls
					style="height: 800px; width: 800px; padding: 0px;">
					<source src="" type="video/webm">
				</video>
				<button id="stopButton" onclick="stopRecording();">Stop</button>
				<br>
				<button id="downloadButton" onclick="downloadData()">DownloadVideo</button>
			</div>

			<!-- <script type="text/javascript"
				src="resources/static/js1/required/adapter.js"></script>
			<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script> -->

			<script>
				var roomname = '${roomname}';
				var broadCastId = ${broadCastId};//integer
			</script>
			<script type="text/javascript"
				src="resources/static/js1/sendVideo.js"></script>

			<br>
			<br>

			<div class="col-md-4 col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading" style="text-align: center;">Live
						Chat Window</div>

					<div id="tablePanelBody" class="panel-body">
						<table id="tablemess" class="table table-striped"
							style="overflow: auto; width: 350px; height: 500px ;display:block;table-layout: fixed;">
								<tbody style="overflow:auto;display:block;width: 350px; height: 500px "></tbody>
							</table>
					</div>


					<div class="panel-footer">
						<div class="input-group">
							<input type="text" id="messageText" class="form-control">
							<span class="input-group-btn">
								<button class="btn btn-primary btn-sm" type="button"
									id="sendBtn" onclick="sendMessage();">Send</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<video id="video1" playsinline loop controls
				style="height: 200px; width: 200px; padding: 0px; display: none;">
			</video>
		</div>

	</div>
	<script type="text/javascript" src="resources/static/js1/chatUiJs.js"></script>

</body>
</html>