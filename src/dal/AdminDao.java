package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class AdminDao {
	public int login(String identity, String password, String table) {
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "select count(*) from "+table+" where identity='" + identity
				+ "' and password='" + password + "'";

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
}
