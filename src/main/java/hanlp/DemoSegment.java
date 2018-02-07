package hanlp;

import java.util.List;
import java.util.Map;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

public class DemoSegment {

	// HanLP.segment其实是对StandardTokenizer.segment的包装。
	public static void hanlp() {
		System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
	}

	public static void standardTokenizer() {
		List<Term> termList = StandardTokenizer.segment("商品和服务");
		System.out.println(termList);
	}

	// NLP分词NLPTokenizer会执行全部命名实体识别和词性标注。
	public static void nLPTokenizer() {
		List<Term> termList = NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程");
		System.out.println(termList);
	}

	// N最短路分词器NShortSegment比最短路分词器慢，但是效果稍微好一些，对命名实体识别能力更强。
	// 一般场景下最短路分词的精度已经足够，而且速度比N最短路分词器快几倍，请酌情选择。
	public static void shortSegment() {
		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true)
				.enableOrganizationRecognize(true);
		Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true)
				.enableOrganizationRecognize(true);
		String[] testCase = new String[] { "中南大学地球物理与信息工程学院，湖南长沙，420000" };
		for (String sentence : testCase) {
			System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
		}
	}

	// CRF对新词有很好的识别能力，但是开销较大。
	public static void crf() {
		Segment segment = new CRFSegment();
		segment.enablePartOfSpeechTagging(true);
		List<Term> termList = segment.seg("你看过穆赫兰道吗");
		System.out.println(termList);
		for (Term term : termList) {
			if (term.nature == null) {
				System.out.println("识别到新词：" + term.word);
			}
		}
	}

	// 适用于“高吞吐量”“精度一般”的场合
	public static void speedTokenizer() {
		String text = "江西鄱阳湖干枯，中国最大淡水湖变成大草原";
		System.out.println(SpeedTokenizer.segment(text));
		long start = System.currentTimeMillis();
		int pressure = 1000000;
		for (int i = 0; i < pressure; ++i) {
			SpeedTokenizer.segment(text);
		}
		double costTime = (System.currentTimeMillis() - start) / (double) 1000;
		System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);
	}

	// 用户自定义词典
	public static void customDictionary() {
		// 动态增加
		CustomDictionary.add("攻城狮");
		// 强行插入
		CustomDictionary.insert("白富美", "nz 1024");
		// 删除词语（注释掉试试）
		// CustomDictionary.remove("攻城狮");
		System.out.println(CustomDictionary.add("单身狗", "nz 1024 n 1"));
		System.out.println(CustomDictionary.get("单身狗"));

		String text = "攻城狮逆袭单身狗，迎娶白富美，走上人生巅峰"; // 怎么可能噗哈哈！

		// AhoCorasickDoubleArrayTrie自动机分词
		final char[] charArray = text.toCharArray();
		CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
			@Override
			public void hit(int begin, int end, CoreDictionary.Attribute value) {
				System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
			}
		});
		// trie树分词
		BaseSearcher searcher = CustomDictionary.getSearcher(text);
		Map.Entry entry;
		while ((entry = searcher.next()) != null) {
			System.out.println(entry);
		}

		// 标准分词
		System.out.println(HanLP.segment(text));
	}

	// 目前分词器基本上都默认开启了中国人名识别，比如HanLP.segment()接口中使用的分词器等等，用户不必手动开启；代码只是为了强调。
	public static void name() {
		String[] testCase = new String[] { "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。", "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四", "张浩和胡健康复员回家了",
				"王总和小丽结婚了", "编剧邵钧林和稽道青说", "这里有关天培的有关事迹", "龚学平等领导,邓颖超生前", };
		Segment segment = HanLP.newSegment().enableNameRecognize(true);
		for (String sentence : testCase) {
			List<Term> termList = segment.seg(sentence);
			System.out.println(termList);
		}
	}

	// 目前标准分词器都默认关闭了地名识别，用户需要手动开启；这是因为消耗性能，其实多数地名都收录在核心词典和用户自定义词典中。
	public static void place() {
		String[] testCase = new String[] { "武胜县新学乡政府大楼门前锣鼓喧天", "蓝翔给宁夏固原市彭阳县红河镇黑牛沟村捐赠了挖掘机", "中南大学计算机科学学院" };
		// Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
		Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
		for (String sentence : testCase) {
			List<Term> termList = segment.seg(sentence);
			System.out.println(termList);
		}
	}

	// 目前分词器默认关闭了机构名识别，用户需要手动开启；这是因为消耗性能，其实常用机构名都收录在核心词典和用户自定义词典中。
	public static void org() {
		String[] testCase = new String[] { "河南农业大学", "中国地质大学（武汉）", "中南大学计算机科学学院", "西安电子科技大学"};
		Segment segment = HanLP.newSegment().enableOrganizationRecognize(false).enablePlaceRecognize(false);
		for (String sentence : testCase) {
			List<Term> termList = segment.seg(sentence);
			System.out.println(termList);
		}
	}

	public static void mytest() {
		// 动态增加
		String text = "中南大学地球物理学院";
		Segment segment = HanLP.newSegment().enableCustomDictionary(true);
		List<Term> termList = segment.seg(text);
		System.out.println(termList);
	}

	public static void main(String[] args) {
		org();
	}

}
