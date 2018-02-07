package common;

import java.util.List;

public class ComMath {

	public static int sum(List<Integer> list) {
		int s = 0;
		for (int a : list) {
			s = s + a;
		}
		return s;
	}

	public static float mean(List<Integer> list) {
		float s;
		s = (float) sum(list);
		s = s / list.size();
		return s;
	}
	
	public static float sigmod(float x){
		return (float) (1.0f/(1.0f+Math.exp(-x)));
	}
}
