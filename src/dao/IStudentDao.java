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
	/**
	 * 学生登录
	 * @param stu_id id
	 * @param stu_name 姓名
	 * @return 返回登陆结果 登陆成功为true ,否则为false
	 */
	public boolean login(String stu_id, String stu_name);
	public boolean login(String stu_id, String stu_name, String stu_exam);
	/**
	 * 添加学生
	 * @param student 学生信息
	 * @return 更改数据库受影响的行数
	 */
	public int add(Student student);
	/**
	 * 删除学生
	 * @param username 学生主键
	 * @return 更改数据库受影响的行数
	 */
	public int delete(String username);
	/**
	 * 查询单个学生信息
	 * @param username 根据主键用户名查询学生信息
	 * @return Student 该学生的信息
	 */
	public Student search(String username);
	/**
	 * 查询所有学生信息
	 * @return List<Student> 所有学生的信息
	 */
	public List<Student> search();
	/**
	 * 更改学生信息
	 * @param username 根据主键用户名查询学生信息
	 * @param student 更改后的学生信息
	 */
	public int update(Student student, String username);
	public int updateIP(Student student, String username);
	public Student searchFor(String id, String examname);
    public Student searchForIp(String ip, String examname);
	public int updateForSubmit(Student student, String username);
}
