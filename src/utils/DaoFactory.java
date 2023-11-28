package utils;

import dao.IExamDao;
import dao.IStudentDao;
import dao.ITeacherDao;
import daoimpl.ExamDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.TeacherDaoImpl;

public class DaoFactory {
	/**
     * 获取任务管理DAO
     * @return DAO
     */
	public static IExamDao getExamDaoInstance() {
		// TODO Auto-generated method stub
		return new ExamDaoImpl();
	}
	public static IStudentDao getStudentDaoInstance() {
		// TODO Auto-generated method stub
		return new StudentDaoImpl();
	}
	public static ITeacherDao getTeacherDaoInstance() {
		// TODO Auto-generated method stub
		return new TeacherDaoImpl();
	}
}
