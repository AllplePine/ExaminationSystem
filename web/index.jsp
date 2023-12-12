<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="utils.*,java.sql.*,java.util.*,bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>上机考试管理系统</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.title {
	color: #fafafa;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
}

</style>
</head>
<body class="spotlight parallax bg-cover bg-size--cover" data-spotlight="fullscreen" style="background-image: url('images/background1.jpg')">
	<span class="bg-primary alpha-7"></span>
	<nav class="navbar navbar-inverse navbar-fixed-top "style="background:none;border: 0px">
	<div class="navbar-header">
		<div class="container">
			<ul class="nav navbar-nav">
				<li style="font-size: 26px;"><a style="color:black">
					<div style="display:flex; align-items:center;">
						<img style="width: 12%" src="images/logo.png">
						<p class="title">
							上机考试管理系统
						</p>
					</div>


				</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container text-center" style="position: relative;top: 60px;">
		<ul id="main_nav" class="nav nav-pills">
			<li style="font-size: 18px;"class="active" ><a href="#student" data-toggle="tab" ><font >学生登录</font></a></li>
			<li style="font-size: 18px;"><a href="#teacher" data-toggle="tab">教师登录</a></li>
			<li style="font-size: 18px;"><a href="#admin" data-toggle="tab">管理员登录</a></li>
		</ul>
		<div id="myTabContent" class="tab-content" style="margin-top: 30px;">
			<div class="tab-pane fade in active" id="student">
				<jsp:include page="student/student_login.jsp"></jsp:include>
			</div>
			<div class="tab-pane fade" id="teacher">
				<jsp:include page="teacher/teacher_login.jsp"></jsp:include>
			</div>
			<div class="tab-pane fade" id="admin">
				<jsp:include page="admin/admin_login.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>