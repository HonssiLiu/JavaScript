package stringcluster;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class WordCluster {
	/**
	 * 统计每个词的总出现次数，返回出现次数大于n次的词汇构成最终的属性词典
	 * 
	 * @param strDir
	 *            处理好的newsgroup文件目录的绝对路径
	 * @param wordMap
	 *            记录出现的每个词构成的属性词典
	 * @return newWordMap 返回出现次数大于n次的词汇构成最终的属性词典
	 * @throws IOException
	 */
	public SortedMap<String, Integer> count(String strDir, Map<String, Integer> wordMap, int n) throws IOException {
		File sampleFile = new File(strDir);
		File[] sample = sampleFile.listFiles();
		String word;
		for (int i = 0; i < sample.length; i++) {
			if (!sample[i].isDirectory()) {
				FileReader samReader = new FileReader(sample[i]);
				BufferedReader samBR = new BufferedReader(samReader);
				while ((word = samBR.readLine()) != null) {
					if (!word.isEmpty())
						wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
				}
				samBR.close();
			} else {
				count(sample[i].getCanonicalPath(), wordMap, n);
			}
		}

		SortedMap<String, Integer> newWordMap = new TreeMap<String, Integer>();
		for (Entry<String, Integer> entry : wordMap.entrySet()) {
			if (entry.getValue() > n)
				newWordMap.put(entry.getKey(), entry.getValue());
		}
		return newWordMap;
	}
}
