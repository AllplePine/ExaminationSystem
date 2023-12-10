<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*,utils.*"%>
<%@ page import="bean.Exam" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<%
		List<Exam> list_=DaoFactory.getExamDaoInstance().search();
		String  examname=null;
		for(Exam exam:list_)
		{
			if(exam.getE_isstart())
				examname=exam.getE_name();
		}
		StringBuffer sb = new StringBuffer();
		String sql = "SELECT * FROM information";
		ResultSet rs = DBUtil.executeQuery(sql);
		while (rs.next()) {
			sb.append("<tr><td width='20%'>");
			sb.append(rs.getString("times"));
			sb.append("</td><td>");
			sb.append(rs.getString("info"));
			sb.append("</td></tr>");
		}
	%>
	<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<br />
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;新增通知消息
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" action="../NotifyInfo"
							method="post" style="margin-left: 20px">
							<%if (examname==null){%>
									<div class="form-group" >
									<input type="text" name="information" class="form-control"
									style="width: 250px" placeholder="通知消息内容*"> <input
											type="submit" class="btn btn-info" value="发送" disabled/>
									</div>
							<%}else{%>
							<div class="form-group">
								<input type="text" name="information" class="form-control"
									style="width: 250px" placeholder="通知消息内容*"> <input
									type="submit" class="btn btn-info" value="发送" />
							</div>
							<%}%>
						</form>

						<table class="table table-bordered">
						</table>
					</div>
				</div>
			</div>
		</div>



		<br />
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;已有通知消息
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form"
							style="margin-left: 20px; margin-top: 5px;">
							<table class="table table-bordered" style="margin-top: 10px">
								<tr>
									<th class="col-md-12" colspan="2" style="text-align: center">通知内容</th>
								</tr>
								<%=sb.toString()%>
							</table>
						</form>

						<table class="table table-bordered">
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>