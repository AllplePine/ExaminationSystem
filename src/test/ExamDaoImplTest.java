package test;

import bean.Exam;
import daoimpl.ExamDaoImpl;
import org.junit.Test;

public class ExamDaoImplTest {
    @Test
    public void testAdd(){
        ExamDaoImpl examDaoImpl = new ExamDaoImpl();
        Exam exam = new Exam();
        exam.setE_name("Test");
        exam.setE_isstart(false);
        exam.setE_autostart(false);
        exam.setE_teacher("MyTest");
        examDaoImpl.add(exam);
    }
}
