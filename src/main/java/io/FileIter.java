package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class FileIter<T> implements Iterator<T> {

	protected List<T> records = new ArrayList<>();

	protected int maxNum = 1000;

	protected int num = 0;

	protected BufferedReader br = null;

	public FileIter(String filePath) throws IOException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		readLines();
	}

	public abstract void readLines() throws IOException;

	@Override
	public boolean hasNext() {
		if (num > 0) {
			return true;
		} else {
			num = 0;
			try {
				readLines();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("FileIter.readLines出错！");
				return false;
			}
			if (num > 0) {
				return true;
			}
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public T next() {
		num--;
		return records.remove(0);
	}

}
