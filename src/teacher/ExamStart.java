package teacher;

import bean.Exam;
import utils.Config;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/ExamStart")
public class ExamStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExamStart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		String examname = (String) session.getAttribute("examname");
		List<Exam> list=DaoFactory.getExamDaoInstance().search();
		boolean isstart=true;
		for(Exam exam :list)
		{
			if(exam.getE_isstart())
			{
				isstart=false;
			}
		}
		if(isstart)
		{
			Exam exam=DaoFactory.getExamDaoInstance().search(examname);
			Config config = (Config)request.getServletContext().getAttribute("config");
			int timegap = config.getTimegap();
			try{
				SimpleDateFormat slp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				if((-1*System.currentTimeMillis()+slp.parse(exam.getE_starttime()).getTime())>timegap*60*1000) throw new Exception("太提前了");
			}catch (Exception e){
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('距离考试开始"+timegap+"分钟之内才允许手动开启考试!'); window.location.href='/exam/teacher/exam_before.jsp'</script>");
				session.removeAttribute("exam_start");
				return;
			}
			session.setAttribute("exam_start", isstart+"");
			exam.setE_isstart(true);
			int result=DaoFactory.getExamDaoInstance().update(exam, examname);
			if(result>0) {
				response.sendRedirect("teacher/exam_before.jsp");
			}
		}else
		{
			session.setAttribute("exam_start", isstart+"");
			response.sendRedirect("teacher/exam_before.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
