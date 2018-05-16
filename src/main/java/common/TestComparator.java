package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestComparator {
	
	public static void main(String[] args) {
		List<String> A = new ArrayList<>();
		A.addAll(Arrays.asList("abc","cdf","a","dafdaf"));
		Collections.sort(A, new StringLengthComparator(1));
		System.out.println(A);
		
		SortedMap<String, Integer> B = new TreeMap<>(new StringLengthComparator(1));
		B.put("abc", 1);
		B.put("cfd", 2);
		//B.addAll(Arrays.asList("abc","cdf","a","dafdaf"));
		System.out.println(B);
		
		SortedMap<String, Integer> C = new TreeMap<>(new EnglishKeyWordsComparator(1));
		C.put("abc cdf", 1);
		C.put("cfd", 2);
		C.put("cfd adfd df", 3);
		C.put("cfd df adfd", 3);
		C.put("cfd adfd df", 3);
		System.out.println(C.containsKey("cdf abc"));
		//B.addAll(Arrays.asList("abc","cdf","a","dafdaf"));
		System.out.println(C);
		
	}
		

}
