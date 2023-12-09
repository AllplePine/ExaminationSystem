<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,bean.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<%
		StringBuffer sb = new StringBuffer();
		//查询所有考试信息
		List<Exam> list = DaoFactory.getExamDaoInstance().searchFor((String) session.getAttribute("teacher"));
		for (Exam exam : list) {
			sb.append("<tr><td>");
			sb.append(exam.getE_name());
			sb.append("</td><td>");
			sb.append(exam.getE_starttime());
			sb.append("</td><td>");
			sb.append(exam.getE_teacher());
			sb.append("</td><td>");
			if (exam.getE_examination()=="null")
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");//glyphicon glyphicon-ok
			if (!exam.getE_autostart())
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_isend())
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_file())
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_clear())
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_isstart() && !exam.getE_isend()) {
				sb.append("<span>考试未开启</span>");
			} else {
				if (!exam.getE_isend())
					sb.append("<a href='../StopExam?examname=" + exam.getE_name()
							+ "&id=1' title='停止考试' class='btn btn-info'><span class=\"glyphicon glyphicon-stop\"></span> 停止考试</a>");
				else if (!exam.getE_clear()) {
					sb.append("<a href='../TeacherDownLoadExam?examname=" + exam.getE_name()
							+ "&id=1' title='下载考生答案' class='btn btn-info'><span class=\"glyphicon glyphicon-floppy-save\"></span> 下载</a>");
					Config c = (Config)request.getServletContext().getAttribute("config");
					if(c.isCandelete()){
							sb.append("&nbsp;&nbsp;<a href='../ClearExam?examname=" + exam.getE_name()+ "&id=1' title='清理考试' class='clear_btn btn btn-info'><span class=\"glyphicon glyphicon-remove\"></span> 清理考试</a>");
						
					}
				} else
					sb.append("");
			}
			sb.append("</td></tr>");
		}
	%>
<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<table class="table table-bordered" style="margin-top: 10px">
			<tr>
				<th class="col-md-1">考试名称</th>
				<th class="col-md-2">考试时间</th>
				<th class="col-md-1">创建人</th>
				<th class="col-md-1">上传试卷</th>
				<th class="col-md-1">自动开始</th>
				<th class="col-md-1">已结束</th>
				<th class="col-md-1">已下载</th>
				<th class="col-md-1">已清理</th>
				<th class="col-md-2"></th>
			</tr>
			<%=sb.toString()%>
		</table>
	</div>

</body>
</html>