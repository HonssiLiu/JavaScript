package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoReg {
	
	//正则表达式匹配两个指定字符串中间的内容
	public static Map<Integer,String> getSubUtil(String soap, String rgex) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			map.put(m.start(1), m.group(1));
		}
		return map;
	}

	//返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static String specialCharacterHandling(String xml, String rgex, String target, String replacement) {
		//String rgex = "<value>(.*?)</value>";
		if (target.length()!=replacement.length()){
			System.out.println("DealStrSub: 替换前后长度必须相同！XML没有改变...");
			return xml;
		}
		Map<Integer, String> matches = getSubUtil(xml, rgex);
		if (matches != null) {
			for (Entry<Integer, String> entry : matches.entrySet()) {
				String sub = entry.getValue();				
				if (sub.contains(target)) {
					int fromIndex = entry.getKey();
					int toIndex = entry.getKey()+sub.length();
					String subNew = sub.replaceAll(target,replacement);
					xml = xml.substring(0, fromIndex) + subNew + xml.substring(toIndex);
				}
			}
		}
		return xml;
	}

	public static void main(String[] args) {
		//String str = "<params><queries><field><name>_all</name><value>C/M</value></field></queries><filters><field><name>aduitStatus</name><value>1</value></field><field><name>belong</name><value>1</value></field><field><name>docType</name><value>1</value><value>3</value><value>14</value><value>6</value><value>21</value><value>22</value></field></filters><school>14</school><page>1</page><size>20</size></params>";
		String str = "<value>a/aa/a</value>";
		System.out.println(str);
		String rgex = "<value>(.*?)</value>";
		//System.out.println(getSubUtil(str, rgex));
		//System.out.println(getSubUtilSimple(str, rgex));
		System.out.println(specialCharacterHandling(str,rgex,"/","_"));
	}
}
