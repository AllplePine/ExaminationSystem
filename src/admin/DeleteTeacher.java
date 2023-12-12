package admin;

import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteTeacher")
public class DeleteTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteTeacher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("delete_username");
		int result=DaoFactory.getTeacherDaoInstance().delete(username);
		if(result>0)
		{
			response.sendRedirect("admin/manage_teacher.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
