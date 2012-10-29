import java.util.ArrayList;
import java.util.List;


public class LengthModel {
	
	float factor;
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	int maxSize=0;

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
	}
 
}
