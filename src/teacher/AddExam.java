<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*,java.util.*,bean.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
<link href="../bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript">
	function checkupload() {
		var file = document.getElementById("file").value;
		if (file == null || file == "")
			return false;
		else
			return true;
	}
</script>
</head>
<body>
	<%
		String examname = null;
		try {
			examname = request.getParameter("examname");
		} catch (Exception e) {
			System.out.println("Encoding");
		}

		if (examname == null)
			examname = (String) session.getAttribute("examname");
		session.setAttribute("examname", examname);
		String exam_starttime = "";
		String upload = "";
		boolean autostart = false;
		Exam exam = DaoFactory.getExamDaoInstance().search(examname);
		exam_starttime = exam.getE_starttime();
		autostart = exam.getE_autostart();
		upload = exam.getE_examination();
	%>
	<%
		//计算参加考试的学生人数
		int count = 0;
		List<Student> list = DaoFactory.getStudentDaoInstance().search();
		for (Student student : list) {
			if (student.getStu_exam().equals(examname)) {
				count++;
			}
		}
	%>
	<jsp:include page="teacher_head.jsp"></jsp:include>
	<div class="container">
		<br />
		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp; 编辑考试信息
					</h3>
				</div>
				<div class="panel-body">
					<form class="form-inline" role="form" action="../EditExam"
						style="margin-left: 20px">
						<div class="form-group">
							<input type="text" class="form-control" style="width: 250px"
								value="<%=examname%>" placeholder="考试名称*" name="examname">
						</div>
						<%--用于提交旧examname--%>
						<%--<div class="form-group" style="display: none;">--%>
							<%--<input type="text" class="form-control" style="width: 250px"--%>
								   <%--value="<%=examname%>" placeholder="考试名称*" name="oldexamname">--%>
						<%--</div>--%>
						<div class="form-group">
							<div class="input-group date form_datetime"
								data-link-field="dtp_input2">
								<input name="exam_starttime" placeholder="考试时间*"
									class="form-control" type="text" readonly
									style="width: 170px; background-color: #fff;"> <%--<span
                                    class="input-group-addon">
								<span
                                        class="glyphicon glyphicon-remove">
								</span>
								</span> --%><span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<br />
						<div class="checkbox" style="padding-top: 8px">
							<label style="font-size: 15px"> <input
								name="exam_autostart" type="checkbox"
								<%if (autostart)
				out.print("checked='checked'");%>>
								自动开始
							</label>
						</div>
						<br />
						<button type="submit" style="margin-top: 8px" class="btn btn-info" id="modifyButton" disabled>修改</button>
					</form>


				</div>
			</div>
		</div>


		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp; 上传试卷
					</h3>
				</div>
				<div class="panel-body">
					<div class="navbar navbar-inverse"
						style="line-height: 50px;padding-left: 25px;color: white;margin-top: 8px;display: <%if (upload == null || upload.equals("null"))
				out.print("none");%>">
						已经上传过试卷，再次上传后原试卷将不可访问<a class="btn btn-default"
							href="../ExamDownload" style="margin-left: 10px"><span
							class="glyphicon glyphicon-eye-open"></span>下载查看</a>
					</div>
					<form class="form-inline" role="form" action="../ExamUpload"
						enctype="multipart/form-data" method="post"
						style="margin-left: 20px; margin-top: 8px;">
						<input type="file" id="file" name="file" />
						<button type="submit" onclick="return checkupload()"
							style="margin-top: 5px" class="btn btn-info">
							<span class="glyphicon glyphicon-saved"></span>
							上传</button>
					</form>
				</div>
			</div>
		</div>


		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp; 导入学生名单
					</h3>
				</div>
				<div class="panel-body">
					<form style="margin-left: 20px; margin-top: 8px">
						<label style="font-size: 12px">目前设定参加此次考试的学生人数：</label> <span><%=count%></span>
						<br /> <a href="../FenYe?exam=<%=examname%>" class="btn btn-info">
						<span class="glyphicon glyphicon-saved"></span>
						继续导入</a>
					</form>
					<br />

				</div>
			</div>
		</div>

		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp; 开启考试
					</h3>
				</div>
				<div class="panel-body">
					<form action="../ExamStart" style="margin-left: 20px; margin-top: 8px;">
						<%if (upload == null || upload.equals("null")){
						%>
						<span style="font-size: 12px; color: #f7AE3F">请及时上传试卷</span> <br />
						<button type="submit" style="margin-top: 12px"
								class="btn btn-warning" disabled>
							<span class="glyphicon glyphicon-play"></span>开启
						</button>
						<%
						}else{
						%>
						<span style="font-size: 12px; color: #f7AE3F">尚未开启考试</span> <br />
						<button type="submit" style="margin-top: 12px"
								class="btn btn-warning">
							<span class="glyphicon glyphicon-play"></span>开启
						</button>
						<%}%>
					</form>
				</div>
			</div>
		</div>
	</div>







	<script type="text/javascript" src="../bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript">
		$.fn.datetimepicker.dates['zh-CN'] = {
			days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
			daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
			months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
			today: "今天",
			suffix: [],
			meridiem: ["上午", "下午"]
		};

		$(document).ready(function () {
			$(".form_datetime").datetimepicker({
				format: 'yyyy-mm-dd hh:ii:ss',
				language: 'zh-CN',
				autoclose: true,
				todayBtn: true,
				startDate: "2016-06-08 17:00",
				minuteStep: 1
			});

			// 添加日期选择后的验证
			$(".form_datetime").on('changeDate', function (event) {
				var selectedDate = event.date;
				// 这里可以根据选定日期执行相应的逻辑
				// 例如，启用修改按钮
				enableModifyButton();
			});

			function enableModifyButton() {
				// 启用修改按钮
				$("#modifyButton").prop("disabled", false);
			}
		});
	</script>

</body>
</html>