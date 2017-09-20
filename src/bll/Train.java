package bll;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import util.Convertion;
import util.IO;

public class Train {
	private static String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static int num=t.indexOf(".metadata");
	private static String path=t.substring(1,num).replace('/', '\\')+"LDAv1.0\\WebContent\\WEB-INF\\";
	
	
	public static void train3p() throws Exception {
		int topicCount=3;
    	/*
    	 * 3 points
    	 */
        // 1. Load corpus from disk
//    	Corpus corpus3 = Corpus.load("data/mini");
    	Corpus corpus3 = Corpus.load(path+"data/sample/all");
        // 2. Create a LDA sampler
        LdaGibbsSampler ldaGibbsSampler3 = new LdaGibbsSampler(corpus3.getDocument(), corpus3.getVocabularySize());
//        System.out.println(corpus3.getDocument());
//        System.out.println(corpus3.getVocabulary());
        // 3. Train it
        ldaGibbsSampler3.gibbs(topicCount);
        // 4. The phi matrix is a LDA model, you can use LdaUtil to explain it.
        
        double[][] phi3 = ldaGibbsSampler3.getPhi();
//        Map<String, Double>[] topicMap3 = LdaUtil.translate(phi3, corpus3.getVocabulary(), 10);
//        LdaUtil.explain(topicMap3);
        double[][] theta = ldaGibbsSampler3.getTheta();
        double[] thetaSum1 = new double[topicCount];
        for(int i=0;i<80;i++){
        	System.out.println("File: "+i);
        	for(int j=0;j<theta[i].length;j++){
//        		System.out.println("topic: "+j);
//        		System.out.println(theta[i][j]);
        		thetaSum1[j] = thetaSum1[j]+theta[i][j];
        	}
        }
        double[] thetaSum2 = new double[topicCount];
        for(int i=80;i<160;i++){
        	System.out.println("File: "+i);
        	for(int j=0;j<theta[i].length;j++){
//        		System.out.println("topic: "+j);
//        		System.out.println(theta[i][j]);
        		thetaSum2[j] = thetaSum2[j]+theta[i][j];
        	}
        }
        double[] thetaSum3 = new double[topicCount];
        for(int i=160;i<240;i++){
        	System.out.println("File: "+i);
        	for(int j=0;j<theta[i].length;j++){
//        		System.out.println("topic: "+j);
//        		System.out.println(theta[i][j]);
        		thetaSum3[j] = thetaSum3[j]+theta[i][j];
        	}
        }
        double[] thetaSum4 = new double[topicCount];
        for(int i=240;i<320;i++){
        	System.out.println("File: "+i);
        	for(int j=0;j<theta[i].length;j++){
//        		System.out.println("topic: "+j);
//        		System.out.println(theta[i][j]);
        		thetaSum4[j] = thetaSum4[j]+theta[i][j];
        	}
        }
        for(int i =0;i<topicCount;i++){
        	System.out.println("mark 3: " + i + "  " + thetaSum1[i]/80);
        	System.out.println("mark 4: " + i+ "  " + thetaSum2[i]/80);
        	System.out.println("mark 5: " + i+ "  " + thetaSum3[i]/80);
        	System.out.println("mark 6: " + i+ "  " + thetaSum4[i]/80);
        	System.out.println("  " );
        }
        double[][] thetas = new double[4][topicCount];
        for(int i=0;i<topicCount;i++){
        	thetas[0][i]=thetaSum1[i]/80;
        	thetas[1][i]=thetaSum2[i]/80;
        	thetas[2][i]=thetaSum3[i]/80;
        	thetas[3][i]=thetaSum4[i]/80;
        }
        
//        for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.println("test thetas:"+thetas[i][j]);
//			}
//		}
        
        Convertion convertion=new Convertion();
        String thetasString=convertion.ArrayToString(thetas);
        System.out.println("thetasString: "+thetasString);
        IO io=new IO();
        io.outputToText(thetasString, path+"data/temp/theta.txt");

        // 5. TODO:Predict. I'm not sure whether it works, it is not stable.
//        int[] document3 = Corpus.loadDocument(answerPath, corpus3.getVocabulary());
//        System.out.println("dc length: "+document3.length);
//        for (int i = 0; i < document3.length; i++) {
//			System.out.println("train doc: "+document3[i]);
//		}
//        double[] tp3 = LdaGibbsSampler.inference(phi3, document3);
//        for(int i = 0;i<tp3.length;i++){
//        	System.out.println(tp3[i]);
//        }
//        double num1=0;
//        double num2=0;
//        double num3=0;
//        double num4=0;
//        
//        for(int i=0;i<topicCount;i++){
//        	double mark3 = thetaSum1[i]/80;
//        	double mark4 = thetaSum2[i]/80;
//        	double mark5 = thetaSum3[i]/80;
//        	double mark6 = thetaSum4[i]/80;
//        	
//        	num1 += mark3*mark3-tp3[i]*tp3[i];
//        	num2 += mark4*mark4-tp3[i]*tp3[i];
//        	num3 += mark5*mark5-tp3[i]*tp3[i];
//        	num4 += mark6*mark6-tp3[i]*tp3[i];
//        }
//        
//        System.out.println(" mark3 :"+num1);
//        System.out.println(" mark4 :"+num2);
//        System.out.println(" mark5 :"+num3);
//        System.out.println(" mark6: "+num4);
        
        
        
        
//        Convertion convertion=new Convertion();
        String s3=convertion.ArrayToString(phi3);
        IO printout=new IO();
        printout.outputToText(s3, path+"data/temp/phi.txt");
//        System.out.println("String: "+s3);
//        
        int vocabularySize = corpus3.getVocabulary().size();
        Map<Integer, String> vocabularyMap=new HashMap<Integer, String>();
        for (int i = 0; i < vocabularySize; i++) {
			String id2word=corpus3.getVocabulary().getWord(i);
			vocabularyMap.put(i, id2word);
		}
        String vocabulary=convertion.MapToString(vocabularyMap);
        System.out.println("vocabularyMap: "+vocabulary);
        io.outputToText(vocabulary, path+"data/temp/vocabulary.txt");
//        Map<String, Object> m3=convertion.StringToMap(s3);
//        System.out.println("Map: ");
//        for (Map.Entry<String, Object> entry : m3.entrySet())
//        {
//            System.out.println(entry);
//        }
	}
    
}
