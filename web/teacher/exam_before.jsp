<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,bean.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
<link href="../bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
<jsp:include page="teacher_head.jsp"></jsp:include>
	<%
		request.setCharacterEncoding("utf-8");
		String exam_start = (String) session.getAttribute("exam_start");
		if (exam_start != null) {
			if (exam_start.equals("true")) {
	%>
	<script type="text/javascript">
		alert("开启考试成功");
	</script>
	<%
		session.setAttribute("exam_start", null);
			} else {
	%>
	<script type="text/javascript">
		alert("当前有考试正在进行，开启考试失败");
	</script>
	<%
		session.setAttribute("exam_start", null);
			}
		}
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
			if (exam.getE_examination() == null)
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_autostart())
				sb.append("");
			else
				sb.append("<span class='glyphicon glyphicon-ok'></span>");
			sb.append("</td><td>");
			if (!exam.getE_isstart())
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
			if (exam.getE_isstart()) {
				sb.append("<span>考试已开启</span>");
			} else if (exam.getE_isend())
				sb.append("<span>考试已结束</span>");
			else {
				sb.append("<a href='add_exam.jsp?examname=" + exam.getE_name()
						+ "' title='编辑考试信息' class='glyphicon glyphicon-edit'></a>");
			}
			sb.append("</td></tr>");
		}
	%>
	<div class="container">
		<div class="alert navbar-inverse"
			style="background-color: white; margin-top: 20px">
			<strong style="margin-left: 20px; font-size: 18px">添加考试</strong>
			<form class="form-inline" role="form" action="../AddExam"
				style="margin-left: 20px; margin-top: 5px;">
				<div class="form-group">
					<input type="text" name="examname" class="form-control"
						style="width: 250px" placeholder="考试名称">
				</div>


				<div class="form-group">
					<div class="input-group date form_datetime"
						data-link-field="dtp_input2">
						<input name="exam_starttime" placeholder="考试时间"
							class="form-control" type="text" readonly
							style="width: 170px; background-color: #fff;"> <span
							<%--class="input-group-addon"><span--%>
							<%--class="glyphicon glyphicon-remove"></span></span> <span--%>
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>


				<div class="checkbox">
					<label style="font-size: 15px"> <input type="checkbox"
						name="exam_autostart">自动开始
					</label>
				</div>
				<button class="btn btn-info" type="submit">添 加</button>
			</form>
		</div>
		<table class="table table-bordered table-hover table-responsive" style="margin-top: 10px">
			<tr>
				<th class="col-md-2">考试名称</th>
				<th class="col-md-2">考试时间</th>
				<th class="col-md-1">创建人</th>
				<th class="col-md-1">上传试卷</th>
				<th class="col-md-1">自动开始</th>
				<th class="col-md-1">进行中</th>
				<th class="col-md-1">已结束</th>
				<th class="col-md-1">已归档</th>
				<th class="col-md-1">已清理</th>
				<th class="col-md-1"></th>
			</tr>
			<%=sb.toString()%>
		</table>
	</div>
	<script type="text/javascript" src="../bootstrap/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$.fn.datetimepicker.dates['zh-CN'] = {
			days : [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" ],
			daysShort : [ "周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日" ],
			daysMin : [ "日", "一", "二", "三", "四", "五", "六", "日" ],
			months : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月",
					"十月", "十一月", "十二月" ],
			monthsShort : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
					"9月", "10月", "11月", "12月" ],
			today : "今天",
			suffix : [],
			meridiem : [ "上午", "下午" ]
		};

		$(".form_datetime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss',
			language : 'zh-CN',
			autoclose : true,
			todayBtn : true,
			startDate : "2016-06-08 17:00",
			minuteStep : 1
		});
	</script>
</body>
</html>