package teacher;

import bean.Student;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet implementation class TeacherAddStudent
 */
@WebServlet("/TeacherAddStudent")
public class TeacherAddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherAddStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String examname = (String) session.getAttribute("examname");
		String xuehao = request.getParameter("xuehao");
		String xingming = request.getParameter("xingming");
		String banji = request.getParameter("banji");
		Student student = new Student();
		student.setStu_id(xuehao);
		student.setStu_name(xingming);
		student.setStu_class(banji);
		student.setStu_exam(examname);
		int result = DaoFactory.getStudentDaoInstance().add(student);
		String id = (String) request.getParameter("id");
		if (result > 0)
			if (id != null && id.equals("2")) {
				response.sendRedirect("./FenYe");
			} else {
				response.sendRedirect("./FenYe?exam=" + URLEncoder.encode(examname, "UTF-8"));
			}
	}

}
