<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"
	import="bean.*,java.util.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">

<style type="text/css">
.center {
	margin-top: 80px;
	position: relative;
	left: 50%;
	width: 360px;
	text-align: center;
	transform: translateX(-50%);
	border-radius: 20px;
	background-color: white;
	padding: 10px 15px 30px 15px;
}
</style>
</head>
<body>
	<%
		List<Exam> list = DaoFactory.getExamDaoInstance().search();
		String examname = null;
		for (Exam exam : list) {
			if (exam.getE_isstart()) {
				examname = exam.getE_name();
				session.setAttribute("examname", examname);
			}
		}
	%>
	<div class="container" >
		<div style="text-align: center; background-color: rgba(220,220,220,0.7);"
			class="row panel panel-default center">
				<%
					if (examname != null) {
				%>
				<h3>
					<b><font color='red'><%=examname%></font> 正在进行中...</b>
				</h3> <%
 					} else{
 						%>
						<h3>
							<b>当前没有考试，不能登录</b>
						</h3> <%
 					}%>
 			<br/>
			<form class="form-horizontal" role="form" action="./StudentLogin">
				<div class="col-xs-offset-1 col-xs-10 input-group">
					<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-user"></span>
					</span> 
					<input type="text" class="input-lg form-control input-font-size"
						name="stu_id" style="width: 260px;" placeholder="学号" required
						autofocus>
				</div>
				<br/>

				<div class="col-xs-offset-1 col-xs-10 input-group">
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-user"></span>
					</span> <input type="text" class="input-lg form-control input-font-size"
						name="stu_username" style="width: 260px;" placeholder="姓名"
						required>
				</div>
				<br />

				<div class="col-xs-offset-1 col-xs-10 input-group">
					<button type="submit" class="pull-left col-xs-5 btn btn-success"
						style="width: 300px; height: 50px; font-size: 20px; color: white;">登录</button>
				</div>
			</form>


		</div>
	</div>
</body>
</html>