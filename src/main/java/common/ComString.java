package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ComString {

	public static String EnCnSpliter = "#&#&#";// 中英文分隔符
	
	public static String ComSpliter = ";|；";

	/********************** 获取类 *****************************/
	/**
	 * 分离并提取数据，当同时输入多中语言时，按输入顺序提取其中一个不为空的内容
	 * 
	 * @param term
	 * @param lan
	 * @return
	 */
	public static List<String> split(String term, String spliter, int...lan) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isBlank(term))
			return list;
		String[] names;
		if (spliter == null){
			names = term.split(ComString.EnCnSpliter);
		}else if(spliter == "default"){
			names = term.split(ComString.ComSpliter + "|" +ComString.EnCnSpliter);
		}else {
			names = term.split(spliter + "|"+ ComString.EnCnSpliter);
		}
		if(lan.length == 0){
			lan = new int[]{1, 2};
		}
		for (String name : names) {
			for (int i : lan) {
				String realname = ComString.getLetter(name, i);
				if (StringUtils.isNotBlank(realname)) {
					list.add(realname);
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取中文、英文、数字列表
	 * 
	 * @param term
	 * @param lan
	 *            1、2、 3
	 * @return list
	 */
	public static List<String> getLetters(String term, int lan) {
		List<String> list = new ArrayList<>();
		if (StringUtils.isNotBlank(term)) {
			String regex = "([\\u4e00-\\u9fa5]*)(\\p{Alpha}*)(\\d*)";
			Matcher m = Pattern.compile(regex).matcher(term);
			while (m.find())
				if (m.group(lan).length() > 0)
					list.add(m.group(lan).toLowerCase());
		}
		return list;
	}

	/**
	 * 获取中文1、英文2、数字3 字符串
	 * 
	 * @param term
	 * @param lan
	 * @return String
	 */
	public static String getLetter(String term, int lan) {
		String join = "";// 默认合并间隔
		if (lan == 2)
			join = " ";// 英文合并间隔
		return StringUtils.join(ComString.getLetters(term, lan), join);
	}

	/**
	 * 获取英文分词
	 * 
	 * @param str
	 * @param num
	 * @return
	 */
	public static List<String> getEnNGram(String str, int num) {
		if (num < 1)
			return null;
		String[] terms = str.replaceAll("_|-", "").replaceAll("\\p{P}+", " ").split("\\p{Z}+");
		List<String> termList = new ArrayList<String>();
		for (String term : terms)
			if (ComString.hasEnChar(term))
				termList.add(term);
		List<String> gramList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int len, size = termList.size();
		for (int i = 0; i < size - num; i++) {
			sb.setLength(0);
			len = i + num - 1;
			sb.append(termList.get(i));
			if (len >= size - 1)
				len = size - 1;
			for (int j = i + 1; j <= len; j++)
				sb.append(termList.get(j));
			gramList.add(sb.toString());
		}
		return gramList;
	}

	/**
	 * 获取中文分词
	 * 
	 * @param str
	 * @param num
	 * @return
	 */
	public static List<String> getHanNGram(String str, int num) {
		if (num < 1)
			return null;
		List<String> gramList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		char[] chars = str.toCharArray();
		int letterNum = 0, len;
		char c;
		for (int i = 0; i < chars.length; i++) {
			c = chars[i];
			if (isLetter(c)) {
				if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
					if (letterNum != 0) {
						gramList.add(sb.toString());
						letterNum = 0;
						sb.setLength(0);
					}
					sb.append(c);
					len = i + num - 1;
					if (len >= chars.length - 1)
						len = chars.length - 1;
					for (int j = i + 1; j <= len; j++) {
						c = chars[j];
						if (Character.UnicodeScript.of(c) != Character.UnicodeScript.HAN)
							break;
						sb.append(c);
					}
					if (sb.length() == num)
						gramList.add(sb.toString());
					sb.setLength(0);
				} else {
					letterNum++;
					sb.append(c);
				}
			}
		}
		if (letterNum != 0)
			gramList.add(sb.toString());
		return gramList;
	}

	/********************** 判断类 *****************************/

	/**
	 * 判断是否包含英文
	 * 
	 * @param text
	 * @return
	 */
	public static boolean hasEnChar(String text) {
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(text);
		return m.matches();
	}

	/**
	 * 判断是否包含中文
	 * 
	 * @param text
	 * @return
	 */
	public static boolean hasCnChar(String text) {
		int count = text.codePointCount(0, text.length());
		for (int i = 0; i < count; i++) {
			int codePoint = text.codePointAt(i);
			if (ComString.isChineseChar(codePoint))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否是全英文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEnStr(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ((ch < 0x61 && ch > 0x5a) || ch < 0x41 || ch > 0x7a) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否是 中 或 英 或 数字
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		if (Character.isAlphabetic(c) || Character.isDigit(c)
				|| Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是中文字符
	 * 
	 * @param codePoint
	 * @return
	 */
	public static boolean isChineseChar(int codePoint) {
		Character.UnicodeScript sc = Character.UnicodeScript.of(codePoint);
		if (sc == Character.UnicodeScript.HAN)
			return true;
		return false;
	}

	/********************** 比较类 *****************************/
	/**
	 * 比较字符串相似度
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static float cosine(String str1, String str2) {
		float sim = 0.0F;
		if (ComString.hasCnChar(str1) && ComString.hasCnChar(str2)) {
			str1 = ComString.getLetter(str1, 1);
			str2 = ComString.getLetter(str2, 1);
			sim = ComString.cosine_sub(ComString.getHanNGram(str1, 1), ComString.getHanNGram(str2, 1));
		} else if (ComString.hasEnChar(str1) && ComString.hasEnChar(str2)) {
			sim = ComString.cosine_sub(ComString.getLetters(str1, 2), ComString.getLetters(str2, 2));
		}
		if (Float.isNaN(sim))
			sim = 0.0f;
		return sim;
	}

	public static float cosine_sub(List<String> list1, List<String> list2) {
		Map<String, Integer> termIndexMap = new HashMap<String, Integer>();
		int index = 0;
		for (String term : list1) {
			if (!termIndexMap.containsKey(term)) {
				termIndexMap.put(term, index);
				index++;
			}
		}
		for (String term : list2) {
			if (!termIndexMap.containsKey(term)) {
				termIndexMap.put(term, index);
				index++;
			}
		}
		int[] vec1 = new int[index], vec2 = new int[index];
		for (int i = 0; i < index; i++) {
			vec1[i] = 0;
			vec2[i] = 0;
		}
		for (String term : list1)
			vec1[termIndexMap.get(term)]++;
		for (String term : list2)
			vec2[termIndexMap.get(term)]++;
		return (float) cosine_sub(vec1, vec2);
	}

	private static double cosine_sub(int[] vec1, int[] vec2) {
		double product = 0f, square1 = 0f, square2 = 0f;
		for (int i = 0; i < vec2.length; i++) {
			product += vec1[i] * vec2[i];
			square1 += vec1[i] * vec1[i];
			square2 += vec2[i] * vec2[i];
		}
		return product / (Math.sqrt(square1) * Math.sqrt(square2));
	}

	/**
	 * 去除两个集合中的相似元素 ：注意set1 与 set2 输入位置的差别
	 * 
	 * @param set1
	 * @param set2
	 * @param minScore
	 *            最低相似度
	 * @return result = set1 - set2; 输入集合是安全的
	 */
	public static Set<?> removeSim(Set<?> set1, Set<?> set2, float minScore) {
		if (set1 == null) {
			return new HashSet<>();
		}
		Set<?> result = new HashSet<>(set1);
		if (set2 == null) {
			return result;
		}
		result.removeAll(set2);// 移除相同元素
		HashSet<Object> removeSet = new HashSet<>();// 移除相似元素
		for (Object a : set1) {
			for (Object b : set2) {
				float score = ComString.cosine(a.toString(), b.toString());
				if (score > minScore) {
					removeSet.add(a);
				}
			}
		}
		result.removeAll(removeSet);
		return result;
	}

	/**
	 * 自身去相似
	 * 
	 * @param set
	 * @param minScore
	 * @return
	 */
	public static Set<?> removeSim(Set<?> set, float minScore) {
		if (set == null) {
			return null;
		}
		Set<Object> result = new HashSet<>();
		for (Object a : set) {
			float score = 0f;
			for (Object b : result) {
				float temp = ComString.cosine(a.toString(), b.toString());
				score = temp > score ? temp : score;
			}
			if (score < minScore) {
				result.add(a);
			}
		}
		return result;
	}

	/**
	 * 交换集合相似元素：set2 -> set1
	 * 
	 * @param set1
	 *            a1 b1 c1 d e f
	 * @param set2
	 *            a2 b2 c2 d g j k
	 * @param minScore
	 * @return a2 b2 c2 d e f
	 */
	public static Set<?> crossSim(Set<?> set1, Set<?> set2, float minScore) {
		if (set1 == null) {
			return null;
		}
		HashSet<Object> result = new HashSet<>(set1);
		if (set2 == null) {
			return result;
		}
		for (Object a : set1) {
			Object b = ComString.mostSim(a, set2, minScore);
			if (b != null) {
				result.remove(a);
				result.add(b);
			}
		}
		return result;
	}

	/**
	 * 查找set中是否有str的相似元素
	 * 
	 * @param str
	 * @param set
	 * @param minScore
	 * @return 查找set中与str最相似的元素，仅当最大相似度大于minScore时，返回set中相应的元素
	 */
	public static Object mostSim(Object str, Set<?> set, float minScore) {
		Object result = null;
		float maxScore = 0f;
		if (StringUtils.isNotBlank(str.toString()) && set != null) {
			for (Object a : set) {
				float score = ComString.cosine(a.toString(), str.toString());
				if (score > maxScore) {
					maxScore = score;
					result = a;
				}
			}
		}
		if (maxScore > minScore) {
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 取两个集合最相似的元素 ：注意set1 与 set2 输入位置的差别
	 * 
	 * @param set1
	 * @param set2
	 * @return [element, float] 返回set1中最大相似度的element 和 相似度
	 */
	public static Object[] simLap(Set<?> set1, Set<?> set2) {
		Object[] result = new Object[] { "", 0f };
		float maxScore = 0.0f;
		if (set1 != null || set2 != null) {
			for (Object a : set1) {
				for (Object b : set2) {
					float score = ComString.cosine(a.toString(), b.toString());
					if (score > maxScore) {
						maxScore = score;
						result[0] = a;
						result[1] = maxScore;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 是否存在交集？
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean hasOverLap(Set<?> set1, Set<?> set2) {
		if (set1 == null || set2 == null)
			return false;
		else {
			List<Object> list = new ArrayList<>(set1);
			list.retainAll(set2);
			if (!list.isEmpty())
				return true;
			else
				return false;
		}
	}

	/**
	 * 取交集
	 * 
	 * @param list1
	 * @param list2
	 * @return 返回重复元素
	 */
	public static Set<?> overLap(Set<?> set1, Set<?> set2) {
		if (set1 == null || set2 == null)
			return null;
		else {
			List<Object> list = new ArrayList<>(set1);
			list.retainAll(set2);
			if (!list.isEmpty())
				return new HashSet<>(list);
			else
				return null;
		}
	}

	/********************** 转换类 *****************************/
	/**
	 * List<String>转List<Integer>
	 * 
	 * @param strList
	 * @return
	 */
	public static List<Integer> string2int(List<String> strList) {
		List<Integer> intList = new ArrayList<>();
		for (String str : strList)
			intList.add(Integer.parseInt(str));
		return intList;
	}

}
