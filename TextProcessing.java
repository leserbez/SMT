import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TextProcessing {
	
	private File textF;
	private File textE;
	private URL urlF;
	private String name;
	int zeilenZähler = 0;
	int wortZähler=0;
	String[] wort;
	List zeilen = new ArrayList();
	List<String> wörter = new ArrayList<String>();
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	
	List<String> wörterListeF = new ArrayList<String>();
	List<String> wörterListeE = new ArrayList<String>();
	
	List<String> einfachListeF = new ArrayList<String>();
	List<String> einfachListeE = new ArrayList<String>();
	
	
	public TextProcessing (String textname, String e, String f){
		this.name = textname + "." + f + ".txt";
		
		urlF = getClass().getClassLoader().getResource(name);
		System.out.println(urlF);
		textF = new File("c:/Users/Lulu/beispiel.de.txt");
		textE = new File("c:/Users/Lulu/beispiel.en.txt");
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
		zeilenZähler = 0;						// wieder auf 0 setzen
		readLine(readE, textEnglish);
		
//		for(int i=0; i<textFrench.size(); i++){
//		System.out.println(textFrench.get(i));
//		System.out.println(textEnglish.get(i));
//		}
		//System.out.println(textFrench.get(0).get(0));
		
		sortWords(textFrench, wörterListeF);
		sortWords(textEnglish, wörterListeE);
//		for(int a=0; a<wörterListeF.size(); a++){
//			System.out.println(wörterListeF.get(a));
//		}
		
		listWords(textFrench, einfachListeF);
		listWords(textEnglish, einfachListeE);
		for(int i=0; i<einfachListeF.size(); i++){
			System.out.println(einfachListeF.get(i));
		}
		Dictionary dict = new Dictionary(einfachListeF, einfachListeE, textFrench, textEnglish);
//		System.out.println(dict.f);
//		System.out.println(einfachListeE.size());
		
		//System.out.println(wörterListeF.get(0).get(0));
		LengthModel length = new LengthModel(textFrench, textEnglish);
//		System.out.println(length.maxSize);
//		System.out.println(length.minSize);
//		System.out.println(length.factoring(5, 5));
		
		BigramModel bigram = new BigramModel(wörterListeF);
		System.out.println(bigram.train("es", "ist"));
		
	}
	
	public void readLine(FileReader file, List textFrench2){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeilen.add(zeilenZähler, zeile);	//einzelne Zeilen
				wort = zeile.split(" ");			//Array mit Wörtern einer Zeile
				
				for(int i=0; i<wort.length; i++){	//alle Wörter des Textes
				wörter.add(wort[i]);
				}
				
				textFrench2.add(wörter);					//ArrayList mit Arrays, die Wörter pro Zeile beinhalten
				wörter=new ArrayList();				//leeren
				zeilenZähler++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listWords(List<ArrayList<String>> text, List<String> wörter){
		for(int i=0; i<text.size(); i++){
			for(int a=0; a<text.get(i).size(); a++){
				if(!(wörter.contains(text.get(i).get(a)))){
					wörter.add(text.get(i).get(a));
				}
			}
		}
	}
	
	public void sortWords(List<ArrayList<String>> text, List<String> wörter){
		wörter.add(0, text.get(0).get(0));
		int worte=0;
		
		for(int a=0; a<text.size(); a++){
			//System.out.println("a="+a);
			for(int b=0; b<text.get(a).size(); b++){
				//System.out.println("b="+ b);
					wörter.add(worte, text.get(a).get(b));
					worte++;
				}
			}
		System.out.println(wörter.size());
		//removeEqualWords(wörter);
		}
	
//	public List<String> removeEqualWords(List<String> wörter){
//		for(int a=1; a<wörter.size(); a++){
//			for(int b=0; b<a; b++){
//				if(wörter.get(b).equals(wörter.get(a))){
//					System.out.println(b);
//					wörter.remove(a);
//				}
//				System.out.println("b="+b);
//			}
//			System.out.println("a="+a);
//			System.out.println(wörter.size());
//		}
//		return wörter;
//		
//		int a=1;
//		int b=0;
//		while(a>b){
//			
//		}
//	}
		
	
	
	
	
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
