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
	private String name;
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
	
	List<String> einfachListeF = new ArrayList<String>();
	List<String> einfachListeE = new ArrayList<String>();
	List<Integer> kodeF = new ArrayList<Integer>();
	List<Integer> kodeE = new ArrayList<Integer>();
	
	
	public TextProcessing (String textname, String e, String f, String option) throws IOException{
		
		this.name = textname + "." + f + ".txt";
		
		if(option.equals("train")){
		
		
		textF = new File(getClass().getResource("train.de.txt").getPath());
		textE = new File(getClass().getResource("train.en.txt").getPath());
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
		sortWords(textFrench, wörterListeF);
		sortWords(textEnglish, wörterListeE);
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
		Dictionary dict = new Dictionary(kodeF, kodeE, kodeTextF, kodeTextE, wörterListeF, wörterListeE);
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
		if(option.equals("lookup")){
			URL url =new URL(getClass().getResource("ausgabe.txt").toString());
			ausgabe = new File(url.getPath());
			FileInputStream fis = new FileInputStream(ausgabe);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF8"));
			lookUp(isr, ausgabe);
		}
		//System.out.println(wörterListeF.get(0).get(0));
		LengthModel length = new LengthModel(textFrench, textEnglish);
//		System.out.println(length.maxSize);
//		System.out.println(length.minSize);
//		System.out.println(length.factoring(5, 5));
		
		BigramModel bigram = new BigramModel(wörterListeF);
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
	
	public void lookUp(InputStreamReader isr2, File ausgabe) throws IOException{
		readLineAusgabe(isr2, ausgabeList);
		while(true){
		InputStreamReader isr = new InputStreamReader(System.in, Charset.forName("UTF8"));
	    BufferedReader br = new BufferedReader(isr);
	    System.out.println("Bitte zu übersetzendes Wort eingeben:");
	    String eingabe = br.readLine();
	    List<ArrayList<String>> neu=new ArrayList<ArrayList<String>>();
	    int a =0;
	    boolean bool = false;
		for(int i=0; i<ausgabeList.size(); i++){	
			
			if (ausgabeList.get(i).get(0).equals(eingabe)){
				neu.add(new ArrayList());
				neu.get(a).add(ausgabeList.get(i).get(0));
				neu.get(a).add(ausgabeList.get(i).get(1));
				neu.get(a).add(ausgabeList.get(i).get(2));
				a++;
				bool = true;
			}
		//System.out.println(neu.size());	
		
		}
		if(bool==true){
		neu = sortArray(neu);
		for(int i=0; i<neu.size(); i++){
			if(neu.get(i)!=null){
			System.out.println(neu.get(i).get(1) +" "+ neu.get(i).get(2));
		}}}
		else{
			System.out.println("Wort unbekannt!");
		}
		}
		//return null;
	}
	
	public List<ArrayList<String>> sortArray(List<ArrayList<String>> array){
		List<Float> neu = new ArrayList<Float>();
		for(int i=0; i<array.size(); i++){
			float f =  Float.valueOf(array.get(i).get(2)).floatValue();
			neu.add(f);
		}
		Collections.sort(neu);
//		for(int i=0; i<neu.size(); i++){
//			System.out.println(neu.get(i));
//		}
		List<ArrayList<String>> neu2 = new ArrayList<ArrayList<String>>();
		neu2.add(0,null);
		neu2.add(1,null);
		neu2.add(2,null);
		neu2.add(3,null);
		neu2.add(4,null);
		for(int i=0; i<array.size(); i++){
			if(neu.size()>0){
			if(array.get(i).get(2).equals(neu.get(neu.size()-1).toString())){
				neu2.set(0, array.get(i));
			}	}		
			if(neu.size()>1){
			if(array.get(i).get(2).equals(neu.get(neu.size()-2).toString())){
				neu2.set(1, array.get(i));
			}}			
			if(neu.size()>2){
			if(array.get(i).get(2).equals(neu.get(neu.size()-3).toString())){
				neu2.set(2, array.get(i));
			}}			
			if(neu.size()>3){
			if(array.get(i).get(2).equals(neu.get(neu.size()-4).toString())){
				neu2.set(3, array.get(i));
			}}
			if(neu.size()>4){
			if(array.get(i).get(2).equals(neu.get(neu.size()-5).toString())){
				neu2.set(4, array.get(i));
			}}
		}
		return neu2;
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
	
	public void sortWords(List<ArrayList<String>> text, List<String> wörter){
		wörter.add(0, text.get(0).get(0));
		int worte=0;
		
		for(int a=0; a<text.size(); a++){
			//System.out.println("a="+a);
			for(int b=0; b<text.get(a).size(); b++){
				//System.out.println("b="+ b);
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
