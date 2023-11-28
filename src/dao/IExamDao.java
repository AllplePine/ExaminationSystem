package dao;

import bean.Exam;

import java.util.List;


public interface IExamDao {

	public int add(Exam exam);

	public int delete(String examname);

	public Exam search(String examname);

	public List<Exam> search();
	public List<Exam> searchFor(String teacher);
	public List<Exam> searchForStu(String student);


	public int update(Exam exam, String examname);
	public int updateFor(Exam exam, String examname);

	public int updaeExamInfo(Exam exam, String examname);
}
