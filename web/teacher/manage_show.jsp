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
	List<Exam> list=DaoFactory.getExamDaoInstance().search();
	String  examname=null;
	for(Exam exam:list)
	{
		if(exam.getE_isstart())
			examname=exam.getE_name();
	}
	
	List<Student> list2=DaoFactory.getStudentDaoInstance().search();
	int all_count=0;
	int login_count=0;
	int submit_count=0;
	for(Student s:list2)
	{
		if(s.getStu_exam().equals(examname))
		{
			all_count++;
			if(s.getStu_ip()!=null)
			{
				login_count++;
			}
			if(s.getStu_submit()!=null&&!s.getStu_submit().equals("null"))
			{
				submit_count++;
			}
		}
			
	}
	if(examname==null) examname="当前无考试进行中";
	int null_login=all_count-login_count;
	int null_submit=all_count-submit_count;
%>
<jsp:include page="teacher_head.jsp"></jsp:include>

<div class="container">
	<br/>
    <div class="row">
      <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-info">
          <div class="panel-heading">
            <h3 class="panel-title">
              <span class="glyphicon glyphicon-th-list"></span>&nbsp;<font color='red'><%=examname%></font> 进行状况
            </h3>
          </div>
          <div class="panel-body">
            <table class="table table-bordered">
              <tr>
                <th>参加人数</th>
                <th>登陆人数</th>
                <th>未登录人数</th>
                <th>已提交</th>
                <th>未提交</th>
              </tr>
              <tr>
                <th><%=all_count %></th>
                <th><%=login_count %></th>
                <th><%=null_login %></th>
                <th><%=submit_count %></th>
                <th><%=null_submit %></th>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>