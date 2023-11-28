package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ITeacherDao;
import utils.*;

public class TeacherDaoImpl implements ITeacherDao {

	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		// 查询此用户名和密码是否存在
		String login = "";
		Teacher teacher = search(username);
		if (!teacher.getT_pwd().equals("")) {
			if (teacher.getT_pwd().equals(password)) {
				if (teacher.getT_manager()) {
					login = "admin";
				} else {
					login = "teacher";
				}

			}
		} else {
			login = "nothing";
		}
		return login;
	}

	@Override
	public int add(Teacher teacher) {
		// TODO Auto-generated method stub
		// 编写sql语句
		String sql = "insert into teacher(t_username,t_pwd,t_name,t_manager) values (?,?,?,?)";

//		try {
//			DesUtils des = new DesUtils("leemenz");
//			teacher.setT_pwd(des.encrypt(teacher.getT_pwd()));
//			System.out.println("成功");
//		} catch (Exception e1) { // TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("失败");
//		}

		int result = 0;
		// 动态为sql的参数赋值
		try {
			PreparedStatement ps = DBUtil.executePreparedStatement(sql);
			ps.setString(1, teacher.getT_username());
			ps.setString(2, teacher.getT_pwd());
			ps.setString(3, teacher.getT_name());
			ps.setBoolean(4, teacher.getT_manager());
			// 执行sql语句
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			String sql = "delete from teacher where t_username='" + username + "'";
			result = DBUtil.executeUpdate(sql);
			DBUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Teacher> search() {
		// TODO Auto-generated method stub
		List<Teacher> list = new ArrayList<Teacher>();
		String sql = "SELECT * FROM teacher";
		try {
			ResultSet rs = DBUtil.executeQuery(sql);
			//rs.next();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setT_username(rs.getString("t_username"));
				teacher.setT_pwd(rs.getString("t_pwd"));
				teacher.setT_name(rs.getString("t_name"));
				teacher.setT_manager(rs.getBoolean("t_manager"));
				list.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int update(Teacher teacher, String username) {
		// TODO Auto-generated method stub
		// sql
		String spl = "update teacher set t_pwd= '" + teacher.getT_pwd() + "',t_name= '" + teacher.getT_name()
				+ "',t_manager= " + teacher.getT_manager() + " where t_username='" + username + "'";
		int result = 0;
		try {
			result = DBUtil.executeUpdate(spl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Teacher search(String username) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		String sql = "select * from teacher where t_username = ?";
		PreparedStatement ps = DBUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				teacher.setT_username(rs.getString("t_username"));
				teacher.setT_pwd(rs.getString("t_pwd"));
				teacher.setT_name(rs.getString("t_name"));
				teacher.setT_manager(rs.getBoolean("t_manager"));
			} else {
				teacher.setT_pwd("");
				teacher.setT_manager(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}


}

