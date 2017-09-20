package bll;

import java.util.Map;

import util.Convertion;
import util.IO;

public class LoadModel {
	private String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private int num=t.indexOf(".metadata");
	private String path=t.substring(1,num).replace('/', '\\')+"LDAv1.0\\WebContent\\WEB-INF\\";
	
	public double[][] loadphi() {
		IO io=new IO();
		Convertion convertion=new Convertion();
		String phiPath=path+"data/temp/phi.txt";
		String phiString=io.loadText(phiPath);
		
		double[][] phi=convertion.StringToArray(phiString);
//		for (int i = 0; i < phi.length; i++) {
//			for (int j = 0; j < phi[i].length; j++) {
//				System.out.println("topic: "+i+"word :"+j+" =" + phi[i][j]);
//			}
//		}
		
		return phi;
		
//		Map<String, Object> m3=convertion.StringToMap(s3);
//		return m3;
	}
	
	public double[][] loadtheta() {
		IO io=new IO();
		Convertion convertion=new Convertion();
		String thetaPath=path+"data/temp/theta.txt";
		String thetaString=io.loadText(thetaPath);
//		System.out.println(thetaString);
		double[][] theta=convertion.StringToArray(thetaString);
//		System.out.println("asdasdasd"+theta.length);
//		for (int i = 0; i < theta.length; i++) {
//			int length=theta[i].length;
//			for (int j = 0; j < length; j++) {
//				System.out.println("topic: " + i + "word :" + j + " ="+ theta[i][j]);
//			}
//		}
		
		return theta;
	}
	
	public Map<Integer, String> loadVocabulary() {
		IO io=new IO();
		Convertion convertion=new Convertion();
		String vocabularyPath=path+"data/temp/vocabulary.txt";
		String vocabularyString=io.loadText(vocabularyPath);
//		System.out.println("vocabularyString: "+vocabularyString);
		
		Map<Integer,String> vocabularyMap=convertion.StringTovMap(vocabularyString);
		
		return vocabularyMap;
	}
	
	public Map<String, Object> loadp6() {
		IO io=new IO();
		Convertion convertion=new Convertion();
		String filepath=path+"data/temp/6.txt";
		String s6=io.loadText(filepath);
		Map<String, Object> m6=convertion.StringToMap(s6);
		return m6;
	}
}
