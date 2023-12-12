<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*,java.util.*,bean.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试系统</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		setInterval("showInfo()", 1000);
	});

	function showInfo() {
		$.ajax({
			type : "post",
			url : "../ShowInformation",
			success : function(text) {
				$("#show").html(text);
			}
		});
	}

	function checkupload() {
		var file = document.getElementById("file").value;
		if (file == null || file == "")
			return false;
		else
			return true;
	}
</script>
</head>

<style type="text/css">
body {
	/*background:antiquewhite;*/
}
</style>

<body>
	<%
		String stu_id = (String) session.getAttribute("stu_id");
		String examname = (String)session.getAttribute("examname");

	%>
<jsp:include page="student_head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="alert navbar-inverse "
					style="background-color: #F0F8FF; height: 214px; margin-top: 20px;">
					<h3>
						<strong>试卷下载</strong>
					</h3>
					<div class="alert"
						style="display:<%if (!examname.equals("")) out.print("none");%> ;background-color: gray; margin-top: 5px">
						<span style="color: white;">本次考试没有电子试卷</span>
					</div>
					<a class="btn btn-default" href="../ExamDownload"
						style="display: <%if (examname.equals("")) out.print("none");%>">
						<span class="glyphicon glyphicon-eye-open"></span>下载查看
					</a>
				</div>
			</div>
			<div class="col-md-6">
				<div class="alert navbar-inverse "
					style="background-color: #F0F8FF; height:fit-content; margin-top: 20px;padding: 10px">
					<h3>
						<strong>答案上传</strong>
					</h3>
					<span style="font-size: 18px">请按照考试要求将答案文件打包，再次进行上传。同名文件多次上传将会覆盖。</span>
					<form class="form-inline" name="file" role="form"
						action="../StudentUpload" enctype="multipart/form-data"
						method="post" style="margin-top: 12px; font-size: 18px">
						<input type="file" id="file" name="file">
						<button type="submit" onclick="return checkupload()"
							style="margin-top: 10px;" class="btn btn-info">
							<span class="glyphicon glyphicon-saved"></span>
							上传</button>
					</form>
				</div>
			</div>
		</div>

		<div class="col-md-6">
			<h4><span class="glyphicon glyphicon-bullhorn" ></span>：</h4><br>
			<table id="show" class="table table-condensed">
			</table>
		</div>
	</div>
</body>
</html>