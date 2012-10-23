import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;


public class TextProcessing {
	
	private File textF;
	private File textE;
	private URL urlF;
	private String name;
	
	public TextProcessing (String textname, String e, String f){
		this.name = textname + "." + f + ".txt";
		
		urlF = getClass().getClassLoader().getResource(name);
		System.out.println(urlF);
		textF = new File(urlF.getPath());
		FileReader read = null;
		try {
			read = new FileReader(textF);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		read(read);
		
	}
	
	public void read(FileReader file){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				System.out.println("Gelesene Zeile: " + zeile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getTextF() {
		return textF;
	}
	public void setTextF(File textF) {
		this.textF = textF;
	}
	public File getTextE() {
		return textE;
	}
	public void setTextE(File textE) {
		this.textE = textE;
	}

}
