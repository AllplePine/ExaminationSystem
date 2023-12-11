<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*,java.util.*,bean.*,utils.*"%>
<%@ page import="java.io.File" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
<script type="text/javascript">
	function checkupload() {
		var file = document.getElementById("file").value;
		if (file == null || file == "")
			return false;
		else
			return true;
	}
</script>
</head>
<body>
	<%
		String stu_id = (String) session.getAttribute("stu_id");
		String savePath = (String)session.getAttribute("savePath");
		File file = new File(savePath);
		StringBuffer sb = new StringBuffer();
		if(file.exists()){
			File[] files = file.listFiles();
			for (File file1 : files) {
				sb.append("<tr><td>");
				sb.append(file1.getName());
				sb.append("</td><td>");
				sb.append(file1.length()+" Byte");
				sb.append("</td><td>");
				sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(file1.lastModified()));
				sb.append("</td><tr>");
			}
		}
	%>
	<jsp:include page="student_head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp; 已上传文件列表
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-bordered">
							<tr>
								<th class="col-md-4">文件名</th>
								<th class="col-md-4">文件大小</th>
								<th class="col-md-4">上传时间</th>
							</tr>
							<%=sb.toString()%>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp; 答案上传
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" name="file"
							action="../StudentUpload" enctype="multipart/form-data"
							method="post" style="margin-top: 12px; font-size: 18px">
							<input type="file" id="file" name="file" value="浏览..." />
							<button type="submit" onclick="return checkupload()"
								style="margin-top: 10px;" class="btn btn-info">
								<span class="glyphicon glyphicon-saved"></span>
								上传</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>