package bll;

import java.sql.*;
import java.util.Map;

import util.Convertion;

import dal.UserDao;
import model.Student;

public class StudentBLL {
	public boolean isStudentExist(Student student){
		long identity=student.getIdentity();
		String table="student";
		UserDao userDao=new UserDao();
		int result=userDao.countUser(identity, table);
		if (result==0) {
			//regist student
			return false;
		}
		else {			
			return true;
		}
	}
	
	public boolean studentRegist(Student student) {
		UserDao userDao=new UserDao();
		long identity=student.getIdentity();
		String name=student.getName();
		String password=student.getPassword();
		String banji=student.getBanji();
		String table="student";
		int result=userDao.userRegist(identity,banji,name,password,table);
		if (result==0) {
			return false;
			
		}
		return true;
	}
	
	public int login(Student student) {
		UserDao userDao=new UserDao();
		long identity=student.getIdentity();
		String password=student.getPassword();
		String table="student";
		if (!isStudentExist(student)) {
			return 0;
		}
		else{
			if (userDao.login(identity, password, table)>0) {
//				Map sMap=getStudentInfo(identity);
//				String name=(String) sMap.get("name");
//				System.out.println(name);
				return 1;
			}
			else {
				return -1;
			}
		}		
	}
	
	public Map getStudentInfo(long identity) {
		UserDao userDao=new UserDao();
		Convertion convertion=new Convertion();
		String table="student";
		ResultSet sResultSet=userDao.getUserInfo(identity, table);
		Map sMap = null;
		try {
			if (sResultSet.next()) {
				sMap = convertion.getResultMap(sResultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sMap;
	}
}
