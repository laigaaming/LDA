package util;

import java.sql.*;
import java.util.*;

public class Convertion {
	private static final String SEP1 = "#";
	private static final String SEP2 = "|";
	
	public static Map getResultMap(ResultSet rs)
			throws SQLException {
		Map hm = new HashMap();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for (int i = 1; i <= count; i++) {
			String key = rsmd.getColumnLabel(i);
			String value = rs.getString(i);
			hm.put(key, value);
		}
		return hm;
	}
	
	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(" ");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :需要转换的Map
	 * @return String转换后的字符串
	 */
	public static String MapToString(Map<?, ?> map) {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof List<?>) {
				sb.append(key.toString() + SEP1 + listToString((List<String>) value));
				sb.append(SEP2);
			} else if (value instanceof Map<?, ?>) {
				sb.append(key.toString() + SEP1
						+ MapToString((Map<?, ?>) value));
				sb.append(SEP2);
			} else {
				sb.append(key.toString() + SEP1 + value.toString());
				sb.append(SEP2);
			}
		}
//		return "M" + EspUtils.EncodeBase64(sb.toString());
		return "M" + sb.toString();
	}

	/**
	 * String转换Map
	 * 
	 * @param mapText
	 *            :需要转换的字符串
	 * @param KeySeparator
	 *            :字符串中的分隔符每一个key与value中的分割
	 * @param ElementSeparator
	 *            :字符串中每个元素的分割
	 * @return Map<?,?>
	 */
	public static Map<String, Object> StringToMap(String mapText) {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);

//		mapText = EspUtils.DecodeBase64(mapText);

		Map<String, Object> map = new HashMap<String, Object>();
		String[] text = mapText.split("\\" + SEP2); // 转换为数组
		for (String str : text) {
			String[] keyText = str.split(SEP1); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			String key = keyText[0]; // key
			String value = keyText[1]; // value
			if (value.charAt(0) == 'M') {
				Map<?, ?> map1 = StringToMap(value);
				map.put(key, map1);
			} else if (value.charAt(0) == 'L') {
				List<?> list = StringToList(value);
				map.put(key, list);
			} else {
				map.put(key, value);
			}
		}
		return map;
	}
	
	
	public static Map<Integer,String> StringTovMap(String mapText) {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);

//		mapText = EspUtils.DecodeBase64(mapText);

		Map<Integer,String> map = new HashMap<Integer,String>();
		String[] text = mapText.split("\\" + SEP2); // 转换为数组
		for (String str : text) {
			String[] keyText = str.split(SEP1); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			Integer key = Integer.parseInt(keyText[0]); // key
			String value = keyText[1]; // value
			map.put(key, value);
			 
		}
//		System.out.println(map.size()+"sdsad");
		return map;
	}
	
	public static List<Object> StringToList(String listText) {
		if (listText == null || listText.equals("")) {
			return null;
		}
		listText = listText.substring(1);

//		listText = EspUtils.DecodeBase64(listText);

		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split(SEP1);
		for (String str : text) {
			if (str.charAt(0) == 'M') {
				Map<?, ?> map = StringToMap(str);
				list.add(map);
			} else if (str.charAt(0) == 'L') {
				List<?> lists = StringToList(str);
				list.add(lists);
			} else {
				list.add(str);
			}
		}
		return list;
	}
	
	public static double[][] StringToArray(String mapText) {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);
		
//		mapText = EspUtils.DecodeBase64(mapText);
//		System.out.println("phi text1"+mapText);
		String[] text = mapText.split("\\|"); // 转换为数组
//		System.out.println("phi text2"+text[0]);
		double[][] thetas = new double[text.length][];
		for (int i=0;i<text.length;i++) {
			String theta = text[i];
			
			String[] p = theta.split("\\#"); // 转换key与value的数组
			ArrayList<Double> pArrayList = new ArrayList<Double>();
			for(int j=0;j<p.length;j++){
				
				Double d = Double.parseDouble(p[j]);
				pArrayList.add(d);
			}
			double[] tempTheta = new double[pArrayList.size()];
			int k =0;
			Iterator<Double> iterator = pArrayList.iterator();
			while (iterator.hasNext()) {
				Double double1 = iterator.next();
				tempTheta[k] = double1;
				k++;
			}
			thetas[i] = tempTheta;
//			if (keyText.length < 1) {
//				continue;
//			}
//			String key = keyText[0]; // key
//			String value = keyText[1]; // value
//			if (value.charAt(0) == 'M') {
//				Map<?, ?> map1 = StringToMap(value);
//				map.put(key, map1);
//			} else if (value.charAt(0) == 'L') {
//				List<?> list = StringToList(value);
//				map.put(key, list);
//			} else {
//				map.put(key, value);
//			}
		}
		return thetas;
	}

	
	public static String ArrayToString(double[][] thetas) {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (double[] theta : thetas) {
//			if (doc == null) {
//				continue;
//			}
			for(double p:theta){
				sb.append(p + SEP1);
			}
			sb.append(SEP2);
//			Object key = obj;
//			Object value = map.get(key);
//			if (value instanceof List<?>) {
//				sb.append(key.toString() + SEP1 + listToString((List<String>) value));
//				sb.append(SEP2);
//			} else if (value instanceof Map<?, ?>) {
//				sb.append(key.toString() + SEP1
//						+ MapToString((Map<?, ?>) value));
//				sb.append(SEP2);
//			} else {
//				sb.append(key.toString() + SEP1 + value.toString());
//				sb.append(SEP2);
//			}
		}
//		return "M" + EspUtils.EncodeBase64(sb.toString());
		return "M" + sb.toString();
	}

}
