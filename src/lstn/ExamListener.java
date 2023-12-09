package lstn;

import daoimpl.ExamDaoImpl;
import utils.Config;
import utils.ConfigUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Timer;

public class ExamListener implements ServletContextListener {
    private Timer timer;
    private ServletContext sc;
    ExamDaoImpl examDao = new ExamDaoImpl();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public ExamListener() {
    }

    public void contextInitialized(ServletContextEvent arg0) {
        this.sc = arg0.getServletContext();
        String configPath = this.sc.getRealPath("") + Config.CONFIG_FILE;
        File configFile = new File(configPath);
      //  configFile.
        Config config;
        if (configFile.exists()) {
            config = ConfigUtil.parseConfig(configFile);
        } else {
            config = new Config();
            ConfigUtil.writeConfig(configFile, config);
        }
        System.out.println("config:"+config.getInterval());
        this.sc.setAttribute("config", config);
        this.sc.setAttribute("configPath", configPath);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        this.timer.cancel();
    }


}
