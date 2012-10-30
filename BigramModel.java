import java.util.ArrayList;
import java.util.List;


public class BigramModel {
	
	List<String> wörter = new ArrayList<String>();
	
	public BigramModel(List<String> wörter){
		this.wörter=wörter;
	}
	
	public float train(String x, String y){
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
