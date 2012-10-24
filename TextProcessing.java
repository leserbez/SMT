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
	int zeilenZ�hler = 0;
	String[] wort;
	ArrayList zeilen = new ArrayList();
	ArrayList w�rter = new ArrayList();
	ArrayList textFrench = new ArrayList();
	ArrayList textEnglish = new ArrayList();
	
	
	public TextProcessing (String textname, String e, String f){
		this.name = textname + "." + f + ".txt";
		
		urlF = getClass().getClassLoader().getResource(name);
		System.out.println(urlF);
		textF = new File("c:/Users/Lulu/train.de.txt");
		textE = new File("c:/Users/Lulu/train.en.txt");
		FileReader readF = null;
		FileReader readE = null;
		try {
			readF = new FileReader(textF);
			readE = new FileReader(textE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		readLine(readF, textFrench);
		zeilenZ�hler = 0;						// wieder auf 0 setzen
		readLine(readE, textEnglish);
		
		for(int i=0; i<textFrench.size(); i++){
		System.out.println(textFrench.get(i));
		System.out.println(textEnglish.get(i));
		}
		
	}
	
	public void readLine(FileReader file, ArrayList list){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeilen.add(zeilenZ�hler, zeile);	//einzelne Zeilen
				wort = zeile.split(" ");			//Array mit W�rtern einer Zeile
				
				for(int i=0; i<wort.length; i++){	//alle W�rter des Textes
				w�rter.add(wort[i]);
				}
				
				list.add(w�rter);					//ArrayList mit Arrays, die W�rter pro Zeile beinhalten
				w�rter=new ArrayList();				//leeren
				zeilenZ�hler++;
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
