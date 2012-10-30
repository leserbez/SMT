import java.util.ArrayList;
import java.util.List;


public class LengthModel {
	
	//float factor;
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	int maxSize=0;
	int minSize=20;

	public LengthModel(List<ArrayList<String>> textF, List<ArrayList<String>> textE){
		textFrench=textF;
		textEnglish=textE;
		train();
	}
	
	public void train(){
		for(int i=0; i<textFrench.size(); i++){
			if(maxSize<textFrench.get(i).size()){
				maxSize=textFrench.get(i).size();
			}
		}
		for(int i=0; i<textFrench.size(); i++){
			if(minSize>textFrench.get(i).size()){
				minSize=textFrench.get(i).size();
			}
		}
	}
	
	public float factoring(int m, int l){
		float factor;
		int zählerML=0;
		int zählerL=0;
		for(int i=0; i<textFrench.size(); i++){
			if((textFrench.get(i).size()==m)&&(textEnglish.get(i).size()==l)){
				zählerML++;
			}
		}
		for(int i=0; i<textFrench.size(); i++){
			if(textEnglish.get(i).size()==l){
				zählerL++;
			}
		}
		factor=zählerML;
		factor=factor/zählerL;
		return factor;
	}
 
}
