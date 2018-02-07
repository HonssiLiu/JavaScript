package ikanalyzer;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class Demo {
	public static void main(String[] args) throws IOException {
		String text = "中南大学铁道学院,420000,湖南长沙，*&@#$school of";
		IKSegmenter ik = new IKSegmenter(new StringReader(text), true);
		Lexeme lexeme = null;
		while ((lexeme = ik.next()) != null) {
			System.out.println(lexeme.getLexemeText());
		}
	}
}
