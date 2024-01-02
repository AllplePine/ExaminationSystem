<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="bean.*,java.util.*,utils.DaoFactory,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<%
		String error = (String) session.getAttribute("pwderror");
		out.print(error);
		session.setAttribute("pwderror", "");
	%>
	<%
		request.setCharacterEncoding("utf-8");
		//查询所有教师信息

		List<Teacher> list = DaoFactory.getTeacherDaoInstance().search();
		StringBuffer sb = new StringBuffer();
		for (Teacher teacher : list) {
			sb.append("<tr><td>");
			sb.append(teacher.getT_username());
			sb.append("</td><td>");
			sb.append(teacher.getT_name());
			sb.append("</td><td>");
			if (teacher.getT_manager()) {
				sb.append("<span class=\"glyphicon glyphicon-ok\"></span>");
			} else {
				sb.append("");
			}
			sb.append("</td><td>");
			//此处缺少href跳转
			sb.append("<a href='edit_teacher.jsp?edit_username=" + teacher.getT_username() + "&edit_name="
					+ teacher.getT_name() + "'><span class='glyphicon glyphicon-edit' title='编辑'></span></a>");
			sb.append("&nbsp&nbsp&nbsp");
			sb.append("<a href='../DeleteTeacher?delete_username=" + teacher.getT_username()
					+ "'><span class='glyphicon glyphicon-trash' title='删除'></span></a>");
			sb.append("</td></tr>");
		}
	%>
	<jsp:include page="admin_head.jsp"></jsp:include>
	<div class="container">
		<br />
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span style="margin-left: 20px">添加教师</span>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form"
							style="margin-left: 20px; margin-top: 5px;"
							action="../AddTeacher">
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px"
									placeholder="用户名*" name="username">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px"
									placeholder="初始口令*" name="pwd">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px"
									placeholder="真实姓名" name="name">
							</div>
							<div class="checkbox">
								<label style="font-size: 15px"> <input type="checkbox"
									name="manager">管理员
								</label>
							</div>
							<button type="submit" class="btn btn-info">添加</button>
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
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;教师信息
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-bordered">
							<tr>
								<th>用户名</th>
								<th>真实姓名</th>
								<th>是否为管理员</th>
								<th></th>
							</tr>
							<%=sb.toString()%>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>