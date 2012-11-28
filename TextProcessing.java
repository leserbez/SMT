import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TextProcessing {
	
	private File textF;
	private File textE;
	private File ausgabe;
	private URL urlF;
	private String nameF;
	private String nameE;
	int zeilenZähler = 0;
	int wortZähler=0;
	String[] wort;
	List zeilen = new ArrayList();
	List<String> wörter = new ArrayList<String>();
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> ausgabeList = new ArrayList<ArrayList<String>>();
	List<ArrayList<Integer>> kodeTextF = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> kodeTextE = new ArrayList<ArrayList<Integer>>();
	
	List<String> wörterListeF = new ArrayList<String>();
	List<String> wörterListeE = new ArrayList<String>();
	
	List<String> textListeF = new ArrayList<String>();
	List<String> textListeE = new ArrayList<String>();
	List<Integer> kodeF = new ArrayList<Integer>();
	List<Integer> kodeE = new ArrayList<Integer>();
	
	
	public TextProcessing (String textname, String a, String b, String option) throws IOException{
		
		this.nameF = textname + "." + a + ".txt";
		this.nameE = textname + "." + b + ".txt";
		
		if(option.equals("train")){
		
		
		textF = new File(getClass().getResource(nameF).getPath());
		textE = new File(getClass().getResource(nameE).getPath());
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
		System.out.println("Zeilen gelesen");
		sortWords(textFrench, wörterListeF, textListeF);
		sortWords(textEnglish, wörterListeE, textListeE);
		System.out.println("sortieren fertig");
		for(int i=0; i<wörterListeF.size(); i++){
			kodeF.add(i,i);
		}
		for(int i=0; i<wörterListeE.size(); i++){
			kodeE.add(i,i);
		}
		kode(textFrench, wörterListeF, kodeF, kodeTextF);
		System.out.println("kodiert 1");
		kode(textEnglish, wörterListeE, kodeE, kodeTextE);
		System.out.println("kodiert 2");
		Dictionary dict = new Dictionary(a, kodeF, kodeE, kodeTextF, kodeTextE, wörterListeF, wörterListeE);
		}
		
//		
//		
//		
//		for(int a=0; a<kodeTextF.size(); a++){
//			System.out.println(kodeTextF.get(a));
//		}
		
//		listWords(textFrench, einfachListeF);
//		listWords(textEnglish, einfachListeE);
//		System.out.println("listen fertig");
//		for(int i=0; i<einfachListeF.size(); i++){
//			System.out.println(einfachListeF.get(i));
//		}
//		for(int i=0; i<einfachListeE.size(); i++){
//			System.out.println(einfachListeE.get(i));
//		}
		//
//		System.out.println(dict.f);
//		System.out.println(einfachListeE.size());
		if(option.equals("lookupWord")){
			URL url =new URL(getClass().getResource("ausgabe."+a+".txt").toString());
			ausgabe = new File(url.getPath());
			FileInputStream fis = new FileInputStream(ausgabe);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF8"));
			LookUpWord look = new LookUpWord(ausgabeList);
			look.lookUp(isr, ausgabe);
		}
		
		if(option.equals("lookupSentence")){
			URL url =new URL(getClass().getResource("ausgabe."+a+".txt").toString());
			ausgabe = new File(url.getPath());
			FileInputStream fis = new FileInputStream(ausgabe);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF8"));
			LookUpSentence look = new LookUpSentence(ausgabeList);
			look.lookUp(isr, ausgabe);
		}
		//System.out.println(wörterListeF.get(0).get(0));
		LengthModel length = new LengthModel(textFrench, textEnglish);
//		System.out.println(length.maxSize);
//		System.out.println(length.minSize);
//		System.out.println(length.factoring(5, 5));
		
		BigramModel bigram = new BigramModel(textListeF, wörterListeF);
		//System.out.println(bigram.train("es", "ist"));
		
	}
	
	public void kode(List<ArrayList<String>> text, List<String> wörter, List<Integer> kode, List<ArrayList<Integer>> kodeText){
		
		for(int i=0; i<text.size(); i++){
			kodeText.add(new ArrayList<Integer>());
			for(int a=0; a<text.get(i).size(); a++){
				String wort = text.get(i).get(a);
				int kodezahl = wörter.indexOf(wort);
				kodeText.get(i).add(a, kodezahl);
			}
		}
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
	
	public void readLineAusgabe(InputStreamReader isr2, List textFrench2){
		try {
			BufferedReader in = new BufferedReader(isr2);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeile = new String(zeile.getBytes(),"UTF8");
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
	
	public void sortWords(List<ArrayList<String>> text, List<String> wörter, List<String> textListe){
		wörter.add(0, text.get(0).get(0));
		int worte=0;
		
		for(int a=0; a<text.size(); a++){
			//System.out.println("a="+a);
			for(int b=0; b<text.get(a).size(); b++){
				//System.out.println("b="+ b);
				textListe.add(text.get(a).get(b));
					if(!(wörter.contains(text.get(a).get(b)))){
					wörter.add(worte, text.get(a).get(b));
					worte++;
				}}
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
