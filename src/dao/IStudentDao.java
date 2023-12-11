package dao;

import bean.Student;

import java.util.List;

/**
 * 学生类dao 接口
 * @Description
 * @author jiangwen
 * @version
 *
 */
public interface IStudentDao {
	public boolean login(String stu_id, String stu_name);
	public boolean login(String stu_id, String stu_name, String stu_exam);
	public int add(Student student);
	public int delete(String username);
	public Student search(String username);
	public List<Student> search();
	public int update(Student student, String username);
	public int updateIP(Student student, String username);
	public Student searchFor(String id, String examname);
    public Student searchForIp(String ip, String examname);
	public int updateForSubmit(Student student, String username);
}
