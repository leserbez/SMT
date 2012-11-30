import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LookUpWord {
	
	List<ArrayList<String>> ausgabeList = new ArrayList<ArrayList<String>>();
	List zeilen = new ArrayList();
	List<String> wörter = new ArrayList<String>();
	int zeilenZähler=0;
	String[] wort;
	
	public LookUpWord(List<ArrayList<String>> ausgabeList){
		this.ausgabeList=ausgabeList;
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
		neu2.add(5,null);
		neu2.add(6,null);
		neu2.add(7,null);
		neu2.add(8,null);
		neu2.add(9,null);
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
			if(neu.size()>5){
			if(array.get(i).get(2).equals(neu.get(neu.size()-6).toString())){
					neu2.set(5, array.get(i));
			}	}		
			if(neu.size()>6){
			if(array.get(i).get(2).equals(neu.get(neu.size()-7).toString())){
					neu2.set(6, array.get(i));
			}}			
			if(neu.size()>7){
			if(array.get(i).get(2).equals(neu.get(neu.size()-8).toString())){
					neu2.set(7, array.get(i));
			}}			
			if(neu.size()>8){
			if(array.get(i).get(2).equals(neu.get(neu.size()-9).toString())){
					neu2.set(8, array.get(i));
			}}
			if(neu.size()>9){
			if(array.get(i).get(2).equals(neu.get(neu.size()-10).toString())){
					neu2.set(9, array.get(i));
			}}
		}
		return neu2;
	}

}
