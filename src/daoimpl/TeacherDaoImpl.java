package daoimpl;

import bean.Teacher;
import dao.ITeacherDao;
import utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements ITeacherDao {

	@Override
	public String login(String username, String password) {

		return null;
	}

	@Override
	public int add(Teacher teacher) {

		return 0;
	}

	@Override
	public int delete(String username) {

		return 0;
	}

	@Override
	public List<Teacher> search() {

		return null;
	}

	@Override
	public int update(Teacher teacher, String username) {

		return 0;
	}

	@Override
	public Teacher search(String username) {

		return null;
	}


}
