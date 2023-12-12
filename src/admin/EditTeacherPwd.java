package admin;

import bean.Teacher;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditTeacherPwd")
public class EditTeacherPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		Teacher teacher=new Teacher();
		boolean isManager = false;
		String username=request.getParameter("edit_teacher_username");
		String pwd=request.getParameter("edit_teacher_password");
		teacher.setT_pwd(pwd);
		teacher.setT_name(request.getParameter("edit_teacher_name"));
		String[] manager = request.getParameterValues("edit_teacher_manage");
		if(manager == null){
			isManager = false;
		}else{
			isManager = true;
		}
		teacher.setT_manager(isManager);
		int result=DaoFactory.getTeacherDaoInstance().update(teacher, username);
		
		if(result > 0){
			session.setAttribute("pwderror", "<script>alert('修改成功');</script>");
			response.sendRedirect("admin/manage_teacher.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
