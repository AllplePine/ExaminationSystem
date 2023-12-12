package teacher;

import bean.Exam;
import utils.DBUtil;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class ClearExam
 */
@WebServlet("/ClearExam")
public class ClearExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearExam() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examname = request.getParameter("examname");
		String id = request.getParameter("id");
		Exam exam = DaoFactory.getExamDaoInstance().search(examname);
		if (exam.getE_file()==true)
		{
			DaoFactory.getExamDaoInstance().update(exam, examname);
			DaoFactory.getExamDaoInstance().delete(examname);
			try {
				String infosql = "delete from information";
				DBUtil.executeUpdate(infosql);
				DBUtil.close();
				String sql = "delete from student where stu_exam='" + examname + "'";
				DBUtil.executeUpdate(sql);
				DBUtil.close();
				String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/" + examname);
				File file = new File(savePath);
				// 判断上传文件的保存目录是否存在
				if (file.exists()) {// 判断文件是否存在
					deleteFile(file);
					File zip = new File(this.getServletContext().getRealPath("/WEB-INF/upload/zips/"+examname+".zip"));
					if(zip.exists()) zip.delete();
				} else {
					System.out.println("所删除的文件不存在");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(id.equals("1"))
				response.sendRedirect("teacher/exam_after.jsp");
			else
				response.sendRedirect("admin/clear_exam.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static Boolean deleteFile(File file) {
		if (file == null || !file.exists()) {
			System.out.println("文件删除失败,请检查文件是否存在以及文件路径是否正确");
			return false;
		}
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				deleteFile(f);
			} else {
				f.delete();
			}
		}
		//文件夹删除
		file.delete();
		return true;
	}
}
