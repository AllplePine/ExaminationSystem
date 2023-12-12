package admin;

import bean.Teacher;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditTeacher")
public class EditTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditTeacher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		boolean isManager = false;
		Teacher teacher=DaoFactory.getTeacherDaoInstance().search(request.getParameter("edit_teacher_username"));
		teacher.setT_username(request.getParameter("edit_teacher_username"));
		if(!request.getParameter("edit_teacher_password").equals("")||request.getParameter("edit_teacher_password")!=null)
			teacher.setT_pwd(request.getParameter("edit_teacher_password"));
		teacher.setT_name(request.getParameter("edit_teacher_name"));
		String[] manager = request.getParameterValues("edit_teacher_manage");
		if (manager == null) {
			isManager = false;
		} else {
			isManager = true;
		}
		teacher.setT_manager(isManager);
		int result=DaoFactory.getTeacherDaoInstance().update(teacher, teacher.getT_username());
		if (result > 0) {
			response.sendRedirect("admin/manage_teacher.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
