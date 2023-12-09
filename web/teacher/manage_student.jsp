<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<br/>
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span>当前考试: <font color='red'><%=session.getAttribute("studentinfo")%></font></span>
							<span style="margin-left: 20px">添加单个学生</span>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form"
							style="margin-left: 50px"
							action="../TeacherAddStudent?id=2" method="post">
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px"
									placeholder="学号*" name="xuehao"> <input type="text"
									class="form-control" style="width: 250px" placeholder="姓名*"
									name="xingming"> <input type="text"
									class="form-control" style="width: 250px" placeholder="班级*"
									name="banji">
								<%
									if(session.getAttribute("studentinfo")==null||"无".equals(session.getAttribute("studentinfo"))){
										%>
									<input type="submit" class="btn btn-info" disabled
									   value="添加" /><%
									}else{ %>
									<input type="submit" class="btn btn-info"
									value="添加" /><%
								}%>

							</div>
						</form>

						<table class="table table-bordered">
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
								href="../FenYe?page=<%=session.getAttribute("current1")%>">&laquo;</a></li>
							<%=(String) session.getAttribute("bar")%>
							<li><a
								href="../FenYe?page=<%=session.getAttribute("current2")%>">&raquo;</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>