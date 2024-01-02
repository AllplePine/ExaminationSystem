<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<%
	String error = (String) session.getAttribute("error");
	out.print(error);
	session.setAttribute("error", "");
%>
<body>
<jsp:include page="admin_head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse "
					 style="background-color: #F0F8FF; height: 350px; margin-top: 20px;">
					<strong style="margin-left: 20px; font-size: 18px">考试清理</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 清楚考试的相关数据，包括数据库中的信息，文件系统中的提交文件</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 只有在主考教师已经包下载学生提交文件后才可以进行</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 清理后的考试可以删除</li>
					</ul>
				</div>
			</div>
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse "
					 style="background-color: #F0F8FF; height: 350px; margin-top: 20px">
					<strong style="margin-left: 20px; font-size: 18px">教师管理</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 可以对教师用户进行增删改查操作，并可以指定特定教师作为系统管理员</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 系统可以有多个管理员</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 如果没有任何一个教师具有管理员身份，则默认管理员登录信息有效</li>
					</ul>

				</div>
			</div>
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse"
					 style="background-color: #F0F8FF; height: 350px; margin-top: 20px;">
					<strong style="margin-left: 20px; font-size: 18px">系统配置</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 设置一些全局性的系统选项，包括后台任务的时间周期，分页查询时的每页记录数，手动开启考试的时间阈值，学生上传文件字数的有限范围等</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 可以指定时候允许主考教师清理和删除考试</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 清理后的考试可以删除</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
http://47.113.202.40/exam