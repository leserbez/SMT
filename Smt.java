import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class Smt {
	
	static String textname;
	static String a;
	static String b;
	static String option;
	File text;
	
	public static void main(String[] args) throws IOException {
		
		textname ="ausgabe";//args[0];
		a ="en";//args[1];
		b ="de";//args[2];
		option ="lookupSentence";//args[3];
		
		TextProcessing process = new TextProcessing(textname, a, b, option);
	
	}

}
