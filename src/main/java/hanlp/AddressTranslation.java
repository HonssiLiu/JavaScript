package hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.weidu.comutil.file.FileIterStr;
import com.weidu.comutil.file.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressTranslation {

	public static void main(String[] args) throws IOException {
		new AddressTranslation().translation();
	}

	public void translation() throws IOException {
		Map<String, List<String>> trans = new HashMap<>();
		FileIterStr fileList = new FileIterStr(this.getClass().getClassLoader().getResourceAsStream("address_cn.txt"));
		while (fileList.hasNext()) {
			String cn = fileList.next();
			List<Pinyin> pinyinList = HanLP.convertToPinyinList(cn);
			String en1 = "";
			String en2 = "";
			for (Pinyin py : pinyinList) {
				en1 = en1 + py.getPinyinWithoutTone();
				en2 = en2 + " " + py.getPinyinWithoutTone();
			}
			List<String> ens = new ArrayList<>();
			ens.add(en1.trim());
			ens.add(en2.trim());
			trans.put(cn, ens);
		}
		FileUtil.map2file(trans, "sources/address_en.txt");
	}

}
