package admin;

import bean.Teacher;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddTeacher")
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//设置请求的字符编码utf-8
		request.setCharacterEncoding("utf-8");
		//将获取的表单数据存入Teacher
		Teacher teacher=new Teacher();
		boolean isManager = false;
		
		teacher.setT_username(request.getParameter("username"));
		teacher.setT_pwd(request.getParameter("pwd"));
		teacher.setT_name(request.getParameter("name"));
		String[] manager = request.getParameterValues("manager");
		//判断manager数组是否为空
		if(manager == null){
			isManager = false;
		}else{
			isManager = true;
		}
		teacher.setT_manager(isManager);
		
		int result= DaoFactory.getTeacherDaoInstance().add(teacher);
		//如果执行成功跳转页面
		if(result > 0)
			response.sendRedirect("admin/manage_teacher.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
