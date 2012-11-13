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
		
		textname = args[0];
		a = args[1];
		b = args[2];
		option = args[3];
		
		TextProcessing process = new TextProcessing(textname, a, b, option);
	
	}

}
