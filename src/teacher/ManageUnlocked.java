package teacher;

import bean.Exam;
import bean.Student;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ManageUnlocked
 */
@WebServlet("/ManageUnlocked")
public class ManageUnlocked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUnlocked() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Student> list= DaoFactory.getStudentDaoInstance().search();
		StringBuffer sb=new StringBuffer();
		String stu_id=request.getParameter("id");
		List<Exam> list_= DaoFactory.getExamDaoInstance().search();
		String  examname=null;
		for(Exam exam:list_)
		{
			if(exam.getE_isstart())
				examname=exam.getE_name();
		}
		for(Student stu:list)
		{
			if(stu.getStu_id().equals(stu_id) && stu.getStu_exam().equals(examname))
			{
				sb.append("<tr><td>"+stu.getStu_id()+"</td>");
				sb.append("<td>"+stu.getStu_name()+"</td>");
				sb.append("<td>"+stu.getStu_class()+"</td>");
				
				if(stu.getStu_ip()!=null&&!"null".equals(stu.getStu_ip()))
				{
					sb.append("<td>"+stu.getStu_ip()+"</td>");
					sb.append("<td>"+"<a href='../UnlockIP?id="+stu.getStu_id()+"&examname="+examname+"' class='glyphicon glyphicon-edit btn btn-info'>解除绑定</a>"+"</td></tr>");
				}
				else
					sb.append("<td>未绑定ip</td><td></td></tr>");
			}
		}
		request.getSession().setAttribute("unlock", sb);
		response.sendRedirect("teacher/manage_unlock.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
