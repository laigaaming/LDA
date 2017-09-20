package dal;

import java.sql.*;

import com.mysql.jdbc.log.Log;


import util.DBUtil;

import model.Student;

public class UserDao {
	public int userRegist(long identity,String banji, String name, String password,  String table) {
		Connection conn = null;
		Statement stmt = null;		

		String sql = "insert into "+table+"(identity,class,name,password) values("+identity+",'"+banji+"','"+name+"','"+password+"')";
		System.out.println(sql);
		
		
		conn = DBUtil.getConnection();
		int result = 0;
		try {
			stmt = conn.createStatement();
			result=stmt.executeUpdate(sql);
			System.out.println("regist result:"+result);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public int countUser(long identity,String table) {
		Connection conn = null;
		Statement stmt = null;

		String sql = "select count(*) from "+table+" where identity=" + identity;
//		System.out.println(sql);
		
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		int rowCount = 0;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCount;
	}
	
	public int login(long identity, String password, String table) {
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "select count(*) from "+table+" where identity=" + identity
				+ " and password='" + password + "'";

		System.out.println(sql);		
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		int rowCount = 0;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public ResultSet getUserInfo(long identity, String table) {
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "select * from "+table+" where identity=" + identity;
		System.out.println(sql);
		
		conn=DBUtil.getConnection();
		ResultSet rs = null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return rs;
	}
}

