package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class IO {
	public static void outputToText(String string, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.print(string);
		pw.flush();
		pw.close();
	}
	
	public static void outputToText(StringBuffer stringBuffer, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.print(stringBuffer);
		pw.flush();
		pw.close();
	}

	public static String loadText(String fileName) {
		// if(file.isFile() && file.exists())

		File file = new File(fileName);

		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String content = "";
		StringBuilder sb = new StringBuilder();

		while (content != null) {
			try {
				content = bf.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (content == null) {
				break;
			}

			sb.append(content.trim());

		}

		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		return removeSymbol(sb.toString());
		return sb.toString();

	}
	
	public static String removeSymbol(String string) {
		// String str =
		// ",.!，，D_NAME。！；‘’”“《》**dfs  #$%^&()-+1431221中国123漢字かどうかのjavaを決定";
		String result = string.replaceAll("[\\pP‘’“”]", "");
		System.out.println(string);
		return result;

	}
}
