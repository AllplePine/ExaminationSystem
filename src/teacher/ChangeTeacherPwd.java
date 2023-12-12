package teacher;

import bean.Teacher;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangeTeacherPwd")
public class ChangeTeacherPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTeacherPwd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("teacher");
		System.out.println("分割线");
		System.out.println(id);
		String oldPwd = request.getParameter("oldPwd");
		String newPwd1 = request.getParameter("newPwd1");
		String newPwd2 = request.getParameter("newPwd2");
		System.out.println(newPwd1 + newPwd2);
		System.out.println(oldPwd);
		Teacher teacher = DaoFactory.getTeacherDaoInstance().search(id);
		System.out.println(teacher.getT_pwd());
		if (teacher.getT_pwd().equals(oldPwd) && newPwd1.equals(newPwd2) && newPwd1 != null) {
			teacher.setT_pwd(newPwd1);
			int result=DaoFactory.getTeacherDaoInstance().update(teacher, id);
			if(result>0)
				session.setAttribute("teacher_error", "<script>alert('密码修改成功');</script>");
				response.sendRedirect("teacher/teacher_main.jsp");
		} else {
			session.setAttribute("teacher_error", "<script>alert('输入原密码错误或修改后两次密码');</script>");
			response.sendRedirect("teacher/teacher_main.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
