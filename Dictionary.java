import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class Dictionary {
	
	List<ArrayList<Float>> tabelle = new ArrayList<ArrayList<Float>>();
	List<ArrayList<Integer>> textFrench = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> textEnglish = new ArrayList<ArrayList<Integer>>();
	List<Integer> w�rterF = new ArrayList<Integer>();
	List<Integer> w�rterE = new ArrayList<Integer>();
	List<String> w�rterListeF = new ArrayList<String>();
	List<String> w�rterListeE = new ArrayList<String>();
	String a;
	Float f=(float) 1;
	
	public Dictionary(String a, List<Integer> kw�rterF, List<Integer> kw�rterE, List<ArrayList<Integer>> textFrench, List<ArrayList<Integer>> textEnglish, List<String> w�rterF, List<String> w�rterE){
		this.a = a;
		this.w�rterF=kw�rterF;
		this.w�rterE=kw�rterE;
		this.textFrench=textFrench;
		this.textEnglish=textEnglish;
		this.w�rterListeF = w�rterF;
		this.w�rterListeE = w�rterE;
		f=f/w�rterF.size();
		System.out.println(textFrench.size());
		erstelleTabelle();
		System.out.println("tabelle erstellt");
		
		for(int i=0; i<=20; i++){
		train(i);
		}
		
//		for(int i=0; i<tabelle.size(); i++){
//		System.out.println(tabelle.get(i).get(w�rterF.indexOf("sollten")));
//		}
//		for(int i=0; i<tabelle.size(); i++){
//			System.out.println(tabelle.get(i));
//		}
		
	    
	}
	
	public void erstelleTabelle(){
		for(int i=0; i<w�rterE.size(); i++){
			tabelle.add(i, new ArrayList<Float>());
			for(int a=0; a<w�rterF.size(); a++){
				tabelle.get(i).add(a, f);
			}
		}
	}
	
	public void train(int f){
		berechnen();
		System.out.println("berechnet");
		normalisieren();
		
		try {
	        PrintWriter p = new PrintWriter (new FileWriter(getClass().getResource("ausgabe."+a+".txt").getPath()));
	        for (int i = 0; i <w�rterF.size(); ++i) {
	        	for(int a=0; a<w�rterE.size(); a++){
	        		if(tabelle.get(a).get(i)>=0.001){
	        String  s =w�rterListeE.get(a)+" "+w�rterListeF.get(i)+" "+tabelle.get(a).get(i);
	          p.println(s);
	        }}}
	        p.close();
	      }
	      catch (IOException e) {
	        System.out.println("Fehler: "+e.toString());
	      }
		System.out.println("Training absolviert: "+ f);
	}
	
	public void berechnen(){
	
		List<ArrayList<Float>> tab = new ArrayList<ArrayList<Float>>();
		
		for(int i=0; i<w�rterE.size(); i++){
			tab.add(i, new ArrayList<Float>());
			for(int a=0; a<w�rterF.size(); a++){
				tab.get(i).add(a, (float) 0);
			}
		}
	System.out.println("neue tab");
		Float s=(float) 0;
		
		for(int i=0; i<textFrench.size(); i++){					//i f�r F-Satz
		
			
			for(int b=0; b<textFrench.get(i).size(); b++){		//b f�r F-Wort
			s=(float) 0;
		
				for(int a=0; a<textEnglish.get(i).size(); a++){//k�nnte evt verbessert werden
					int index = textFrench.get(i).get(b);
					s= s + tabelle.get(textEnglish.get(i).get(a)).get(index);
				}
				
				for(int c=0; c<textEnglish.get(i).size(); c++){
				int zahlF = textFrench.get(i).get(b);
				int zahlE = textEnglish.get(i).get(c);
				tab.get(textEnglish.get(i).get(c)).set(zahlF, tab.get(zahlE).get(zahlF) + (tabelle.get(zahlE).get(zahlF)/s));
				}
		
			
			}
			//System.out.println("satz "+i+ " erledigt");
		}
//		for(int i=0; i<tab.size(); i++){
//		System.out.println(tab.get(i));	
//		}
		System.out.println("umrechnen beginnt");
		tabelle = tab;
		
	}
	
	public void normalisieren(){
		
		float summe=0;
		
		List<ArrayList<Float>> tab = tabelle;
		
		for(int i=0; i<tab.size(); i++){
			for(int a=0; a<tab.get(i).size(); a++){
				summe= summe + tab.get(i).get(a);
			}
			for(int b=0; b<tab.get(i).size(); b++){
				float a =tab.get(i).get(b);
				tab.get(i).set(b, (a/summe));
			}
			summe=0;
		}
		
		tabelle=tab;
	}
	

}
