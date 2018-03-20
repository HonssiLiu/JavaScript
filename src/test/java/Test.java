import com.weidu.common.string.ComString;

public class Test {

	public static void main(String[] args) {
		String[] strArr = new String[] { "www.mi cmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、",
				"不要啊", "やめて", "韩佳人", "???" };
		for (String str : strArr) {
			System.out.format("原字符：%s, 修正后：%s\n", str, ComString.cleanCn(str));
		}
	}

}
