package lstn;

import bean.Exam;
import daoimpl.ExamDaoImpl;
import utils.Config;
import utils.ConfigUtil;
import utils.DaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Exam> le = examDao.search();
        List<Exam> list = DaoFactory.getExamDaoInstance().search();
		for (Exam exam : list) {
			if (exam.getE_isstart()) {
                this.sc.setAttribute("examname", exam.getE_name());
			}
		}

        this.timer = new Timer();
        long delay = (long)(config.getInterval()*1000);
        this.timer.schedule(new checkExamTask(), delay);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        this.timer.cancel();
    }

    class checkExamTask extends TimerTask {
        checkExamTask() {
        }

        public void run() {
            Exam activeExam = null;
            Exam nearestExam = null;
            Config config = (Config) ExamListener.this.sc.getAttribute("config");
            long delay = (long)(config.getInterval()*1000);
            if (ExamListener.this.sc.getAttribute("examname") == null) {
                List<Exam> le = examDao.search();
                Date nearest = null;
                Iterator var9 = le.iterator();

                label45:
                while(true) {
                    Exam e;
                    Date date = null;
                    do {
                        do {
                            do {
                                if (!var9.hasNext()) {
                                    break label45;
                                }

                                e = (Exam)var9.next();
                                if (e.getE_isstart()) {
                                    activeExam = e;
                                    break label45;
                                }
                            } while(!e.getE_autostart());
                        } while(e.getE_isend());
                        try {
                            date = simpleDateFormat.parse(e.getE_starttime());
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    } while(nearest != null && !date.before(nearest));
                    try {
                        nearest = simpleDateFormat.parse(e.getE_starttime());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    nearestExam = e;
                    if(e.getE_examination()==null||"null".equals(e.getE_examination())) nearestExam=null;
                }
                if (activeExam != null) {
                    ExamListener.this.sc.setAttribute("examname", activeExam.getE_name());
                } else if (nearestExam != null) {
                    Date now = new Date();
                    Date target = null;
                    try {
                        target = simpleDateFormat.parse(nearestExam.getE_starttime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (target.before(now)) {
                        nearestExam.setE_isstart(true);
                        examDao.updateExamStart(nearestExam);
                        ExamListener.this.sc.setAttribute("examname", nearestExam.getE_name());
                    } else if (target.getTime() - now.getTime() < delay) {
                        delay = target.getTime() - now.getTime();
                    }
                }
            }

            ExamListener.this.timer.schedule(ExamListener.this.new checkExamTask(), delay);
        }
    }
}
