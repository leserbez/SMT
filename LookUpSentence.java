import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class LookUpSentence {
	
	List<ArrayList<String>> ausgabeList=new ArrayList<ArrayList<String>>();;
	List<ArrayList<String>> bigram = new ArrayList<ArrayList<String>>();
	List<String> einfachWörter = new ArrayList<String>();
	HashSet<Float[]> stack = new HashSet<Float[]>();
	List zeilen = new ArrayList();
	List<String> wörter = new ArrayList<String>();
	int zeilenZähler=0;
	String[] wort;
	String[] eingabe;
	InputStreamReader isr;
	
	public LookUpSentence(List<ArrayList<String>> ausgabeList, List<String> einfachWörter) throws IOException{
		//this.ausgabeList=ausgabeList;
		this.einfachWörter=einfachWörter;
		URL url =new URL(getClass().getResource("bigram.txt").toString());
		File aus = new File(url.getPath());
		FileInputStream fis = new FileInputStream(aus);
		isr = new InputStreamReader(fis, Charset.forName("UTF8"));
		
	}
	
	public void readLineBigram(List text){
		
		try {
			BufferedReader in = new BufferedReader(isr);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				zeile = new String(zeile.getBytes(),"UTF8");
				zeilen.add(zeilenZähler, zeile);	//einzelne Zeilen
				wort = zeile.split(" ");			//Array mit Wörtern einer Zeile
				
				for(int i=0; i<wort.length; i++){	//alle Wörter des Textes
				wörter.add(wort[i]);
				}
				
				text.add(wörter);					//ArrayList mit Arrays, die Wörter pro Zeile beinhalten
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
	
	public void look(InputStreamReader isr2) throws IOException{
		readLineAusgabe(isr2, ausgabeList);
		zeilenZähler=0;
		readLineBigram(bigram);
		System.out.println(bigram);
		while(true){
		InputStreamReader isr = new InputStreamReader(System.in, Charset.forName("UTF8"));
	    BufferedReader br = new BufferedReader(isr);
	    System.out.println("Bitte zu übersetzendes Wort eingeben:");
	    String eingab = br.readLine();
	    eingabe = eingab.split(" ");
	    //while zielsatz nicht fertig
	    for(int i=0; i<eingabe.length; i++){					//stack füllen
	    	for(int a=0; a<einfachWörter.size(); a++){
	    		if(eingabe[i].equals(einfachWörter.get(a))){
	    			Float[] b = {(float) i , (float) a};
	    			stack.add(b);
	    		}
	    	}
	    }
	    //wahrscheinlichkeit ausrechnen
	    
	    
	    //bestes auswählen, festhalten
	    
	    //stack leeren
		}
	}
	
	public void lookUp(InputStreamReader isr2, File ausgabe) throws IOException{
		readLineAusgabe(isr2, ausgabeList);
		zeilenZähler=0;
		readLineBigram(bigram);
		System.out.println(bigram);
		while(true){
		InputStreamReader isr = new InputStreamReader(System.in, Charset.forName("UTF8"));
	    BufferedReader br = new BufferedReader(isr);
	    System.out.println("Bitte zu übersetzendes Wort eingeben:");
	    String eingab = br.readLine();
	    eingabe = eingab.split(" ");
	    List<ArrayList<String>> neu=new ArrayList<ArrayList<String>>();
	    int a =0;
	    boolean bool = false;
	    for(int b=0; b<eingabe.length; b++){
	    	System.out.println("schleife");
	    	neu.add(b,new ArrayList());
		for(int i=0; i<ausgabeList.size(); i++){	
			//System.out.println(ausgabeList.size());
			
			if (ausgabeList.get(i).get(0).equals(eingabe[b])){
				
				neu.get(b).add(ausgabeList.get(i).get(1)); //vlt 0?
				neu.get(b).add(ausgabeList.get(i).get(2));
				
				
				bool = true;
			}
			a++;
		}
		//System.out.println(neu.size());	
		
		}
		if(bool==true){
		ArrayList<String> neu2 = sortArray(neu);
		String aus = "";
		for(int i=0; i<neu2.size(); i++){
			if(neu2.get(i)!=null){
			aus = aus +" "+ neu2.get(i);
		}}
		System.out.println(aus);
		}
		
		else{
			System.out.println("Satz unbekannt!");
		}
		}
		//return null;
	}
	
	public ArrayList<String> sortArray(List<ArrayList<String>> array){
		List<ArrayList<Float>> neu = new ArrayList<ArrayList<Float>>();
		for(int a=0; a<array.size(); a++){
			neu.add(new ArrayList());
			for(int b=1; b<array.get(a).size(); b=b+2){
				float f =  Float.valueOf(array.get(a).get(b)).floatValue();
				neu.get(a).add(f);
			}
			//System.out.println(neu.get(a));
			Collections.sort(neu.get(a));
			//System.out.println(neu.get(a));
		}
		ArrayList<String> neu2 = new ArrayList<String>();
		for(int i=0; i<array.size(); i++){
			neu2.add(i,null);
			for(int a=1; a<array.get(i).size(); a=a+2){
				if(array.get(i).get(a).equals(neu.get(i).get(neu.get(i).size()-1).toString())){
					neu2.set(i, array.get(i).get(a-1));
				}
			}
		}
		return neu2;
	}
	


}
