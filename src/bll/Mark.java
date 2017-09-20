package bll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import kl.Entity;
import kl.calculateKL;

import util.CopyFile;

public class Mark {
	int topicCount = 3;
	private String t = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();
	private int num = t.indexOf(".metadata");
	private String path = t.substring(1, num).replace('/', '\\')
			+ "LDAv1.0\\WebContent\\WEB-INF\\";

	public int markAnswer(long identity, String finalAnswer) {
		// TO DO: 对获取到的作答（String类型）进行评分，并返回分数值（int类型）
		 System.out.println("finalAnswer: "+finalAnswer);
		
		LoadModel loadModel = new LoadModel();
		double[][] phi= loadModel.loadphi(); // 三分模型
		double[][] theta= loadModel.loadtheta();
		Map<Integer, String> vocabularyMap=loadModel.loadVocabulary();
		Vocabulary vocabulary = new Vocabulary();
		for (Map.Entry<Integer, String> entry : vocabularyMap.entrySet())
        {
//            System.out.println(entry);
            vocabulary.getId(entry.getValue(), true);
        }
		int[] document = new int[]{};
		try {
			/*	验证准确率代码
			document = Corpus.loadDocument( path+"data/sample/all/"+finalAnswer, vocabulary);
			*/
			System.out.println("Answer comes from: "+path+"data/answer/"+identity+".txt");
			document = Corpus.loadDocument( path+"data/answer/"+identity+".txt", vocabulary);
//			for (int i = 0; i < document.length; i++) {
//				System.out.println("mark doc: "+document[i]);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Map<String, Object> m4 = loadModel.loadp4(); // 四分模型
//		Map<String, Object> m5 = loadModel.loadp5(); // 五分模型
//		Map<String, Object> m6 = loadModel.loadp6(); // 六分模型
		// for (Map.Entry<String, Object> entry : m3.entrySet()){
		// System.out.println(entry);
		// }
		/*********************************** 计分 *************************************/
		double[] tp3 = LdaGibbsSampler.inference(phi, document);
//        for(int i = 0;i<tp3.length;i++){
//        	System.out.println(tp3[i]);
//        }
        double num1=0;
        double num2=0;
        double num3=0;
        double num4=0;
        
        ArrayList<Entity> entityList3 = new ArrayList<Entity>();
        ArrayList<Entity> entityList4 = new ArrayList<Entity>();
        ArrayList<Entity> entityList5 = new ArrayList<Entity>();
        ArrayList<Entity> entityList6 = new ArrayList<Entity>();
        ArrayList<Entity> entityList7 = new ArrayList<Entity>();
        for(int i=0;i<topicCount;i++){
        	double mark3 = theta[0][i]/80;
        	double mark4 = theta[1][i]/80;
        	double mark5 = theta[2][i]/80;
        	double mark6 = theta[3][i]/80;
        	Entity entity3 = new Entity();
        	Entity entity4 = new Entity();
        	Entity entity5 = new Entity();
        	Entity entity6 = new Entity();
        	Entity answer = new Entity();
        	answer.setWord(""+i);
        	answer.setpValue(tp3[i]);
        	entity3.setWord(""+i);
        	entity4.setWord(""+i);
        	entity5.setWord(""+i);
        	entity6.setWord(""+i);
        	entity3.setpValue(mark3);
        	entity4.setpValue(mark4);
        	entity5.setpValue(mark5);
        	entity6.setpValue(mark6);
        	entityList7.add(answer);
        	entityList3.add(entity3);
        	entityList4.add(entity4);
        	entityList5.add(entity5);
        	entityList6.add(entity6);
        }
        num1 = calculateKL.CalKL(entityList3,entityList7);
        num2 = calculateKL.CalKL(entityList4,entityList7);
        num3 = calculateKL.CalKL(entityList5,entityList7);
        num4 = calculateKL.CalKL(entityList6,entityList7);
        
        System.out.println(" mark3 :"+num1);
        System.out.println(" mark4 :"+num2);
        System.out.println(" mark5 :"+num3);
        System.out.println(" mark6: "+num4);
        double[] mark = new double[]{num1,num2,num3,num4};
        mark = bubbleSort(mark);
        
        Map<String, Double> topic3 = LdaUtil.translate(tp3, phi, vocabulary, 30);
//        LdaUtil.explain(topic3);
        int score=0;
        if(mark[0]==num1){
        	score=3;
        }else if(mark[0]==num2){
        	score=4;
		}else if(mark[0]==num3){
        	score=5;
		}else if(mark[0]==num4){
        	score=6;
		}
//        System.out.print("init score:"+)
//        System.out.println("actual score:"+score);
		/*********************************** 计分 *************************************/

		/******************** 用于将评分后的作答加入到评分样本当中 **********************/
		CopyFile copyFile = new CopyFile();
		File sFile = new File(path + "data/answer/" + identity + ".txt");
//		System.out.println(path + "data/sample/" + score + "/" + score + "_"
//				+ identity + ".txt");
		File tFile = new File(path + "data/sample/" + score + "/" + score + "_"
				+ identity + ".txt");
//		copyFile.fileChannelCopy(sFile, tFile);
		/****************************************************************************/
		return score;
	}
	 public static double[] bubbleSort(double[] arr){  
	        boolean flag = true;  
	        int n = arr.length;  
	        while(flag){  
	            flag = false;  
	            for(int j=0;j<n-1;j++){  
	                if(arr[j] >arr[j+1]){  
	                    //数据交换  
	                    double temp = arr[j];  
	                    arr[j] = arr[j+1];  
	                    arr[j+1] = temp;  
	                    //设置标志位  
	                    flag = true;  
	                }  
	            }  
	            n--;  
	        }  
	        return arr;  
	    }  
}
