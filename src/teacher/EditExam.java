package teacher;

import bean.Exam;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditExam")
public class EditExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String old_examname = (String) session.getAttribute("examname");
		String examname = request.getParameter("examname");
		session.setAttribute("examname", examname);
		String exam_starttime = request.getParameter("exam_starttime");
		String[] exam_autostart = request.getParameterValues("exam_autostart");
		boolean autostart = false;
		if (exam_autostart != null) {
			autostart = true;
		}
		Exam exam = new Exam();
		exam.setE_name(examname);
		exam.setE_starttime(exam_starttime);
		exam.setE_autostart(autostart);
		int result = DaoFactory.getExamDaoInstance().updaeExamInfo(exam, old_examname);
		if (result > 0) {
			response.sendRedirect("teacher/add_exam.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
