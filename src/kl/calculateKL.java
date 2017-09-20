package kl;

import java.io.File;
//import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import jeasy.analysis.MMAnalyzer;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.lang.Math;

public class calculateKL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws  FileNotFoundException,IOException
	{
		
		
		// TODO Auto-generated method stub;
		ArrayList<Entity> enList1=new ArrayList<Entity>();
		enList1=CalcuP("C:/Users/liuyu/workspace/KL/KL/zhangailing.txt");
		ArrayList<Entity> enList2=new ArrayList<Entity>();
		enList2=CalcuP("C:/Users/liuyu/workspace/KL/KL/zhangailing2.txt");
		ArrayList<Entity>enList3=new ArrayList<Entity>();
		enList3=CalcuP("C:/Users/liuyu/workspace/KL/KL/maozedong.txt");
		double f1=CalKL(enList1,enList2);
		double f2=CalKL(enList2,enList1);
		double f3=CalKL(enList1,enList3);
		double f4=CalKL(enList3,enList1);
		double f5=CalKL(enList2,enList3);
		double f6=CalKL(enList3,enList2);
		System.out.println("����С��Բ������й���Ű���ʲô�����ܡ������롶��С��Բ�����Ű����һ���Ρ���KL���룺 "+f1);
		System.out.println("����С��Բ�����Ű����һ���Ρ��롶��С��Բ������й���Ű���ʲô�����ܡ�������KL����"+f2);
		System.out.println("����С��Բ������й���Ű���ʲô�����ܡ������롶1945��ë�󶫺ͽ���ʯ������̸��ǰ�������鱨ս����KL���� "+f3);
		System.out.println("��1945��ë�󶫺ͽ���ʯ������̸��ǰ�������鱨ս���롶��С��Բ������й���Ű���ʲô�����ܡ�������KL���� "+f4);
		System.out.println("����С��Բ���Ű����һ���Ρ��롶1945��ë�󶫺ͽ���ʯ������̸��ǰ�������鱨ս����KL����"+f5);
		System.out.println("��1945��ë�󶫺ͽ���ʯ������̸��ǰ�������鱨ս���롶��С��Բ���Ű����һ���Ρ���KL����"+f6);
//System.out.println(enList.size());	
		
		
	
}
	
	/********
	 this function read in a string from disk file* ***********/
	
	public static String GetFileText(String path) throws  FileNotFoundException,IOException
	{   
		InputStreamReader inStreamReader=new InputStreamReader(new FileInputStream(path),"UTF-8");
		//String strFile1=
		BufferedReader bufReader=new BufferedReader(inStreamReader);
		String line;
		StringBuilder sb=new StringBuilder();
		while((line=bufReader.readLine())!=null)
		{
			sb.append(line+"��");
		}
		inStreamReader.close();
		bufReader.close();
		String strFile=sb.toString();
	  
		
		
		return strFile;
		
	}

	/*this function cut a piece of text in to words sequeces*/
	public static String CutText(String path)throws FileNotFoundException,IOException
	{
		
      String fileText=GetFileText(path);
     
		
		MMAnalyzer analyzer=new MMAnalyzer();
		String result =null;
		String spliter="|";
		try  	
        {
			result = analyzer.segment(fileText, spliter);	
		}  	
        catch (IOException e)  	
        { 	
        	e.printStackTrace(); 	
        } 	
		//System.out.print(result);
        return result;
		
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String CutTextSingleCharacter(String path)throws FileNotFoundException,IOException
	{   String text=GetFileText(path);
		String proText=null;
		Pattern pattern=Pattern.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]");
		Matcher m=pattern.matcher(text);
		StringBuffer sb=new StringBuffer();
		Boolean flag=m.find();
		while(flag)
		{
			int start=m.start();
			int end=m.end();
			sb.append(text.substring(start, end)+"|");
			//System.out.println(text.substring(start,end));
			flag=m.find();
		}
       proText=sb.toString();
		return proText;
}

	
	
	/*****�����ı���ÿ��������ֵ����Ƶ�ʴ������********/
	public static ArrayList<Entity> CalcuP(String path) throws IOException
	{    //�Դ�Ϊ��λ���������
		String result=CutText(path);
		//����Ϊ��λ���������
		//String result=CutTextSingleCharacter(path);
		String []words=result.split("\\|");
		
	  
		ArrayList<Entity> enList=new ArrayList();
		for(String w: words)
		{  w=w.trim();
			Entity en=new Entity();
			en.word=w;
			en.pValue=1;
			enList.add(en);
			//System.out.println(w);
		}
	
		float total=enList.size();
		for(int i=0;i<enList.size()-1;i++)
		{ 
			
			if(!enList.get(i).word.isEmpty())
			{
				for(int j=i+1;j<enList.size();j++)
				{
					if(enList.get(i).word.equals(enList.get(j).word))
					{
						enList.get(i).pValue++;
						enList.get(j).pValue=0;
						enList.get(j).word="";
					}
				}
			}
		}
		for(int i=enList.size()-1;i>=0;i--)
		{
			if(enList.get(i).pValue<1.0)
				enList.remove(i);
		}
		for(int i=0;i<enList.size();i++)
		{
			enList.get(i).pValue=enList.get(i).pValue/total;
		}
		
	return enList;
	}
	
	
/*���ڼ��������ı��������*/
public static float CalKL(ArrayList<Entity>p,ArrayList<Entity>q)
{  
	float kl=0;
	
    float infinity=10000000;//�����
    double accretion=infinity;//�������������ĳ�ʼֵΪ�����
    //��q���ҳ���p�����Ӧ�ʵĸ��ʣ�����ҵ��ˣ��ͽ�accretion��ֵ���£����ۼӵ���������棻���û�ҵ�����������Ϊ�����
	for(int i=0;i<p.size();i++)
	{   
		if(q.size()!=0)
		{   for(int j=q.size()-1;j>=0;j--)
			{	
				if(p.get(i).word.equals(q.get(j).word))
				{  accretion=p.get(i).pValue*Math.log(p.get(i).pValue/q.get(j).pValue);
					//q.remove(j);
					break;
					
				}
		}
		
		kl+=accretion;
		accretion=infinity;
		}
		
		
	}

	
	return kl;
	
}

}
