import java.util.ArrayList;
import java.util.List;



public class Dictionary {
	
	List<ArrayList<Float>> tabelle = new ArrayList<ArrayList<Float>>();
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	List<String> w�rterF = new ArrayList<String>();
	List<String> w�rterE = new ArrayList<String>();
	Float f=(float) 1;
	
	public Dictionary(List<String> w�rterF, List<String> w�rterE, List<ArrayList<String>> textFrench, List<ArrayList<String>> textEnglish){
		this.w�rterF=w�rterF;
		this.w�rterE=w�rterE;
		this.textFrench=textFrench;
		this.textEnglish=textEnglish;
		f=f/w�rterF.size();
		System.out.println(w�rterF.get(1));
		erstelleTabelle();
		System.out.println(tabelle.get(0));
		System.out.println(tabelle.get(1));
		berechnen();
		System.out.println("Tabelle:");
		System.out.println(tabelle.get(0));
		System.out.println(tabelle.get(1));
		normalisieren();
		System.out.println("Tabelle:");
		System.out.println(tabelle.get(0));
		System.out.println(tabelle.get(1));
		berechnen();
		System.out.println("Tabelle:");
		System.out.println(tabelle.get(0));
		System.out.println(tabelle.get(1));
		normalisieren();
		System.out.println("Tabelle:");
		System.out.println(tabelle.get(0));
		System.out.println(tabelle.get(1));
	}
	
	public void erstelleTabelle(){
		for(int i=0; i<w�rterE.size(); i++){
			tabelle.add(i, new ArrayList<Float>());
			for(int a=0; a<w�rterF.size(); a++){
				tabelle.get(i).add(a, f);
			}
		}
	}
	
	public void train(){
		berechnen();
		normalisieren();
	}
	
	public void berechnen(){
	
		List<ArrayList<Float>> tab = new ArrayList<ArrayList<Float>>();
		
		for(int i=0; i<w�rterE.size(); i++){
			tab.add(i, new ArrayList<Float>());
			for(int a=0; a<w�rterF.size(); a++){
				tab.get(i).add(a, (float) 0);
			}
		}
	
		Float s=(float) 0;
		
		
		for(int i=0; i<textFrench.size(); i++){					//i f�r F-Satz
			System.out.println("neueSchleife");
			
			for(int b=0; b<textFrench.get(i).size(); b++){		//b f�r F-Wort
			s=(float) 0;
				
				
				for(int a=0; a<textEnglish.get(i).size(); a++){//k�nnte evt verbessert werden
					int index = w�rterF.indexOf(textFrench.get(i).get(b));
					System.out.println(index);
					System.out.println(b);
					s= s + tabelle.get(w�rterE.indexOf(textEnglish.get(i).get(a))).get(index);
				}
				
				for(int c=0; c<textEnglish.get(i).size(); c++){
				int zahlF = w�rterF.indexOf(textFrench.get(i).get(b));
				int zahlE = w�rterE.indexOf(textEnglish.get(i).get(c));
				tab.get(w�rterE.indexOf(textEnglish.get(i).get(c))).set(zahlF, tab.get(zahlE).get(zahlF) + (tabelle.get(zahlE).get(zahlF)/s));
				for(int a=0; a<tab.size(); a++){
					System.out.println(tab.get(a));	
					}
				}
			
			System.out.println(s);
			
			}
			
		}
//		for(int i=0; i<tab.size(); i++){
//		System.out.println(tab.get(i));	
//		}
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
				tab.get(i).set(b, (tab.get(i).get(b)/summe));
			}
			summe=0;
		}
		for(int i=0; i<tab.size(); i++){
			System.out.println(tab.get(i));	
			}
		tabelle=tab;
	}
	

}
