<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<%
		StringBuffer sb = new StringBuffer();
		try {
			sb = (StringBuffer) session.getAttribute("unlock");
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("unlock", null);
	%>
	<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<br />
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;查找学生登录信息
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" action="../ManageUnlocked"
							style="margin-left: 20px">
							<div class="form-group">
								<input type="text" class="form-control" style="width: 250px"
									name="id" placeholder="学号*">
								<%--<span class="glyphicon glyphicon-search" aria-hidden="true"></span>--%>
								<button type="submit" class="btn btn-info">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查找
								</button>
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
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;查找结果
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-bordered">
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>班级</th>
								<th>ip地址</th>
								<th>解除锁定</th>
							</tr>
							<%
								if (sb != null)
									out.print(sb.toString());
							%>
						</table>
					</div>
				</div>
			</div>
		</div>


	</div>
</body>
</html>