package admin;

import bean.Teacher;
import dao.ITeacherDao;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("t_username");
		String password = request.getParameter("t_pwd");
		ITeacherDao teacherDaoInstance = DaoFactory.getTeacherDaoInstance();
		String login = teacherDaoInstance.login(username, password);
		if (login.equals("admin")) {
			HttpSession session = request.getSession();
			Teacher t = teacherDaoInstance.search(username,password);
			session.setAttribute("username", username);
     	 	session.setAttribute("login", password);
     	 	session.setAttribute("admin_name", t.getT_name());
			response.sendRedirect("admin/manage_teacher.jsp");
		} else
			response.sendRedirect("index.jsp");
	}
}
