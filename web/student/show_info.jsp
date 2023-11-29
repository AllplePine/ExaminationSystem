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
<jsp:include page="student_head.jsp"></jsp:include>
	<%
		StringBuffer sb = new StringBuffer();
		//查询所有考试信息
		List<Exam> list = DaoFactory.getExamDaoInstance().searchForStu((String) session.getAttribute("stu_id"));
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
			if (exam.getE_isstart()) {
				sb.append("<a href='student_main.jsp"+ "' title='参加考试' class='btn btn-primary btn-sm'>参加考试</a>");
			} else
				sb.append("<span>考试已结束</span>");
			sb.append("</td></tr>");
		}
	%>
	<div class="container">
    <div class="row">
      <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-info">
          <div class="panel-heading">
            <h3 class="panel-title">
              <span class="glyphicon glyphicon-th-list"></span>&nbsp;所有考试
            </h3>
          </div>
          <div class="panel-body">
            <table class="table table-bordered">
              <tr>
                <th>考试名称</th>
                <th>考试时间</th>
                <th>创建人</th>
                <th>上传试卷</th>
                <th>操作</th>
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