package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoDate {
	public static void main(String[] args) {
		// 当前时间
		Date now = new Date();
		System.out.println("当前时间:");
		// 打印当前时间
		System.out.println(now);
		System.out.println("当前时间:" + now.toString());
		// getTime() 得到一个long型的整数
		// 这个整数代表 1970.1.1 08:00:00:000，每经历一毫秒，增加1
		System.out.println("当前时间getTime()返回的值是：" + now.getTime());
		// 通过System.currentTimeMillis()获取当前日期的毫秒数,与new Date().getTime()效果相同
		System.out.println("System.currentTimeMillis() \t返回值: " + System.currentTimeMillis());
		// 从1970年1月1日 早上8点0分0秒(中国所有计算机时间的原点) 开始经历的毫秒数
		Date d2 = new Date(5000);
		System.out.println("从1970年1月1日 早上8点0分0秒 开始经历了5秒的时间");
		System.out.println(d2);

		// 日期格式化
		// y 代表年
		// M 代表月
		// d 代表日
		// H 代表24进制的小时
		// h 代表12进制的小时
		// m 代表分钟
		// s 代表秒
		// S 代表毫秒
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date d = new Date();
		String str = sdf.format(d);
		System.out.println("当前时间通过 yyyy-MM-dd HH:mm:ss SSS 格式化后的输出: " + str);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		String str1 = sdf1.format(d1);
		System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: " + str1);

		// 字符串转日期
		// 模式（yyyy/MM/dd HH:mm:ss）需要和字符串格式保持一致，如果不一样就会抛出解析异常ParseException
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		str = "2016/1/5 12:12:12";
		try {
			Date d21 = sdf.parse(str);
			System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s", str, d21.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
