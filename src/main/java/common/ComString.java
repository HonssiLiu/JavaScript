package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComString {

	public static String EnCnSpliter = "#&#&#";// 中英文分隔符

	/**
	 * 替换控制符(制表符、换页符、制表符等等)
	 * 
	 * @param str
	 * @param repalcement
	 * @return
	 */
	public static String replaceFormat(String str, String repalcement) {
		return str.replaceAll("[\\f\\n\\r\\t\\v]", repalcement);
	}

	/**
	 * 替换控制符(制表符、换页符、制表符等等)、标点符号、特殊字符。
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceSymbol(String str, String repalcement) {
		return str.replaceAll("[\\f\\n\\r\\t\\v\\pC\\pS\\pP]", repalcement);
	}

	/**
	 * 获取中文\英文,未匹配上时返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String getLetter(String str) {
		return regMatch("[\\u4E00-\\u9FA5A-Za-z]+", str);
	}

	/**
	 * 获取中文,未匹配上时返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String getCn(String str) {
		return regMatch("[\\u4e00-\\u9fa5]+", str);
	}

	/**
	 * 获取英文,未匹配上时返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String getEn(String str) {
		return regMatch("[A-Za-z]+", str);
	}

	public static String getEnWithSpace(String str) {
		StringBuffer cn = new StringBuffer();
		Matcher m = Pattern.compile("[A-Za-z]+").matcher(str);
		while (m.find()) {
			cn.append(m.group() + " ");
		}
		return cn.toString().trim();
	}

	/**
	 * 获取数字,未匹配上时返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String getNum(String str) {
		return regMatch("\\d+", str);
	}

	/**
	 * 通用匹配，未匹配上时返回""
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static String regMatch(String regex, String str) {
		StringBuffer cn = new StringBuffer();
		Matcher m = Pattern.compile(regex).matcher(str);
		while (m.find()) {
			cn.append(m.group());
		}
		return cn.toString();
	}

	/**
	 * 判断str是否以set中的任意元素为结尾
	 * 
	 * @param str
	 * @param set
	 * @return
	 */
	public static boolean ends(String str, Collection<String> set) {
		for (String obj : set) {
			if (str.endsWith(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否包含？
	 * 
	 * @param set
	 * @param str
	 * @return
	 */
	public static boolean contains(Collection<?> set, Object str) {
		for (Object obj : set) {
			if (str.equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public static void addContains(List<String> list, String s) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(s)) {
				list.set(i, s);
				return;
			} else if (s.contains(list.get(i))) {
				return;
			}
		}
		list.add(s);
	}

	/**
	 * 去除重复元素，并保留顺序
	 * 
	 * @param list
	 * @return
	 */
	public static void removeDup(List<?> list) {
		List<Object> newList = new ArrayList<>();
		for (int i = list.size() - 1; i >= 0; i--) {
			if (newList.contains(list.get(i))) {
				list.remove(i);
				// System.out.println(list.remove(i));
			} else {
				newList.add(list.get(i));
			}
		}
	}
	
	public static int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		// 注意长度，字符有0长度
		//dp[i][j] 代表最小操作数（步骤），从 word1[0..i-1]转化为 word2[0..j-1].
		int[][] dp = new int[m + 1][n + 1];
		// dp[i][0]=i表示，字符长度为i变为长度0，delete i个子符，需要i步
		// dp[0][i]=i表示，字符长度为0变为长度i，insert i个子符，需要i步
		for (int i = 0; i <= m; i++)
			dp[i][0] = i;
		for (int i = 0; i <= n; i++)
			dp[0][i] = i;
		//注意<=
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++) {
				//如果前i-1
				if (word1.charAt(i - 1) == word2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = 1 + 
					Math.min(dp[i - 1][j - 1], 
							Math.min(dp[i][j - 1], dp[i - 1][j]));
			}

		return dp[m][n];
	}

}
