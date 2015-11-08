package mytest.maven;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import java.util.List;

public class Main {
	public static void main (String args[]) {
		String str = "すもももももももものうち、知ってる、来た、見る";
		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(str);
		for (Token token: tokens) {
			System.out.println(token.getSurfaceForm()
					+ "\t" + token.getAllFeatures() + "\t" + token.getReading());
		}
	}
}
