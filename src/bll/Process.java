package bll;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import util.Convertion;
import util.CopyFile;
import util.Datetime;
import util.IO;


public class Process {
	private static final List<String> DIC = new ArrayList<>();
	private static int MAX_LENGTH = 0;
	
	private String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private int num=t.indexOf(".metadata");
	private String path=t.substring(1,num).replace('/', '\\')+"LDAv1.0\\WebContent\\WEB-INF\\";
	public void loadDic() {
		
		try {			
	        System.out.println(path);
			System.out.println("开始初始化词典");
			int max = 1;
			int count = 0;
			List<String> lines = Files.readAllLines(Paths.get(path+"data/dic/dic.txt"), Charset.forName("utf-8"));
			for (String line : lines) {
				DIC.add(line);
				count++;
				if (line.length() > max) {
					max = line.length();
				}
			}
			MAX_LENGTH = max;
			System.out.println("完成初始化词典，词数目：" + count);
			System.out.println("最大分词长度：" + MAX_LENGTH);
		} catch (IOException ex) {
			System.err.println("词典装载失败:" + ex.getMessage());
		}
	}

	public String processAnswer(String answer,long identity) {
		Convertion convertion=new Convertion();
		IO printout=new IO();
		System.out.println("处理中...");
		String answer2 =removeSymbol(answer);
		String result = convertion.listToString(seg(answer2));
		System.out.println(result);
		System.out.println("处理完毕");
		System.out.println("开始生成文件");

		printout.outputToText(result, path+"data/answer/"+identity+".txt" );
		
		File sFile=new File(path+"data/answer/"+identity+".txt");
		Datetime datetime=new Datetime();
		String time= datetime.getTime();
		File tFile=new File(path+"data/answer/archive/"+identity+"_"+time+".txt");
		CopyFile copyFile=new CopyFile();
		copyFile.fileChannelCopy(sFile, tFile);
		System.out.println("生成文件完毕");
		return result;
	}

	public static List<String> seg(String text) {
		List<String> result = new ArrayList<>();
		while (text.length() > 0) {
			int len = MAX_LENGTH;
			if (text.length() < len) {
				len = text.length();
			}
			// 取指定的最大长度的文本去词典里面匹配
			String tryWord = text.substring(0, 0 + len);
			while (!DIC.contains(tryWord)) {
				// 如果长度为一且在词典中未找到匹配，则按长度为一切分
				if (tryWord.length() == 1) {
					break;
				}
				// 如果匹配不到，则长度减一继续匹配
				tryWord = tryWord.substring(0, tryWord.length() - 1);
			}
			result.add(tryWord);
			// 从待分词文本中去除已经分词的文本
			text = text.substring(tryWord.length());
		}
		return result;
	}

	public static String removeSymbol(String string) {
		// String str =
		// ",.!，，D_NAME。！；‘’”“《》**dfs  #$%^&()-+1431221中国123漢字かどうかのjavaを決定";
		String result = string.replaceAll("[\\pP‘’“”]", "");
		System.out.println(string);
		return result;

	}
}
