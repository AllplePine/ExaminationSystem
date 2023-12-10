package student;

import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ShowInformation
 */
@WebServlet("/ShowInformation")
public class ShowInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String sqlSearch = "select * from information";
		PrintWriter out = response.getWriter();
		ResultSet rs = null;
		rs = DBUtil.executeQuery(sqlSearch);
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td>时间</td><td>消息</td></tr>");
		try {
			while (rs.next()) {
				sb.append("<tr><td  style=\"text-align: left\">"+rs.getString(3) +"</td><td style=\"text-align: left\">"+ rs.getString(2) + "</td></tr>");
			}
			DBUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
