package dao;

import bean.Teacher;

import java.util.List;

public interface ITeacherDao {

	public String login(String username, String password);

	public int add(Teacher teacher);

	public int delete(String username);

	public Teacher search(String username);
	public Teacher search(String username, String password);

	public List<Teacher> search();

	public int update(Teacher teacher, String username);
}
