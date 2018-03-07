package stanfordcorenlp;

import edu.stanford.nlp.simple.*;

public class SimpleCoreNLPDemo2 {
	public static void main(String[] args) {
		// Create a document. No computation is done yet.
		Sentence sen = new Sentence("College of Environmental Science and Engineering, Hunan University, Changsha 410082, China");
		System.out.println(sen);
		for(int i = 0; i<sen.length();i++) {
			System.out.format("lemma:%-10s\t pos:%-10s\t ner:%-10s\n", sen.lemma(i), sen.posTag(i), sen.nerTag(i));
		}
	}
}