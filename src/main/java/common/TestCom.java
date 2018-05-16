package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCom {

	public static void main(String[] args) {
		String a = "北京师范大学-香港浸会大学联合国际学院";
		System.out.println(ComString.getCn(a));

		String org = "csu, fcks csu,csu,damcsu,csu ,csu";
		String REG = "\\b" + "csu" + "\\b";
		Matcher m = Pattern.compile(REG).matcher(org);
		if (m.find())
			org = m.replaceAll("");
		System.out.println(org);
	}

}
