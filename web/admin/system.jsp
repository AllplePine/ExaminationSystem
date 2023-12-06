<%@ page import="utils.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上机考试管理系统</title>
</head>
<body>
	<jsp:include page="admin_head.jsp"></jsp:include>
	<%
		ServletContext servletContext = request.getServletContext();
		Config config1 = (Config)servletContext.getAttribute("config");
		request.setAttribute("interval",config1.getInterval());
		request.setAttribute("pagesize",config1.getPagesize());
		request.setAttribute("timegap",config1.getTimegap());
		request.setAttribute("minfilesize",config1.getMinfilesize());
		request.setAttribute("maxfilesize",config1.getMaxfilesize());
		boolean candelete = config1.isCandelete();
	%>
	<div class="container">
		<br/>
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span>&nbsp; 修改系统配置
						</h3>
					</div>
					<div class="panel-body">
						<form action="../EditSystemConfig">
							<table>
								<tr>
									<td align="right"><b>后台任务时间间隔</b></td>
									<td style="padding-left: 20px"><input name="interval" type="text" value=${interval}
										class="form-control"></td>
								</tr>
								<tr>
									<td></td>
									<td style="padding-left: 20px">制定扫描考试信息的间隔时间，单位时间为分钟</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td align="right"><b>分页查询记录条数</b></td>
									<td style="padding-left: 20px"><input name="pagesize" type="text" value=${pagesize}
										class="form-control"></td>
								</tr>

								<tr>
									<td></td>
									<td style="padding-left: 20px">指定分页查询时每页显示记录数的默认值（查询页面中可以更改）</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td align="right"><b>手动开启考试时间阈值</b></td>
									<td style="padding-left: 20px"><input name="timegap" type="text" value=${timegap}
										class="form-control"></td>
								</tr>
								<tr>
									<td></td>
									<td style="padding-left: 20px">指定手工开启考试时允许的最大提前量，单位为分钟</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td align="right"><b>上传文件字节数下限</b></td>
									<td style="padding-left: 20px"><input name="minfilesize" type="text"
										value=${minfilesize} class="form-control"></td>
								</tr>
								<tr>
									<td></td>
									<td style="padding-left: 20px">指定上传文件的下限（字节），低于此值发出警告</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td align="right"><b>上传文件字节数上限</b></td>
									<td style="padding-left: 20px"><input name="maxfilesize" type="text"
										value=${maxfilesize} class="form-control"></td>
								</tr>
								<tr>
									<td></td>
									<td style="padding-left: 20px">指定上传文件的长度上限（字节），高于此值发出警告</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td align="right"><b>允许教师清理和删除考试</b></td>
									<td style="padding-left: 20px"><input
											name="candelete" type="checkbox"
										<%if (candelete)
				out.print("checked='checked'");%>>
										允许</td>
								</tr>

								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td></td>
									<td style="padding-left: 20px"><button type="submit"
											class="btn btn-info">修改</button></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>