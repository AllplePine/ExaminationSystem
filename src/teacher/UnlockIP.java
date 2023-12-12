package teacher;

import bean.Student;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UnlockIP")
public class UnlockIP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String stu_id=request.getParameter("id");
		String examname=request.getParameter("examname");
		List<Student> list=DaoFactory.getStudentDaoInstance().search();
		for(Student stu:list)
		{
			if(stu.getStu_id().equals(stu_id)&&stu.getStu_exam().equals(examname))
			{
				stu.setStu_ip("null");
				int result=DaoFactory.getStudentDaoInstance().updateIP(stu, stu_id);
				if(result>0)
				{
					response.sendRedirect("teacher/manage_unlock.jsp");
					return;
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
