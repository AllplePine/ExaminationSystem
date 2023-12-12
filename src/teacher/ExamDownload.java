package teacher;

import bean.Exam;
import utils.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet("/ExamDownload")
public class ExamDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExamDownload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session=request.getSession();
		String examname = (String) session.getAttribute("examname");
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload/"+examname);
        String fileName = "";
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        Exam exam=DaoFactory.getExamDaoInstance().search(examname);
        fileName=exam.getE_examination();
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileInputStream in = new FileInputStream(fileSaveRootPath + "/" + fileName);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
