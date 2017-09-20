/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2015/1/29 17:22</create-date>
 *
 * <copyright file="TestCorpus.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package bll;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hankcs
 */
public class TestCorpus {
	private String t = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();

	private int num = t.indexOf(".metadata");
	private String path = t.substring(1, num).replace('/', '\\')
			+ "LDAv1.0\\WebContent\\WEB-INF\\";

	public static void main(String[] args) throws Exception {
		TestCorpus test = new TestCorpus();
		test.testAll();
	}

	public void testAll() throws Exception {
		System.out.println(path);
		String answerPath = path + "data/sample/all";
		File folder = new File(answerPath);
		Map<String, File> files = new TreeMap<String, File>();
		for (File file : folder.listFiles()) {
			files.put(file.getName(), file);
		}
		int i = 1;
		int count = 0;
		Mark mark = new Mark();
		for (Map.Entry<String, File> file : files.entrySet()) {
			int result = mark.markAnswer(1, file.getKey());

			if (result == Integer.parseInt(file.getKey().substring(0, 1))
					|| result + 1 == Integer.parseInt(file.getKey().substring(
							0, 1))
					|| result - 1 == Integer.parseInt(file.getKey().substring(
							0, 1))) {
				count++;
			}
			System.out.print("init score:" + file.getKey().substring(0, 1));
			System.out.println("actual score:" + result);
			if (i == 80 || i == 160 || i == 240 || i == 320) {
				System.out.println("Success count :" + count);
				count = 0;
			}
			i++;
		}
		// train4p(answerPath);
		// train5p(answerPath);
		// train6p(answerPath);
	}

	/*
	 * lijiaming - 20160120
	 */
	public static String[] train(String answerPath) throws Exception {
		String[] result = new String[3];
		result[0] = answerPath.substring(14);
		// 1. Load corpus from disk
		// Corpus corpus3 = Corpus.load("dic");
		Corpus corpus3 = Corpus.load("data/answer/3");
		// 2. Create a LDA sampler
		LdaGibbsSampler ldaGibbsSampler3 = new LdaGibbsSampler(
				corpus3.getDocument(), corpus3.getVocabularySize());
		// 3. Train it
		ldaGibbsSampler3.gibbs(3);
		// 4. The phi matrix is a LDA model, you can use LdaUtil to explain it.
		double[][] phi3 = ldaGibbsSampler3.getPhi();
		Map<String, Double>[] topicMap3 = LdaUtil.translate(phi3,
				corpus3.getVocabulary(), 5);
		// LdaUtil.explain(topicMap3);
		double[][] theta = ldaGibbsSampler3.getTheta();
		double[] thetaSum1 = new double[3];
		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < theta[i].length; j++) {
				// System.out.println("topic: "+j);
				// System.out.println(theta[i][j]);
				thetaSum1[j] = thetaSum1[j] + theta[i][j];
			}
		}
		double[] thetaSum2 = new double[3];
		for (int i = 80; i < 160; i++) {
			for (int j = 0; j < theta[i].length; j++) {
				// System.out.println("topic: "+j);
				// System.out.println(theta[i][j]);
				thetaSum2[j] = thetaSum2[j] + theta[i][j];
			}
		}
		double[] thetaSum3 = new double[3];
		for (int i = 160; i < 240; i++) {
			for (int j = 0; j < theta[i].length; j++) {
				// System.out.println("topic: "+j);
				// System.out.println(theta[i][j]);
				thetaSum3[j] = thetaSum3[j] + theta[i][j];
			}
		}
		double[] thetaSum4 = new double[3];
		for (int i = 240; i < 320; i++) {
			for (int j = 0; j < theta[i].length; j++) {
				// System.out.println("topic: "+j);
				// System.out.println(theta[i][j]);
				thetaSum4[j] = thetaSum4[j] + theta[i][j];
			}
		}
		// for(int i =0;i<3;i++){
		// System.out.println("mark 3: " + i + "  " + thetaSum1[i]/80);
		// System.out.println("  " );
		// }
		// for(int i =0;i<3;i++){
		// System.out.println("mark 3: " + i+ "  " + thetaSum2[i]/80);
		// System.out.println("  " );
		// }
		// for(int i =0;i<3;i++){
		// System.out.println("mark 5: " + i+ "  " + thetaSum3[i]/80);
		// System.out.println("  " );
		// }
		// for(int i =0;i<3;i++){
		// System.out.println("mark 6: " + i+ "  " + thetaSum4[i]/80);
		// System.out.println("  " );
		// }
		//
		// 5. TODO:Predict.
		int[] document3 = Corpus.loadDocument(answerPath,
				corpus3.getVocabulary());
		double[] tp3 = LdaGibbsSampler.inference(phi3, document3);

		Map<String, Double> topic = LdaUtil.translate(tp3, phi3,
				corpus3.getVocabulary(), 5);
		// LdaUtil.explain(topic);

		// for(int i = 0;i<tp3.length;i++){
		// System.out.println(tp3[i]);
		// }
		double num1 = 0;
		double num2 = 0;
		double num3 = 0;
		double num4 = 0;

		double[] temp1 = new double[3];
		double[] temp2 = new double[3];
		double[] temp3 = new double[3];
		double[] temp4 = new double[3];

		for (int i = 0; i < 3; i++) {
			double mark3 = thetaSum1[i] / 80;
			double mark4 = thetaSum2[i] / 80;
			double mark5 = thetaSum3[i] / 80;
			double mark6 = thetaSum4[i] / 80;

			temp1[i] = Math.sqrt(Math.abs(mark3 * mark3 - tp3[i] * tp3[i]));
			temp2[i] = Math.sqrt(Math.abs(mark4 * mark4 - tp3[i] * tp3[i]));
			temp3[i] = Math.sqrt(Math.abs(mark5 * mark5 - tp3[i] * tp3[i]));
			temp4[i] = Math.sqrt(Math.abs(mark6 * mark6 - tp3[i] * tp3[i]));
		}
		temp1 = bubbleSort(temp1);
		temp2 = bubbleSort(temp2);
		temp3 = bubbleSort(temp3);
		temp4 = bubbleSort(temp4);

		for (int i = 0; i < 3; i++) {
			num1 += temp1[i];
			num2 += temp2[i];
			num3 += temp3[i];
			num4 += temp4[i];
		}
		double[] distance = new double[] { num1, num2, num3, num4 };
		distance = bubbleSort(distance);
		// System.out.println(" mark3 :"+num1);
		// System.out.println(" mark4 :"+num2);
		// System.out.println(" mark5 :"+num3);
		// System.out.println(" mark6: "+num4);
		if (distance[0] == num1) {
			// System.out.println("mark : 3");
			result[1] = 3 + "";
		} else if (distance[0] == num2) {
			// System.out.println("mark : 3");
			result[1] = 4 + "";
		} else if (distance[0] == num3) {
			// System.out.println("mark : 5");
			result[1] = 5 + "";
		} else if (distance[0] == num4) {
			// System.out.println("mark : 6");
			result[1] = 6 + "";
		}
		if (Integer.parseInt(answerPath.substring(14, 15)) == Integer
				.parseInt(result[1])
				|| Integer.parseInt(answerPath.substring(14, 15)) - 1 == Integer
						.parseInt(result[1])
				|| Integer.parseInt(answerPath.substring(14, 15)) + 1 == Integer
						.parseInt(result[1])) {
			result[2] = "OK";
		} else {
			result[2] = "Not OK";
		}
		// VocabularyDao dao = new VocabularyDao();
		// ArrayList<VocabularyVO> keyWords = dao.getVocabularyListByMark(0);
		// Iterator<VocabularyVO> it = keyWords.iterator();
		// while(it.hasNext()){
		// VocabularyVO vo = (VocabularyVO) it.next();
		// for (String keyWord : topic3.keySet())
		// {
		// if(!keyWord.equalsIgnoreCase(vo.getWord())){
		// System.out.println("Key word :" + keyWord + " is missing. ");
		// }
		// }
		// }
		return result;
	}

	public static void train4p(String answerPath) throws Exception {
		/*
		 * 3 points
		 */
		// 1. Load corpus from disk
		// Corpus corpus = Corpus.load("data/mini");
		Corpus corpus4 = Corpus.load("data/answer/3");
		// 4. Create a LDA sampler
		LdaGibbsSampler ldaGibbsSampler4 = new LdaGibbsSampler(
				corpus4.getDocument(), corpus4.getVocabularySize());
		// 3. Train it
		ldaGibbsSampler4.gibbs(10);
		// 3. The phi matrix is a LDA model, you can use LdaUtil to explain it.
		double[][] phi4 = ldaGibbsSampler4.getPhi();
		Map<String, Double>[] topicMap4 = LdaUtil.translate(phi4,
				corpus4.getVocabulary(), 10);
		// LdaUtil.explain(topicMap4);
		// 5. TODO:Predict. I'm not sure whether it works, it is not stable.
		// int[] document = Corpus.loadDocument("data/mini/军事_510.txt",
		// corpus.getVocabulary());
		int[] document4 = Corpus.loadDocument(answerPath,
				corpus4.getVocabulary());
		double[] tp4 = LdaGibbsSampler.inference(phi4, document4);
		for (int i = 0; i < tp4.length; i++) {
			System.out.println(tp4[i]);
		}
		Map<String, Double> topic4 = LdaUtil.translate(tp4, phi4,
				corpus4.getVocabulary(), 30);
		// LdaUtil.explain(topic4);
	}

	public static void train5p(String answerPath) throws Exception {
		/*
		 * 5 points
		 */
		// 1. Load corpus from disk
		// Corpus corpus = Corpus.load("data/mini");
		Corpus corpus5 = Corpus.load("data/answer/5");
		// 4. Create a LDA sampler
		LdaGibbsSampler ldaGibbsSampler5 = new LdaGibbsSampler(
				corpus5.getDocument(), corpus5.getVocabularySize());
		// 3. Train it
		ldaGibbsSampler5.gibbs(10);
		// 3. The phi matrix is a LDA model, you can use LdaUtil to explain it.
		double[][] phi5 = ldaGibbsSampler5.getPhi();
		Map<String, Double>[] topicMap5 = LdaUtil.translate(phi5,
				corpus5.getVocabulary(), 10);
		// LdaUtil.explain(topicMap5);
		// 5. TODO:Predict. I'm not sure whether it works, it is not stable.
		// int[] document = Corpus.loadDocument("data/mini/军事_510.txt",
		// corpus.getVocabulary());
		int[] document5 = Corpus.loadDocument(answerPath,
				corpus5.getVocabulary());
		double[] tp5 = LdaGibbsSampler.inference(phi5, document5);
		for (int i = 0; i < tp5.length; i++) {
			System.out.println(tp5[i]);
		}
		Map<String, Double> topic5 = LdaUtil.translate(tp5, phi5,
				corpus5.getVocabulary(), 30);
		// LdaUtil.explain(topic5);
	}

	public static void train6p(String answerPath) throws Exception {
		/*
		 * 6 points
		 */
		// 1. Load corpus from disk
		// Corpus corpus = Corpus.load("data/mini");
		Corpus corpus6 = Corpus.load("data/answer/6");
		// 4. Create a LDA sampler
		LdaGibbsSampler ldaGibbsSampler6 = new LdaGibbsSampler(
				corpus6.getDocument(), corpus6.getVocabularySize());
		// 3. Train it
		ldaGibbsSampler6.gibbs(10);
		// 3. The phi matrix is a LDA model, you can use LdaUtil to explain it.
		double[][] phi6 = ldaGibbsSampler6.getPhi();
		Map<String, Double>[] topicMap6 = LdaUtil.translate(phi6,
				corpus6.getVocabulary(), 10);
		// LdaUtil.explain(topicMap6);
		// 5. TODO:Predict. I'm not sure whether it works, it is not stable.
		// int[] document = Corpus.loadDocument("data/mini/军事_610.txt",
		// corpus.getVocabulary());
		int[] document6 = Corpus.loadDocument(answerPath,
				corpus6.getVocabulary());
		double[] tp6 = LdaGibbsSampler.inference(phi6, document6);
		for (int i = 0; i < tp6.length; i++) {
			System.out.println(tp6[i]);
		}
		Map<String, Double> topic6 = LdaUtil.translate(tp6, phi6,
				corpus6.getVocabulary(), 30);
		// LdaUtil.explain(topic6);
	}

	public static double[] bubbleSort(double[] arr) {
		boolean flag = true;
		int n = arr.length;
		while (flag) {
			flag = false;
			for (int j = 0; j < n - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// 数据交换
					double temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					// 设置标志位
					flag = true;
				}
			}
			n--;
		}
		return arr;
	}
}
