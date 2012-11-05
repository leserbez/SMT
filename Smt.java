import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class Smt {
	
	static String textname;
	static String f;
	static String e;
	static String option;
	File text;
	
	public static void main(String[] args) throws IOException {
		
		textname = "file";//args[0];
		e = "a";//args[1];
		f = "a";//args[2];
		option ="train";//args[0];
		
		TextProcessing process = new TextProcessing(textname, e, f, option);
	
	}

}
