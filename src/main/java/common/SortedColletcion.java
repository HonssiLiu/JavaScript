package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortedColletcion {

	public static void main(String[] args) {
		
		SortedSet<String> set = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String obj1, String obj2) {
				obj1 = obj1.trim();
				obj2 = obj2.trim();
				Integer l1 = obj1.length();
				Integer l2 = obj2.length();
				if (obj1.equals(obj2)) {
					return 0;
				} else if (l1.intValue() >= l2.intValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		set.add("abc");
		set.add("中南大");
		set.add("a");
		set.add("b");
		set.add("a");
		System.out.println(set);
		
		
		SortedMap<String, Integer> SCHOOL = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String obj1, String obj2) {
				obj1 = obj1.trim();
				obj2 = obj2.trim();
				Integer l1 = obj1.length();
				Integer l2 = obj2.length();
				if (obj1.equals(obj2)) {
					return 0;
				} else if (l1.intValue() >= l2.intValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		SCHOOL.put("abc", 3);
		SCHOOL.put("cdf", 2);
		SCHOOL.put("cdfa", 3);
		SCHOOL.put("abc", 2);
		SCHOOL.put("", 4);
		SCHOOL.put("b", 1);
		SCHOOL.put("a", 1);
		for (Entry<String, Integer> entry : SCHOOL.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println(SCHOOL.get("abc"));
		
		List<String> orgList = new ArrayList<>();
		orgList.add("abc");
		orgList.add("中南大");
		orgList.add("a");
		orgList.add("b");
		orgList.add("a");
		
		Collections.sort(orgList, new Comparator<String>() {
			@Override
			public int compare(String obj1, String obj2) {
				obj1 = obj1.trim();
				obj2 = obj2.trim();
				Integer l1 = obj1.length();
				Integer l2 = obj2.length();
				if (obj1.equals(obj2)) {
					return 0;
				} else if (l1.intValue() >= l2.intValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		System.out.println(orgList);
		
	}

}
