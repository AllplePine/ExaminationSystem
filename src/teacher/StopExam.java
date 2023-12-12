package teacher;

import bean.Exam;
import utils.DaoFactory;
import utils.InformationClear;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StopExam")
public class StopExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examname = request.getParameter("examname");
		String id = request.getParameter("id");
		Exam exam = DaoFactory.getExamDaoInstance().search(examname);
		exam.setE_isend(true);
		exam.setE_isstart(false);
		request.getServletContext().setAttribute("examname",null);
		request.getSession().setAttribute("examname",null);
		int result=DaoFactory.getExamDaoInstance().update(exam, examname);
		InformationClear.clear();
		if(result>0)
		{
			if(id.equals("1"))
				response.sendRedirect("teacher/exam_after.jsp");
			else
				response.sendRedirect("admin/clear_exam.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
