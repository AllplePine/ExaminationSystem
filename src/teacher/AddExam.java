package teacher;

import bean.Exam;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AddExam")
public class AddExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExam() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String examname = request.getParameter("examname");
		String exam_starttime = request.getParameter("exam_starttime");
		String[] exam_autostart = request.getParameterValues("exam_autostart");
		HttpSession session=request.getSession();
		String teacher=(String)session.getAttribute("teacher");
		boolean autostart = false;
		if (exam_autostart != null) {
			autostart = true;
		}
		Exam exam=new Exam();
		exam.setE_name(examname);
		exam.setE_starttime(exam_starttime);
		exam.setE_autostart(autostart);
		exam.setE_teacher(teacher);
		int result = 0;
		List<Exam> search = DaoFactory.getExamDaoInstance().search();
		boolean no_exist = true;
		for (Exam search1 : search) {
			String e_name = search1.getE_name();
			if(exam.getE_name().equalsIgnoreCase(e_name)) no_exist=false;
		}
		if(no_exist){
			result=DaoFactory.getExamDaoInstance().add(exam);
		}else{
			PrintWriter out = response.getWriter();
			out.print("<script>alert('考试已存在!'); window.location.href='/exam/teacher/exam_before.jsp'</script>");
		}
		// 如果执行成功跳转页面
		if (result > 0) {
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			savePath += "/"+examname;
			File file = new File(savePath);
			file.mkdir();
			response.sendRedirect("teacher/exam_before.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
