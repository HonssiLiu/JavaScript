package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class SystemIO {
	public static void main(String[] args) {
		scannerInt();
	}

	public static void sysin() {
		// 控制台输入
		try (InputStream is = System.in;) {
			while (true) {
				// 敲入a,然后敲回车可以看到
				// 97 13 10
				// 97是a的ASCII码
				// 13 10分别对应回车换行
				int i = is.read();
				System.out.println(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//使用Scanner就可以逐行读取字符串
	public static void scanner(){
		Scanner s = new Scanner(System.in);
        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
	}
	
	//使用Scanner从控制台读取整数
	public static void scannerInt(){
		Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        System.out.println("第一个整数："+a);
        int b = s.nextInt();
        System.out.println("第二个整数："+b);
        s.close();
	}
	
	public static void formatio() {
		String name = "盖伦";
		int kill = 8;
		String title = "超神";

		// 使用格式化输出
		// %s表示字符串，%d表示数字,%n表示换行
		String sentenceFormat = "%s 在进行了连续 %d 次击杀后，获得了 %s 的称号%n";
		// 使用printf格式化输出
		System.out.printf(sentenceFormat, name, kill, title);
		// 使用format格式化输出
		System.out.format(sentenceFormat, name, kill, title);
		/*
		 * （1）在DOS和Windows中，每行结尾是 “\r\n”； （2）Linux系统里，每行结尾只有 “\n”；
		 * （3）Mac系统里，每行结尾是只有 "\r"。 使用%n，就可以做到平台无关的换行
		 */

		int year = 2020;
		// 总长度，左对齐，补0，千位分隔符，小数点位数，本地化表达

		// 直接打印数字
		System.out.format("%d%n", year);
		// 总长度是8,默认右对齐
		System.out.format("%8d%n", year);
		// 总长度是8,左对齐
		System.out.format("%-8d%n", year);
		// 总长度是8,不够补0
		System.out.format("%08d%n", year);
		// 千位分隔符
		System.out.format("%,8d%n", year * 10000);

		// 小数点位数
		System.out.format("%.2f%n", Math.PI);

		// 不同国家的千位分隔符
		System.out.format(Locale.FRANCE, "%,.2f%n", Math.PI * 10000);
		System.out.format(Locale.US, "%,.2f%n", Math.PI * 10000);
		System.out.format(Locale.UK, "%,.2f%n", Math.PI * 10000);

		// "一个\\t制表符长度是8"，使用\t制表符可以达到对齐的效果
		System.out.println("abc\tdef");
		System.out.println("ab\tdef");
		System.out.println("a\tdef");
		// \n换行符
		System.out.println("abc\ndef");
	}

}
