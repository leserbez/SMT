import java.util.ArrayList;
import java.util.List;


public class BigramModel {
	
	List<String> w�rter = new ArrayList<String>();
	
	public BigramModel(List<String> w�rter){
		this.w�rter=w�rter;
	}
	
	public float train(String x, String y){
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
