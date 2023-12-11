package admin;

import bean.Teacher;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EditTeacher
 */
@WebServlet("/EditTeacher")
public class EditTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// 设置请求的字符编码utf-8
		request.setCharacterEncoding("utf-8");
		// 获取输入的表单数据
		boolean isManager = false;
		Teacher teacher=DaoFactory.getTeacherDaoInstance().search(request.getParameter("edit_teacher_username"));
		teacher.setT_username(request.getParameter("edit_teacher_username"));
		if(!request.getParameter("edit_teacher_password").equals("")||request.getParameter("edit_teacher_password")!=null)
			teacher.setT_pwd(request.getParameter("edit_teacher_password"));
		teacher.setT_name(request.getParameter("edit_teacher_name"));
		String[] manager = request.getParameterValues("edit_teacher_manage");
		// 判断manager数组是否为空
		if (manager == null) {
			isManager = false;
		} else {
			isManager = true;
		}
		teacher.setT_manager(isManager);
		int result=DaoFactory.getTeacherDaoInstance().update(teacher, teacher.getT_username());
		// 如果执行成功跳转页面
		if (result > 0) {
			response.sendRedirect("admin/manage_teacher.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
