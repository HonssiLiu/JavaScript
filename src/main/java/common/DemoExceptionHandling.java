package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*可查异常： CheckedException, 如FileNotFoundException，可查异常即必须进行处理的异常*/

/*运行时异常：RuntimeException，不是必须进行try catch的异常，
 * 除数不能为0异常:ArithmeticException 
 * 下标越界异常:ArrayIndexOutOfBoundsException 
 * 空指针异常:NullPointerException 等
 * 即便不进行try catch，也不会有编译错误*/

/*错误Error，指的是系统级别的异常，通常是内存用光了，与运行时异常一样，错误也是不要求强制捕捉的*/

public class DemoExceptionHandling {
	public static void main(String[] args) {
		test4();
		// method1();
	}

	public static void test() {
		File f = new File("d:/LOL.exe");
		try {
			System.out.println("试图打开 d:/LOL.exe");
			new FileInputStream(f);
			System.out.println("成功打开");
		} catch (FileNotFoundException e) {
			System.out.println("d:/LOL.exe不存在");
			e.printStackTrace();
		} finally {
			System.out.println("无论文件是否存在， 都会执行的代码");
		}
	}

	// FileNotFoundException是Exception的子类，使用Exception也可以catch住FileNotFoundException
	public static void test2() {
		File f = new File("d:/LOL.exe");
		try {
			System.out.println("试图打开 d:/LOL.exe");
			new FileInputStream(f);
			System.out.println("成功打开");
		} catch (Exception e) {
			System.out.println("d:/LOL.exe不存在");
			e.printStackTrace();
		}
	}

	/*
	 * Throwable是类，Exception和Error都继承了该类 所以在捕捉的时候，也可以使用Throwable进行捕捉
	 * 异常分Error和Exception Excetion里又分运行时异常和可查异常。
	 */
	public static void test22() {
		File f = new File("d:/LOL.exe");
		try {
			System.out.println("试图打开 d:/LOL.exe");
			new FileInputStream(f);
			System.out.println("成功打开");
		} catch (Throwable e) {
			System.out.println("d:/LOL.exe不存在");
			e.printStackTrace();
		}
	}

	// 可能有多种异常:文件未找到异常，解析异常
	public static void test3() {
		File f = new File("d:/LOL.exe");
		try {
			System.out.println("试图打开 d:/LOL.exe");
			new FileInputStream(f);
			System.out.println("成功打开");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse("2016-06-03");
		} catch (FileNotFoundException e) {
			System.out.println("d:/LOL.exe不存在");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("日期格式解析错误");
			e.printStackTrace();
		}
	}

	/*
	 * 考虑如下情况： 主方法调用method1 method1调用method2 method2中打开文件
	 * 
	 * method2中需要进行异常处理 但是method2不打算处理，而是把这个异常通过throws抛出去 那么method1就会接到该异常。
	 * 处理办法也是两种，要么是try catch处理掉，要么也是抛出去。 method1选择本地try catch住 一旦try
	 * catch住了，就相当于把这个异常消化掉了，主方法在调用method1的时候，就不需要进行异常处理了
	 */
	public static void method1() {
		try {
			method2();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void method2() throws FileNotFoundException {
		File f = new File("d:/LOL.exe");
		System.out.println("试图打开 d:/LOL.exe");
		new FileInputStream(f);
		System.out.println("成功打开");
	}

	/* 自定义异常 */
	class MyException extends Exception {
		public MyException() {
		}

		public MyException(String msg) {
			super(msg);
		}
	}
	public void testMyException() throws MyException {
		throw new MyException("发现了自定义异常！");
	}
	public static void test4() {
		try {
			new DemoExceptionHandling().testMyException();
		} catch (MyException e) {
			System.out.println("异常的具体原因:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
