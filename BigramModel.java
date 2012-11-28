import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class BigramModel {
	
	List<String> wörter = new ArrayList<String>();
	List<String> einfachWörter = new ArrayList<String>();
	
	List<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
	
	int zähler=0;
	
	public BigramModel(List<String> wörter, List<String> einfachWörter){
		this.wörter=wörter;
		this.einfachWörter=einfachWörter;
		train();
	}
	
	public void train(){
		for(int i=0; i<einfachWörter.size(); i++){
			for(int a=0; a<einfachWörter.size(); a++){
				if(trainWord(einfachWörter.get(i), einfachWörter.get(a))>0.1){
					
					result.add(zähler, new ArrayList());
					result.get(zähler).add(i+"");
					result.get(zähler).add(a+"");
					result.get(zähler).add(trainWord(wörter.get(i), wörter.get(a))+"");
					zähler++;
					System.out.println(result.get(zähler-1));
				}
			}
		}
		System.out.println("bigram fertig");
		try {
	        PrintWriter p = new PrintWriter (new FileWriter(getClass().getResource("bigram.txt").getPath()));
	        for (int i = 0; i <result.size(); ++i) {
	        	String s = result.get(i).get(0) +" "+result.get(i).get(1)+" "+result.get(i).get(2);
	          p.println(s);
	        }
	        p.close();
	      }
	      catch (IOException e) {
	        System.out.println("Fehler: "+e.toString());
	      }
	}
	
	public float trainWord(String x, String y){
		int zählerX=0;
		int zählerXY=0;
		float factor;
		for(int i=0; i<wörter.size()-1; i++){
			if((wörter.get(i).equals(x))&&(wörter.get(i+1).equals(y))){
				zählerXY++;
			}
			if(wörter.get(i).equals(x)){
				zählerX++;
			}
		}
		factor=zählerXY;
		factor=factor/zählerX;
		return factor;
	}

}
