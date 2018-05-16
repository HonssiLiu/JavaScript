package common;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<Object> {

	private int direction = -1;// 小于0代表降序，大于等于0代表升序

	public StringLengthComparator() {
	}

	public StringLengthComparator(int direction) {
		if (direction < 0) {
			direction = -1;
		} else {
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Integer l1 = obj1.toString().length();
		Integer l2 = obj2.toString().length();
		if (l1 > l2) {
			return direction;
		} else if (l1 < l2) {
			return -direction;
		} else {
			return direction * obj1.toString().compareTo(obj2.toString());
		}
	}

}
