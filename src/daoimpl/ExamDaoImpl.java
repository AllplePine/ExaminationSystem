package daoimpl;

import bean.Exam;
import dao.IExamDao;
import utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDaoImpl implements IExamDao {

	@Override
	public int add(Exam exam) {
		// TODO Auto-generated method stub
		String sql = "insert into exam(e_name,e_starttime,e_teacher,e_autostart,e_isend,e_file,e_clear,e_isstart) values (?,?,?,?,?,?,?,?)";
		int result = 0;
		// 动态为sql的参数赋值
		try {
			PreparedStatement ps = DBUtil.executePreparedStatement(sql);
			ps.setString(1, exam.getE_name());//考试名称
			ps.setString(2, exam.getE_starttime());//考试时间
			ps.setString(3, exam.getE_teacher());//创建人
			ps.setBoolean(4, exam.getE_autostart());//是否自动开始
			ps.setBoolean(5, false);//是否结束
			ps.setBoolean(6, false);//是否归档
			ps.setBoolean(7, false);//是否清理
			ps.setBoolean(8, false);//是否正在进行
			// 执行sql语句
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(String examname) {
		int result = 0;
		String sql = "DELETE FROM exam WHERE e_name=?";

		try (PreparedStatement ps = DBUtil.executePreparedStatement(sql)) {
			ps.setString(1, examname);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void updateExamStart(Exam nearestExam){
		String sql = "UPDATE exam SET e_isstart=true WHERE e_name=?";
		int result = 0;

		try (PreparedStatement ps = DBUtil.executePreparedStatement(sql)) {
			//ps.setBoolean(1, nearestExam.getE_isstart());
			ps.setString(1, nearestExam.getE_name());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Exam> search() {
		// TODO Auto-generated method stub
		List<Exam> list = new ArrayList<Exam>();
		String sql = "SELECT * FROM exam";
		try {
			ResultSet rs = DBUtil.executeQuery(sql);
			while (rs.next()) {
				Exam exam = new Exam();
				exam.setE_name(rs.getString("e_name"));
				exam.setE_starttime(rs.getString("e_starttime"));
				exam.setE_teacher(rs.getString("e_teacher"));
				exam.setE_examination(rs.getString("e_examination"));
				exam.setE_isend(rs.getBoolean("e_isend"));
				exam.setE_autostart(rs.getBoolean("e_autostart"));
				exam.setE_file(rs.getBoolean("e_file"));
				exam.setE_clear(rs.getBoolean("e_clear"));
				exam.setE_isstart(rs.getBoolean("e_isstart"));
				list.add(exam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Exam> searchFor(String teacher) {
		List<Exam> list = new ArrayList<Exam>();
		String sql = "SELECT * FROM exam where e_teacher='"+teacher+"'";
		try {
			ResultSet rs = DBUtil.executeQuery(sql);
			while (rs.next()) {
				Exam exam = new Exam();
				exam.setE_name(rs.getString("e_name"));
				exam.setE_starttime(rs.getString("e_starttime"));
				exam.setE_teacher(rs.getString("e_teacher"));
				exam.setE_examination(rs.getString("e_examination"));
				exam.setE_isend(rs.getBoolean("e_isend"));
				exam.setE_autostart(rs.getBoolean("e_autostart"));
				exam.setE_file(rs.getBoolean("e_file"));
				exam.setE_clear(rs.getBoolean("e_clear"));
				exam.setE_isstart(rs.getBoolean("e_isstart"));
				list.add(exam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Exam> searchForStu(String id) {
		List<Exam> list = new ArrayList<Exam>();
		String sql = "SELECT * FROM exam where e_name in (select stu_exam from student where stu_id='"+id+"')";
		try {
			ResultSet rs = DBUtil.executeQuery(sql);
			while (rs.next()) {
				Exam exam = new Exam();
				exam.setE_name(rs.getString("e_name"));
				exam.setE_starttime(rs.getString("e_starttime"));
				exam.setE_teacher(rs.getString("e_teacher"));
				exam.setE_examination(rs.getString("e_examination"));
				exam.setE_isend(rs.getBoolean("e_isend"));
				exam.setE_autostart(rs.getBoolean("e_autostart"));
				exam.setE_file(rs.getBoolean("e_file"));
				exam.setE_clear(rs.getBoolean("e_clear"));
				exam.setE_isstart(rs.getBoolean("e_isstart"));
				list.add(exam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateFor(Exam exam, String examname) {
		String sql = "UPDATE exam SET e_file=? WHERE e_name=?";
		int result = 0;

		try (PreparedStatement ps = DBUtil.executePreparedStatement(sql)) {
			ps.setBoolean(1, exam.getE_file());
			ps.setString(2, exam.getE_name());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int update(Exam exam, String examname) {
		String sql = "UPDATE exam SET e_name=?, e_starttime=?, e_teacher=?, e_examination=?, e_isend=?, e_autostart=?, e_file=?, e_clear=?, e_isstart=? WHERE e_name=?";
		int result = 0;

		try (PreparedStatement ps = DBUtil.executePreparedStatement(sql)) {
			ps.setString(1, exam.getE_name());
			ps.setString(2, exam.getE_starttime());
			ps.setString(3, exam.getE_teacher());
			ps.setString(4, exam.getE_examination());
			ps.setBoolean(5, exam.getE_isend());
			ps.setBoolean(6, exam.getE_autostart());
			ps.setBoolean(7, exam.getE_file());
			ps.setBoolean(8, exam.getE_clear());
			ps.setBoolean(9, exam.getE_isstart());
			ps.setString(10, examname);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updaeExamInfo(Exam exam, String examname) {
		String sql = "UPDATE exam SET e_name=?, e_starttime=?, e_autostart=? WHERE e_name=?";
		int result = 0;

		try (PreparedStatement ps = DBUtil.executePreparedStatement(sql)) {
			ps.setString(1, exam.getE_name());
			ps.setString(2, exam.getE_starttime());
			ps.setBoolean(3, exam.getE_autostart());
			ps.setString(4, examname);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}


	@Override
	public Exam search(String examname) {
		Exam exam = new Exam();
		String sqlSearch = "select * from exam where e_name='" + examname + "'";
		ResultSet rs = null;
		rs = DBUtil.executeQuery(sqlSearch);
		try {
			while (rs.next()) {
				exam.setE_name(rs.getString("e_name"));
				exam.setE_starttime(rs.getString("e_starttime"));
				exam.setE_teacher(rs.getString("e_teacher"));
				exam.setE_examination(rs.getString("e_examination"));
				exam.setE_isend(rs.getBoolean("e_isend"));
				exam.setE_autostart(rs.getBoolean("e_autostart"));
				exam.setE_file(rs.getBoolean("e_file"));
				exam.setE_clear(rs.getBoolean("e_clear"));
				exam.setE_isstart(rs.getBoolean("e_isstart"));
			}
			DBUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;
	}

}
