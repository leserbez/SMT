import java.io.File;


public class Smt {
	
	static String textname;
	static String f;
	static String e;
	File text;
	
	public static void main(String[] args) {
		
		textname = "file";//args[0];
		e = "a";//args[1];
		f = "a";//args[2];
		
		TextProcessing process = new TextProcessing(textname, e, f);
	
	}

}
