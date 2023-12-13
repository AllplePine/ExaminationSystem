package utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
	private static Properties pro = new Properties();
	private static  String URL;
	private static  String USER;
	private static  String PASSWORD;
	static {
		try {
			pro.load(JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties"));
			URL = pro.getProperty("url");
			USER = pro.getProperty("username");
			PASSWORD=pro.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected static Statement s = null;
	protected static ResultSet rs = null;
	protected static Connection conn = null;

	public static synchronized Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static int executeUpdate(String sql){
		int result = 0;
		try {
			s = getConnection().createStatement();
			result = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet executeQuery(String sql){
		try {
			s = getConnection().createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static PreparedStatement executePreparedStatement(String sql){
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	public static void rollback(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try {
			if(rs != null)
				rs.close();
			if(s != null)
				s.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
