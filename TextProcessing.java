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
	int zeilenZ�hler = 0;
	int wortZ�hler=0;
	String[] wort;
	List zeilen = new ArrayList();
	List<String> w�rter = new ArrayList<String>();
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	
	List<String> w�rterListeF = new ArrayList<String>();
	List<String> w�rterListeE = new ArrayList<String>();
	
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
		zeilenZ�hler = 0;						// wieder auf 0 setzen
		readLine(readE, textEnglish);
		
//		for(int i=0; i<textFrench.size(); i++){
//		System.out.println(textFrench.get(i));
//		System.out.println(textEnglish.get(i));
//		}
		//System.out.println(textFrench.get(0).get(0));
		
		sortWords(textFrench, w�rterListeF);
		sortWords(textEnglish, w�rterListeE);
//		for(int a=0; a<w�rterListeF.size(); a++){
//			System.out.println(w�rterListeF.get(a));
//		}
		
		listWords(textFrench, einfachListeF);
		listWords(textEnglish, einfachListeE);
		for(int i=0; i<einfachListeF.size(); i++){
			System.out.println(einfachListeF.get(i));
		}
		Dictionary dict = new Dictionary(einfachListeF, einfachListeE, textFrench, textEnglish);
//		System.out.println(dict.f);
//		System.out.println(einfachListeE.size());
		
		//System.out.println(w�rterListeF.get(0).get(0));
		LengthModel length = new LengthModel(textFrench, textEnglish);
//		System.out.println(length.maxSize);
//		System.out.println(length.minSize);
//		System.out.println(length.factoring(5, 5));
		
		BigramModel bigram = new BigramModel(w�rterListeF);
		System.out.println(bigram.train("es", "ist"));
		
	}
	
	public void readLine(FileReader file, List textFrench2){
		try {
			BufferedReader in = new BufferedReader(file);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeilen.add(zeilenZ�hler, zeile);	//einzelne Zeilen
				wort = zeile.split(" ");			//Array mit W�rtern einer Zeile
				
				for(int i=0; i<wort.length; i++){	//alle W�rter des Textes
				w�rter.add(wort[i]);
				}
				
				textFrench2.add(w�rter);					//ArrayList mit Arrays, die W�rter pro Zeile beinhalten
				w�rter=new ArrayList();				//leeren
				zeilenZ�hler++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listWords(List<ArrayList<String>> text, List<String> w�rter){
		for(int i=0; i<text.size(); i++){
			for(int a=0; a<text.get(i).size(); a++){
				if(!(w�rter.contains(text.get(i).get(a)))){
					w�rter.add(text.get(i).get(a));
				}
			}
		}
	}
	
	public void sortWords(List<ArrayList<String>> text, List<String> w�rter){
		w�rter.add(0, text.get(0).get(0));
		int worte=0;
		
		for(int a=0; a<text.size(); a++){
			//System.out.println("a="+a);
			for(int b=0; b<text.get(a).size(); b++){
				//System.out.println("b="+ b);
					w�rter.add(worte, text.get(a).get(b));
					worte++;
				}
			}
		System.out.println(w�rter.size());
		//removeEqualWords(w�rter);
		}
	
//	public List<String> removeEqualWords(List<String> w�rter){
//		for(int a=1; a<w�rter.size(); a++){
//			for(int b=0; b<a; b++){
//				if(w�rter.get(b).equals(w�rter.get(a))){
//					System.out.println(b);
//					w�rter.remove(a);
//				}
//				System.out.println("b="+b);
//			}
//			System.out.println("a="+a);
//			System.out.println(w�rter.size());
//		}
//		return w�rter;
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
