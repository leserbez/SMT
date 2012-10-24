import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class TextProcessing {
	
	private File textF;
	private File textE;
	private URL urlF;
	private String name;
	int zeilenZähler = 0;
	int wortzähler = 0;
	String[] wort;
	ArrayList zeilen = new ArrayList();
	ArrayList wörter = new ArrayList();
	ArrayList text = new ArrayList();
	
	public TextProcessing (String textname, String e, String f){
		this.name = textname + "." + f + ".txt";
		
		urlF = getClass().getClassLoader().getResource(name);
		System.out.println(urlF);
		textF = new File("c:/Users/Lulu/train.de.txt");
		FileReader read = null;
		try {
			read = new FileReader(textF);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		readLine(read);
		for(int i=0; i<wörter.size(); i++){
				
			System.out.println(wörter.get(i));
		}
		for(int i=0; i<wort.length; i++){
			System.out.println(wort[i]);
		}
		System.out.println(text.get(0));
		
	}
	
	public void readLine(FileReader file){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeilen.add(zeilenZähler, zeile);	//einzelne Zeilen
				wort = zeile.split(" ");			//Array mit Wörtern einer Zeile
				
				for(int i=0; i<wort.length; i++){	//alle Wörter des Textes
				wörter.add(wort[i]);
				}
				text.add(wörter);
				wörter=new ArrayList();
				zeilenZähler++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read(FileReader file){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				
				
				
				zeilenZähler++;
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
