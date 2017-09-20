package bll;

import model.Admin;
import model.Student;
import dal.AdminDao;
import dal.UserDao;

public class AdminBLL {
	private static String t = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();
	private static int num = t.indexOf(".metadata");
	private static String path = t.substring(1, num).replace('/', '\\')
			+ "LDAv1.0\\WebContent\\WEB-INF\\";

	/*public boolean isAdminExist(Admin admin) {
		String identity = admin.getIdentity();
		String table = "admin";
		UserDao userDao = new UserDao();
		int result = userDao.countUser(identity, table);
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}*/

	public static boolean trainSample() {
		String answerPath = path + "data/answer/20121003574.txt";
		Train train = new Train();
		try {
			train.train3p();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public int login(Admin admin) {
		String identity=admin.getIdentity();
		String password=admin.getPassword();
		AdminDao adminDao=new AdminDao();
		String table = "admin";
		
		if (adminDao.login(identity, password, table) > 0) {
			return 1;
		} else {
			return -1;
		}

	}
}
