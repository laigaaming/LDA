package test;

import java.io.File;

import util.Convertion;
import util.IO;

public class Verification {
	private String t = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();
	private int num = t.indexOf(".metadata");
	private String path = t.substring(1, num).replace('/', '\\')
			+ "LDAv1.0\\WebContent\\WEB-INF\\";

	public void verify() {

		String filepath = path + "data/sample/3";
		File file = new File(filepath);
		IO io=new IO();
		Convertion convertion=new Convertion();

		String[] filelist = file.list();
		StringBuffer result=new StringBuffer();
		String title="File name     "+"      init score"+"  actual score";
		result.append(title);
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(filepath + "/" + filelist[i]);
			if (!readfile.isDirectory()) {
				// System.out.println("path=" + readfile.getPath());
				// System.out.println("absolutepath=" + readfile.getAbsolutePath());
				String fileName = filepath + "/" + readfile.getName();
				int initscore=Integer.parseInt(readfile.getName().substring(0, 1));
				System.out.println("name=" + fileName);

				String text = io.loadText(fileName);
				System.out.println(text);

				System.out.println("处理中...");
				System.out.println(text);
				int actualscore=5;
				System.out.println("init score:"+initscore);
				System.out.println("actual score:"+actualscore);
				System.out.println("处理完毕");
				System.out.println("开始生成文件");
				
				result.append("\r\n");
				result.append(readfile.getName()+"       "+initscore+"               "+actualscore);
				io.outputToText(result, path +"data/temp/verification.txt" );

				System.out.println("生成文件完毕");
			}
		}

	}
}
