package admin;

import utils.Config;
import utils.ConfigUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EditSystemConfig")
public class EditSystemConfig extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext servletContext = request.getServletContext();
        Config config1 = (Config)servletContext.getAttribute("config");
        config1.setInterval(Integer.parseInt(request.getParameter("interval")));
        config1.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
        config1.setTimegap(Integer.parseInt(request.getParameter("timegap")));
        config1.setMinfilesize(Integer.parseInt(request.getParameter("minfilesize")));
        config1.setMaxfilesize(Integer.parseInt(request.getParameter("maxfilesize")));
        File configFile = new File((String) servletContext.getAttribute("configPath"));
        String[] candeletes = request.getParameterValues("candelete");
        boolean candelete = false;
        if (candeletes != null) {
            candelete = true;
        }
        config1.setCandelete(candelete);
        ConfigUtil.writeConfig(configFile, config1);
        PrintWriter out = response.getWriter();
        out.print("<script>alert('修改成功!'); window.location.href='/exam/admin/system.jsp'</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
