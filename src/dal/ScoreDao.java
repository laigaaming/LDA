package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;
import util.Datetime;

public class ScoreDao {
	public ResultSet getScore(String table) {
		Connection conn = null;
		Statement stmt = null;

		String sql = "select * from " + table;
		System.out.println(sql);

		conn = DBUtil.getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return rs;
	}
	
	public int addScore(long identity,int score,String table) {
		Connection conn = null;
		Statement stmt = null;		
		
		Datetime dt=new Datetime();
		String datetime=dt.getDateTime();
		String sql = "insert into "+table+"(identity,score,date) values("+identity+","+score+",'"+datetime+"')";
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
}
