<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="utils.*,java.sql.*"%>
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
</head>

<style type="text/css">
body {
	background:#e8f2fe;
}
.title2{
	color: #fafafa;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
}
</style>


<body>
<%
	String stu_name = (String) session.getAttribute("stu_name");
	String examname = (String)session.getAttribute("examname");
%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-collapse collapse">
		<div class="container">
			<ul class="nav navbar-nav">
				<li style="font-size: 18px; text-color: #ffffff;padding-top: 10px">
					<img style="width: 10%;" src="../images/logo.png">
					<a style="display: inline;color:#ffffff;" class="title2">上机考试系统</a>
				</li>
				<li><a href="show_info.jsp"> <span
						class="glyphicon glyphicon-home"></span>首页
				</a></li>
				<li><a href="student_submit.jsp"><span
						class="glyphicon glyphicon-eye-open"></span>查看提交</a></li>
			</ul>
			<ul class="nav navbar-nav" style="float: right">
				<li><a>欢迎 <%=stu_name%> 同学</a></li>
				<li><a href="../index.jsp"> 退出 </a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container" style="margin-top: 50px;">
		<h1>
			<%=stu_name%> 同学,考试 <font color='red'><%= examname%></font> 正在进行中...
		</h1>
		<br/>
	</div>
</body>
</html>