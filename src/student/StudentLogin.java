package student;

import bean.Student;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String stu_id = request.getParameter("stu_id");
		String stu_name = request.getParameter("stu_username");
		String stu_exam =(String) request.getSession().getAttribute("examname");
		System.out.println("stu_exam:"+stu_exam);
		String ip = request.getHeader("x-forwarded-for");
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println("x-forwarded-for: " + request.getHeader("x-forwarded-for"));
		System.out.println("Proxy-Client-IP: " + request.getHeader("Proxy-Client-IP"));
		System.out.println("WL-Proxy-Client-IP: " + request.getHeader("WL-Proxy-Client-IP"));
		System.out.println("X-Real-IP: " + request.getHeader("X-Real-IP"));
		System.out.println("RemoteAddr: " + request.getRemoteAddr());
		System.out.println(ip);
		boolean login = DaoFactory.getStudentDaoInstance().login(stu_id, stu_name,stu_exam);
		boolean can = false;
		if(login){
			List<Student> list = DaoFactory.getStudentDaoInstance().search();
			for (Student stu : list) {
				if (stu.getStu_id().equals(stu_id) && stu.getStu_exam().equals(stu_exam)) {
					if(stu.getStu_ip()==null || stu.getStu_ip().equals("null")){
						Student s = DaoFactory.getStudentDaoInstance().searchForIp(ip,stu_exam);
							if(s==null){
								can = true;
								stu.setStu_ip(ip);
								System.out.println("初次登陆StudentLogin 45");
								DaoFactory.getStudentDaoInstance().updateIP(stu, stu_id);
							}
						}
					else if(stu.getStu_ip().equals(ip)){
						can = true;
					}
				}
			}
		}
		// 登录确认
		try {

			if (login && can) {
				Student student = DaoFactory.getStudentDaoInstance().searchFor(stu_id,stu_exam);
				if (student!=null) {
						//进入考试界面
						String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/" + stu_exam+"/"+stu_id);
						request.getSession().setAttribute("savePath", savePath);
						HttpSession session = request.getSession();
						session.setAttribute("stu_id", stu_id);
						session.setAttribute("stu_name", stu_name);
						response.sendRedirect("student/show_info.jsp");

				} else {//无考试不让登陆
					response.sendRedirect("index.jsp");
				}
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
