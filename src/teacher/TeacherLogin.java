package teacher;

import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//import com.example.utils.DesUtils;


@WebServlet("/TeacherLogin")
public class TeacherLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("t_id"); // 获得教师的ID
		String pwd = request.getParameter("t_pwd"); // 获得教师的口令
		String login = DaoFactory.getTeacherDaoInstance().login(id, pwd);
		if (login.equals("teacher") || login.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("teacher", id);
			response.sendRedirect("teacher/exam_before.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
