package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferStream {
	public static void main(String[] args) throws IOException {
		File f = new File("d:/test/lol.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();

		// 创建文件字符流
		// 缓存流必须建立在一个存在的流的基础上
		// 把打开的流放在try里面，当try结束时，会自动关闭流

		// 以缓存的方式高效写数据
		try (// 创建文件字符流
				FileWriter fw = new FileWriter(f);
				// 缓存流必须建立在一个存在的流的基础上
				PrintWriter pw = new PrintWriter(fw);) {
			pw.println("garen kill teemo");
			pw.println("teemo revive after 1 minutes");
			pw.println("teemo try to garen, but killed again");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 以缓存的方式高效读数据
		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr);// 缓存流
		) {
			while (true) {
				// 一次读一行
				String line = br.readLine();
				if (null == line)
					break;
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 有的时候，需要立即把数据写入到硬盘，而不是等缓存满了才写出去。 这时候就需要用到flush
		try (FileWriter fr = new FileWriter(f); PrintWriter pw = new PrintWriter(fr);) {
			pw.println("2garen kill teemo");
			pw.flush();// 强制把缓存中的数据写入硬盘，无论缓存是否已满
			pw.println("2teemo revive after 1 minutes");
			pw.flush();
			pw.println("2teemo try to garen, but killed again");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
