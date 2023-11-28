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

		return 0;
	}

	@Override
	public int delete(String examname) {
		return 0;
	}


	@Override
	public List<Exam> search() {

		return null;
	}

	@Override
	public List<Exam> searchFor(String teacher) {

		return null;
	}

	@Override
	public List<Exam> searchForStu(String id) {

		return null;
	}

	public int updateFor(Exam exam, String examname) {
		return 0;
	}

	@Override
	public int update(Exam exam, String examname) {

		return 0;
	}

	@Override
	public int updaeExamInfo(Exam exam, String examname) {
		return 0;
	}


	@Override
	public Exam search(String examname) {

		return null;
	}

}
