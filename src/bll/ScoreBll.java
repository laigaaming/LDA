package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import util.Convertion;

import dal.ScoreDao;

public class ScoreBll {
	public ResultSet getScore() {
		ScoreDao scoreDao=new ScoreDao();
		String table="mark";
		ResultSet scorers=scoreDao.getScore(table);
		
		return scorers;
	}
	
	public boolean addScore(long identity,int score) {
		ScoreDao scoreDao=new ScoreDao();
		String table="mark";
		int result=scoreDao.addScore(identity, score, table);
		if (result==0) {
			return false;
			
		}
		return true;
	}
}
