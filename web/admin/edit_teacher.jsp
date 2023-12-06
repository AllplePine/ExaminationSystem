<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
<style type="text/css">
.inputclass {
	height: 30px;
	width: 350px;
}
tr {
	line-height: 50px;
}
</style>
</head>
<script>
	function enableButton() {
		var passwordInput = document.getElementById("edit_teacher_password");
		var usernameInput = document.getElementById("edit_teacher_username");
		var submitButton = document.getElementById("submit_button");

		if (usernameInput.value.trim() != "" && passwordInput.value.trim() != "") {
			submitButton.disabled = false;
		} else {
			submitButton.disabled = true;
		}
	}
</script>
<body>
	<%
		String username=request.getParameter("edit_username");
		String name=request.getParameter("edit_name");
		
	%>
	<jsp:include page="admin_head.jsp"></jsp:include>
	<div class="container">
		<h3><strong>修改教师账号</strong></h3>
		<form action="../EditTeacherPwd" method="post">
		<table style="margin-left: 50px">
			<tr>
				<td align="right"><b>用户名:<b></td>
				<td style="padding-left: 20px"><input readonly="readonly" type="text"  name="edit_teacher_username"
													  class="form-control inputclass" value="<%=username %>" id="edit_teacher_username" oninput="enableButton()"></td>
			</tr>
			<tr>
				<td align="right"><b>密码:<b></td>
				<td style="padding-left: 20px"><input type="password" id="edit_teacher_password" name="edit_teacher_password"
													  class="form-control inputclass" oninput="enableButton()"></td>
			</tr>
			<tr>
				<td align="right"><b>全名:<b></td>
				<td style="padding-left: 20px"><input type="text" 
					class="form-control inputclass" name="edit_teacher_name" value="<%=name%>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="padding-left: 20px"><label style="font-size: 15px">
						<input type="checkbox" name="edit_teacher_manage">管理员
				</label></td>
			</tr>
			<tr>
				<td></td>
				<td style="padding-left: 20px"><button class="btn btn-info" id="submit_button" type="submit" disabled>修改</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>