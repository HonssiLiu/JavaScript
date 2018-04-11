package common;

import java.io.IOException;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import io.FileCommIO;
import io.FileIter;
import io.FileIterStr;

public class RemoveSimInFile {

	public static void main(String[] args) {
		try {
			FileIter<String> texts = new FileIterStr("F:\\data\\named-entity-recognition\\prep.txt");
			SortedSet<String> set = new TreeSet<>(new Comparator<String>() {
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
			while (texts.hasNext()) {
				set.add(texts.next());
			}
			FileCommIO.OutputIterator(set.iterator(), "F:\\data\\named-entity-recognition\\prep2.txt", false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
