<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
<link href="../bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container alert navbar-inverse">
		<strong style="color: white; margin-left: 20px; font-size: 15px">完成学生名单的导入和修改后,</strong>
		<a href="add_exam.jsp" class="btn btn-default"><span
			class="glyphicon glyphicon-pencil"></span>返回编辑</a>
	</div>
	<div class="container">
		<br />
		
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span style="margin-left: 20px">添加单个学生</span>
						</h3>
					</div>
					<div class="panel-body">

						<form class="form-inline" role="form" style="margin-left: 50px"
							action="../TeacherAddStudent" method="post">
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px" placeholder="学号*" name="xuehao">
								<input type="text" class="form-control" style="width: 250px" placeholder="姓名*" name="xingming">
								<input type="text" class="form-control" style="width: 250px" placeholder="班级*" name="banji">
								<input type="submit" class="btn btn-info" value="添加" />
							</div>
						</form>
						<table class="table table-bordered">
						</table>
					</div>
				</div>
			</div>


			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;学生信息
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-bordered">
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>班级</th>
							</tr>
							<%
								StringBuilder sb = (StringBuilder) session.getAttribute("info");
								out.print(sb);
							%>
						</table>
						<ul class="pagination pagination-lg">
							<li><a
								href="../FenYe?page=<%=session.getAttribute("current1")%>&exam=<%=session.getAttribute("studentinfo")%>">&laquo;</a></li>
							<%=(String) session.getAttribute("bar")%>
							<li><a
								href="../FenYe?page=<%=session.getAttribute("current2")%>&exam=<%=session.getAttribute("studentinfo")%>">&raquo;</a></li>
						</ul>
					</div>
				</div>
			</div>

		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp; 批量导入学生名单
					</h3>
				</div>
				<div class="panel-body">
					<form action="../ImportStuFromExcel" method="post"
						class="form-inline" role="form" enctype="multipart/form-data"
						style="margin-left: 20px; margin-top: 12px;">
						<input type="file" name="file" accept=".xls"/>
						<br />
						<button type="submit" style="margin-top: 5px" class="btn btn-info">
							<span class="glyphicon glyphicon-saved"></span>
							导入</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>