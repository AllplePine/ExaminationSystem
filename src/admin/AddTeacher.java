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
		
		request.setCharacterEncoding("utf-8");
		Teacher teacher=new Teacher();
		boolean isManager = false;
		
		teacher.setT_username(request.getParameter("username"));
		teacher.setT_pwd(request.getParameter("pwd"));
		teacher.setT_name(request.getParameter("name"));
		String[] manager = request.getParameterValues("manager");
		if(manager == null){
			isManager = false;
		}else{
			isManager = true;
		}
		teacher.setT_manager(isManager);
		
		int result=DaoFactory.getTeacherDaoInstance().add(teacher);
		if(result > 0)
			response.sendRedirect("admin/manage_teacher.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
