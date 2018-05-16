package common;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

//按英文单词个数+长度降序排列, 如果单词个数和总长度都相同,就对比单个词,如果单词相同则输入对象相等,  
//abc dfg ksde == dfg ksde, abc
public class EnglishKeyWordsComparator implements Comparator<String> {

	private int direction = -1;// 小于0代表降序，大于等于0代表升序

	public EnglishKeyWordsComparator() {
	}

	public EnglishKeyWordsComparator(int direction) {
		if (direction < 0) {
			direction = -1;
		} else {
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(String obj1, String obj2) {
		Integer l1 = obj1.split(" ").length;
		Integer l2 = obj2.split(" ").length;
		if (l1 > l2) {
			return direction;
		} else if (l1 < l2) {
			return -direction;
		} else {
			Integer ll1 = obj1.length();
			Integer ll2 = obj2.length();
			if (ll1 > ll2)
				return direction;
			else if (ll1 < ll2) {
				return -direction;
			} else {
				Set<String> strs1 = new HashSet<>(Arrays.asList(obj1.split(" ")));
				Set<String> strs2 = new HashSet<>(Arrays.asList(obj2.split(" ")));
				if (strs1.size() == strs2.size()) {
					strs1.removeAll(strs2);
					if (strs1.size() == 0) {
						return 0;
					}
				}
				return direction * obj1.compareTo(obj2);
			}
		}
	}

}
