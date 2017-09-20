package util;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import bll.ScoreBll;
import bll.StudentBLL;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;

public class SimpleExcelWrite {
	public void createExcel(OutputStream os) throws WriteException, IOException {
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("成绩单", 0);
		// 创建要显示的具体内容
		Label formate = new Label(0, 0, "姓名");
		sheet.addCell(formate);
		Label floats = new Label(1, 0, "学号");
		sheet.addCell(floats);
		Label integers = new Label(2, 0, "班级");
		sheet.addCell(integers);
		Label booleans = new Label(3, 0, "成绩");
		sheet.addCell(booleans);
		Label dates = new Label(4, 0, "考试时间");
		sheet.addCell(dates);

		// Label name = new Label(0,1,"张三");
		// sheet.addCell(name);
		// Label identity = new Label(1,1,"20121003574");
		// sheet.addCell(identity);
		// Label banji = new Label(2,1,"一班");
		// sheet.addCell(banji);
		// Label score = new Label(3,1,"4");
		// sheet.addCell(score);

		ScoreBll scoreBll = new ScoreBll();
		ResultSet scorers = scoreBll.getScore();
		StudentBLL studentBLL = new StudentBLL();
		try {
			int i = 1;
			while (scorers.next()) {

				int row = scorers.getRow();
				int column = scorers.getMetaData().getColumnCount();

				String s_identity = Long.toString(scorers.getLong("identity"));
				String s_score = Integer.toString(scorers.getInt("score"));
				String date = scorers.getString("date");
				Map sInfo = studentBLL.getStudentInfo(scorers
						.getLong("identity"));

				String s_name = sInfo.get("name").toString();
				String s_banji = sInfo.get("class").toString();
				System.out.println(s_identity + date + s_score);

				Label name = new Label(0, i, s_name);
				sheet.addCell(name);
				Label identity = new Label(1, i, s_identity);
				sheet.addCell(identity);
				Label banji = new Label(2, i, s_banji);
				sheet.addCell(banji);
				Label score = new Label(3, i, s_score);
				sheet.addCell(score);
				Label examdate = new Label(4, i, date);
				sheet.addCell(examdate);
				// }
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 浮点数据
		// Number number = new Number(1,1,3.1415926535);
		// sheet.addCell(number);
		// //整形数据
		// Number ints = new Number(2,1,15042699);
		// sheet.addCell(ints);
		// Boolean bools = new Boolean(3,1,true);
		// sheet.addCell(bools);
		// //日期型数据
		// Calendar c = Calendar.getInstance();
		// Date date = c.getTime();
		// WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT1);
		// DateTime dt = new DateTime(4,1,date,cf1);
		// sheet.addCell(dt);
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

}
