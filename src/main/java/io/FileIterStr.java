package io;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class FileIterStr extends FileIter<String> {

	public FileIterStr(String filePath) throws IOException {
		super(filePath);
	}

	public void readLines() throws IOException {
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

}
