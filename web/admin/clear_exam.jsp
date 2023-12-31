<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*,utils.*,java.util.*,bean.*"%>
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
		List<Exam> list = DaoFactory.getExamDaoInstance().search();
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
							+ "&id=2' title='停止考试' class='btn btn-info'>停止考试</a>");
				else if (!exam.getE_clear()) {
					sb.append("<a href='../TeacherDownLoadExam?examname=" + exam.getE_name()
							+ "&id=2' title='下载考生答案' class='btn btn-info'>下载</a>");
					if(!exam.getE_file()){
						sb.append("&nbsp;&nbsp;<a disabled='disabled' href='#'"+"&id=1' title='清理考试' class='clear_btn btn btn-info'>清理考试</a>");
					}else{
						sb.append("&nbsp;&nbsp;<a href='../ClearExam?examname=" + exam.getE_name()
								+ "' title='清理考试' class='clear_btn btn btn-info'>清理考试</a>");
					}
				} else
					sb.append("");
			}
			sb.append("</td></tr>");
		}
	%>
<jsp:include page="admin_head.jsp"></jsp:include>
	<div class="container">
		<table class="table table-bordered" style="margin-top: 10px">
			<tr>
				<th class="col-md-1">考试名称</th>
				<th class="col-md-2">考试时间</th>
				<th class="col-md-1">创建人</th>
				<th class="col-md-1">上传试卷</th>
				<th class="col-md-1">自动开始</th>
				<th class="col-md-1">已结束</th>
				<th class="col-md-1">已归档</th>
				<th class="col-md-1">已清理</th>
				<th class="col-md-2"></th>
			</tr>
			<%=sb.toString()%>
		</table>
	</div>
	<script type="text/javascript">
		$(".clear_btn").click(function() {
			var href = $(this).attr("href");

			if (href === '#') {
				alert("请先下载（如已经下载请尝试刷新页面）");
				return false; // Prevent default action (don't follow the link)
			} else {
				var confirmClear = confirm("确定清除考试资料?");

				return confirmClear; // Allow or prevent the default action based on user confirmation
			}
		});
	</script>
</body>
</html>