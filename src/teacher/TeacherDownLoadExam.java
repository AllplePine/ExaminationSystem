package teacher;

import bean.Exam;
import utils.DaoFactory;
import utils.ToZip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;


@WebServlet("/TeacherDownLoadExam")
public class TeacherDownLoadExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examname = request.getParameter("examname");
		String id = request.getParameter("id");
		Exam exam = DaoFactory.getExamDaoInstance().search(examname);
		exam.setE_file(true);
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/" + examname);
		String zipFilePath=this.getServletContext().getRealPath("/WEB-INF/upload/zips");
		File zips = new File(zipFilePath);//
		if(!zips.exists()) zips.mkdirs();//
		System.out.println(savePath);
		boolean flag = ToZip.fileToZip(savePath, zipFilePath, examname);
		if(flag)
			System.out.println("压缩成功");
		else
			System.out.println("压缩失败");
		int result=DaoFactory.getExamDaoInstance().update(exam, examname);
        String fileName = examname+".zip";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileInputStream in = new FileInputStream(zipFilePath + "/" + fileName);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        if(result>0)
		{

			if(id.equals("1"))
			{
				System.out.println("执行到这了");
				response.sendRedirect("teacher/exam_after.jsp");
			}
			else
				response.sendRedirect("admin/clear_exam.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
