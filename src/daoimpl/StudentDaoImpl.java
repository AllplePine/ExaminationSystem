package daoimpl;

import bean.Student;
import dao.IStudentDao;
import utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

	@Override
	public boolean login(String username, String password) {
		boolean login = false;
		try{
			Student student = search(username);
			if (student.getStu_name().equals(password)) {
				System.out.println(1);
				login = true;
			}
		}catch (Exception e){}
		return login;
	}

	public boolean login(String stu_id, String stu_name,String stu_exam) {
		boolean login = false;
		try{
			Student student = searchStudent(stu_id,stu_name,stu_exam);
			if (student.getStu_name().equals(stu_name)) {
				login = true;
			}
		}catch (Exception e){}
		return login;
	}


	public Student searchStudent(String username, String password, String examname){
		Student student = new Student();
		String sql = "select * from student where stu_id = ? and stu_exam=?";
		PreparedStatement ps = DBUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, username);
			ps.setString(2, examname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student.setStu_id(rs.getString("stu_id"));
				student.setStu_name(rs.getString("stu_name"));
				student.setStu_class(rs.getString("stu_class"));
				student.setStu_exam(rs.getString("stu_exam"));
				student.setStu_ip(rs.getString("stu_ip"));
				student.setStu_submit(rs.getString("stu_submit"));
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public int add(Student student) {
		int result = 0;
		String insert = "insert into student (stu_id,stu_name,stu_class,stu_exam) values(?,?,?,?)";
		PreparedStatement ps = DBUtil.executePreparedStatement(insert);
		try {
			ps.setString(1, student.getStu_id());
			ps.setString(2, student.getStu_name());
			ps.setString(3, student.getStu_class());
			ps.setString(4, student.getStu_exam());
			result = ps.executeUpdate();
			ps.close();
			DBUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(String username) {
		int result = 0;
		try {
			String sql = "delete from student where stu_id='" + username + "'";
			result = DBUtil.executeUpdate(sql);
			DBUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//new
	public Student searchFor(String id, String examname){
		Student student = new Student();
		String sql = "select * from student where stu_id = ? and stu_exam=?";
		PreparedStatement ps = DBUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, id);
			ps.setString(2, examname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student.setStu_id(rs.getString("stu_id"));
				student.setStu_name(rs.getString("stu_name"));
				student.setStu_class(rs.getString("stu_class"));
				student.setStu_exam(rs.getString("stu_exam"));
				student.setStu_ip(rs.getString("stu_ip"));
				student.setStu_submit(rs.getString("stu_submit"));
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//用于判断是否查询到student，下面调用的哪个成员方法方法都无所谓
		if(student.getStu_exam()==null) return null;
		return student;
	}

	@Override
	public Student search(String username) {
		Student student = new Student();
		String sql = "select * from student where stu_id = ?";
		PreparedStatement ps = DBUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student.setStu_id(rs.getString("stu_id"));
				student.setStu_name(rs.getString("stu_name"));
				student.setStu_class(rs.getString("stu_class"));
				student.setStu_exam(rs.getString("stu_exam"));
				student.setStu_ip(rs.getString("stu_ip"));
				student.setStu_submit(rs.getString("stu_submit"));
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}


	public Student searchForIp(String ip, String examname) {
		Student student = new Student();
		String sql = "select * from student where stu_ip = ? and stu_exam=?";
		PreparedStatement ps = DBUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, ip);
			ps.setString(2, examname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student.setStu_id(rs.getString("stu_id"));
				student.setStu_name(rs.getString("stu_name"));
				student.setStu_class(rs.getString("stu_class"));
				student.setStu_exam(rs.getString("stu_exam"));
				student.setStu_ip(rs.getString("stu_ip"));
				student.setStu_submit(rs.getString("stu_submit"));
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(student.getStu_exam()==null) return null;
		return student;
	}

	@Override
	public List<Student> search() {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM student";
		try {
			ResultSet rs = DBUtil.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();
				student.setStu_id(rs.getString("stu_id"));
				student.setStu_name(rs.getString("stu_name"));
				student.setStu_class(rs.getString("stu_class"));
				student.setStu_exam(rs.getString("stu_exam"));
				student.setStu_ip(rs.getString("stu_ip"));
				student.setStu_submit(rs.getString("stu_submit"));
				list.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int update(Student student, String username) {
		String spl = "update student set stu_name= '" + student.getStu_name() + "',stu_class= '"
				+ student.getStu_class() + "',stu_submit= '" + student.getStu_submit() + "',stu_ip= '"
				+ student.getStu_ip() + "',stu_exam= '" + student.getStu_exam() + "' where stu_id='" + username + "'";
		int result = 0;
		try {
			result = DBUtil.executeUpdate(spl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateIP(Student student, String username) {
		String spl = "update student set stu_ip= '"
				+ student.getStu_ip() + "' where stu_id='" + username + "' and stu_exam='"+student.getStu_exam()+"'";
		int result = 0;
		try {
			result = DBUtil.executeUpdate(spl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateForSubmit(Student student, String username) {
		String spl = "update student set stu_submit= '"
				+ student.getStu_submit() + "' where stu_id='" + username + "' and stu_exam='"+student.getStu_exam()+"'";
		int result = 0;
		try {
			result = DBUtil.executeUpdate(spl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
