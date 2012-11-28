import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class BigramModel {
	
	List<String> w�rter = new ArrayList<String>();
	List<String> einfachW�rter = new ArrayList<String>();
	
	List<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
	
	int z�hler=0;
	
	public BigramModel(List<String> w�rter, List<String> einfachW�rter){
		this.w�rter=w�rter;
		this.einfachW�rter=einfachW�rter;
		train();
	}
	
	public void train(){
		for(int i=0; i<einfachW�rter.size(); i++){
			for(int a=0; a<einfachW�rter.size(); a++){
				if(trainWord(einfachW�rter.get(i), einfachW�rter.get(a))>0.1){
					
					result.add(z�hler, new ArrayList());
					result.get(z�hler).add(i+"");
					result.get(z�hler).add(a+"");
					result.get(z�hler).add(trainWord(w�rter.get(i), w�rter.get(a))+"");
					z�hler++;
					System.out.println(result.get(z�hler-1));
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
		int z�hlerX=0;
		int z�hlerXY=0;
		float factor;
		for(int i=0; i<w�rter.size()-1; i++){
			if((w�rter.get(i).equals(x))&&(w�rter.get(i+1).equals(y))){
				z�hlerXY++;
			}
			if(w�rter.get(i).equals(x)){
				z�hlerX++;
			}
		}
		factor=z�hlerXY;
		factor=factor/z�hlerX;
		return factor;
	}

}
