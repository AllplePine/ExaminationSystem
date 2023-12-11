package utils;

import dao.IExamDao;
import dao.IStudentDao;
import dao.ITeacherDao;
import daoimpl.ExamDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.TeacherDaoImpl;

public class DaoFactory {
	public static IExamDao getExamDaoInstance() {
		return new ExamDaoImpl();
	}
	public static IStudentDao getStudentDaoInstance() {
		return new StudentDaoImpl();
	}
	public static ITeacherDao getTeacherDaoInstance() {
		return new TeacherDaoImpl();
	}
}
