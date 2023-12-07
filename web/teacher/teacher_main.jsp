<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<%
	String error = (String) session.getAttribute("teacher_error");
	out.print(error);
	session.setAttribute("teacher_error", "");
%>
<body>
<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse "
					style="background-color: #F0F8FF; height: 350px; margin-top: 20px;">
					<strong style="margin-left: 20px; font-size: 18px">考前操作</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 新建考试：指定考试名称、开始时间</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 编辑自己创建的、未创建的的考试、修改考试信息</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 上传试卷</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 学生名单导入</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 开始考试</li>
					</ul>
				</div>
			</div>
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse "
					style="background-color: #F0F8FF; height: 350px; margin-top: 20px">
					<strong style="margin-left: 20px; font-size: 18px">考中管理</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 查看考试情况</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 管理学生信息，手工添加个别学生信息</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 解除学生登陆锁定</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 添加或删除通知信息</li>
					</ul>

				</div>
			</div>
			<div class="col-md-4" style="padding-left: 5px; padding-right: 5px;">
				<div class="alert navbar-inverse"
					style="background-color: #F0F8FF; height: 350px; margin-top: 20px;">
					<strong style="margin-left: 20px; font-size: 18px">考后操作</strong>
					<ul class="list-group">
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 主考教师（考试创建者）可以结束考试</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 主考教师可以打包下载学生的提交文件</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 主考教师可以到处提交信息</li>
						<li class="list-group-item"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 如果管理员设置，只考教师可以清理和删除考试</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>