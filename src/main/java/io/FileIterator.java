package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FileIterator implements Iterator<String> {

	private List<String> records = new ArrayList<>();

	private int maxNum = 1000;

	private int num = 0;

	private BufferedReader br = null;

	public FileIterator(String filePath) {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			readLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readLines() throws IOException {
		if (br != null) {
			String line;
			while (num < maxNum && (line = br.readLine()) != null) {
				if (StringUtils.isNotBlank(line)) {
					records.add(line);
					num++;
				}
			}
		}
	}

	public boolean hasNext() {
		if (num > 0) {
			return true;
		} else {
			num = 0;
			try {
				readLines();
			} catch (IOException e) {
			}
			if (num > 0) {
				return true;
			}
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		return false;
	}

	public String next() {
		num--;
		return records.remove(0);
	}

}
