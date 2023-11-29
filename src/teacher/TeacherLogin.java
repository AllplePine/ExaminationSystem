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
		
//		request.setCharacterEncoding("utf-8");
//		String id = request.getParameter("t_id"); // 获得教师的ID
//		String pwd = request.getParameter("t_pwd"); // 获得教师的口令
//		
//		if (id.equals("teacher") || id.equals("admin")) {
//			HttpSession session = request.getSession();
//			session.setAttribute("teacher", id);
//			response.sendRedirect("teacher/teacher_main.jsp");
//		} else {
//			response.sendRedirect("index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("t_id"); // 获得教师的ID
		String pwd = request.getParameter("t_pwd"); // 获得教师的口令

//		try {
//			DesUtils des = new DesUtils("leemenz");
//			pwd = des.encrypt(pwd);
//			System.out.println("成功");
//			System.out.println(pwd);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block e1.printStackTrace();
//			System.out.println("失败");
//		}

		String login = DaoFactory.getTeacherDaoInstance().login(id, pwd);
		//Teacher teacher = new Teacher();
		if (login.equals("teacher") || login.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("teacher", id);
			response.sendRedirect("teacher/exam_before.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
