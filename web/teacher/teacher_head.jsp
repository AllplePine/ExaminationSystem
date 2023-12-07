<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,bean.*,utils.*"%>
<%@ page import="dao.ITeacherDao" %>
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

<style type="text/css">
body {
	background:rgba(248,248,255,0.7);
}
.title2{
	color: #fafafa;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
}
</style>

</head>
<body>
	<%
		String teacher = (String) session.getAttribute("teacher");
		ITeacherDao teacherDaoInstance = DaoFactory.getTeacherDaoInstance();
		Teacher search = teacherDaoInstance.search(teacher);
		String teacher_name = search.getT_name();
		//System.out.print(teacher);
	%>
	<%
		int flag = 1;
		List<Exam> list = DaoFactory.getExamDaoInstance().search();
		for (Exam e : list) {
			if (e.getE_isstart())
				flag = 1;
		}
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-collapse collapse">
			<div class="container">
				<ul class="nav navbar-nav">
					<li style="font-size: 18px; text-color: #ffffff;padding-top: 10px">
						<img style="width: 10%;" src="../images/logo.png">
						<a style="display: inline;color:#ffffff;" class="title2">上机考试系统</a>
					</li>
					<li><a href="teacher_main.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 首页 </a></li>
					<li><a href="exam_before.jsp"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span> 考前操作 </a></li>
					<li
						<%if (flag != 1)
				out.print("style='pointer-events:none;'");%>
						class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span> 考中管理<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="manage_show.jsp">考试概况</a></li>
							<li><a href="../FenYe">学生信息</a></li>
							<li><a href="manage_unlock.jsp">解除锁定</a></li>
							<li><a href="manage_notify.jsp">通知管理</a></li>
						</ul></li>
					<li
						<%if (flag != 1)
				out.print("style='pointer-events:none;'");%>><a
						href="exam_after.jsp?"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span> 考后操作 </a></li>
				</ul>
				<ul class="nav navbar-nav" style="float: right">
					<li><a><span class="glyphicon glyphicon-user" aria-hidden="true"></span> <%=teacher_name%> 老师,欢迎回来</a></li>
					<li><a href="" data-toggle="modal" data-target="#myModal">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 修改密码 </a></li>
					<li><a href="../index.jsp"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 退出 </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container" style="margin-top: 50px;height: 20px">
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 300px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<b>修改密码</b>
					</h4>
				</div>
				<form action="../ChangeTeacherPwd" method="post">
					<div class="alert navbar-inverse "
						style="background-color: #eeeeee; height: auto; margin-top: 20px">
						<input style="width: 250px" name="oldPwd" type="password"
							placeholder="原密码" class="form-control"> <br /> <input
							type="password" style="width: 250px" placeholder="新密码"
							name="newPwd1" class="form-control"> <br /> <input
							type="password" style="width: 250px" placeholder="重输新密码"
							name="newPwd2" class="form-control"> <br />
						<button type="submit" style="width: 250px" class="btn btn-primary">
							修改</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>