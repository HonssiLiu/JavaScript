package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regulation {

	public String xml = "<H1>Chapter 1 - 介绍正则表达式</H1>";
	public String regex1 = "<.*>";
	public String regex2 = "<.*?>";
	public String regex3 = "<\\w+?>";
	public void xml() {
		Matcher m = Pattern.compile(regex2).matcher(xml);
		while (m.find())
			System.out.println(m.group(4));
	}

	public String http = "http://www.runoob.com:80/html/html-tutorial.html";
	public String regex4 = "(\\w+)://([^/:]+)(:\\d*)?([^# ]*)";
	public void http() {
		Matcher m = Pattern.compile(regex4).matcher(http);
		while (m.find())
			for (int i = 1; i <= 4; i++)
				System.out.println(m.group(i));
	}
	
	public String str = "Windows 95、Windows 98 和 Windows NT";
	public String regex5 = "(?<=Windows )(95|98|NT)";
	public void cleanDoctile() {
		Matcher m = Pattern.compile(regex5).matcher(str);
//		while(m.find()) {
//			System.out.println(m.group());
//		}
		if(m.find()) {
			String re = m.replaceAll("2000");
			System.out.println(re);
		}
	}

	public static void main(String[] args) {
		Regulation regulation = new Regulation();
		regulation.cleanDoctile();
	}

}
