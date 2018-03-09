import com.weidu.common.string.ComString;

public class Test {
	
	public static void main(String[] args) {
		String[] strArr = new String[] { "www.micmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、",
				"不要啊", "やめて", "韩佳人", "???" };
		for(String str: strArr) {
			System.out.println(str);
			System.out.println(ComString.hasCnChar(str));
		}
	}
	
}
