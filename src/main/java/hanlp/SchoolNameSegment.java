package hanlp;

import java.net.URL;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import io.FileUtil;

public class SchoolNameSegment {

	public void test() {
		URL url = this.getClass().getClassLoader().getResource("schoolNames.txt");
		List<String> schoolNames = FileUtil.InputData(url.getPath());
		Segment segment = HanLP.newSegment().enableCustomDictionary(false);
		for (String sentence : schoolNames) {
			List<Term> termList = segment.seg(sentence);
			System.out.println(termList);
		}
	}

	public static void main(String[] args) {
		SchoolNameSegment cc = new SchoolNameSegment();
		cc.test();
	}
}
