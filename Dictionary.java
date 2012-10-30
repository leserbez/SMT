import java.util.ArrayList;
import java.util.List;



public class Dictionary {
	
	List<ArrayList<Float>> tabelle = new ArrayList<ArrayList<Float>>();
	List<ArrayList<String>> textFrench = new ArrayList<ArrayList<String>>();
	List<ArrayList<String>> textEnglish = new ArrayList<ArrayList<String>>();
	List<String> wörterF = new ArrayList<String>();
	List<String> wörterE = new ArrayList<String>();
	Float f=(float) 1;
	
	public Dictionary(List<String> wörterF, List<String> wörterE, List<ArrayList<String>> textFrench, List<ArrayList<String>> textEnglish){
		this.wörterF=wörterF;
		this.wörterE=wörterE;
		this.textFrench=textFrench;
		this.textEnglish=textEnglish;
		f=f/wörterF.size();
	}
	
	public void erstelleTabelle(){
		for(int i=0; i<wörterE.size(); i++){
			for(int a=0; a<wörterF.size(); a++){
				tabelle.get(i).add(f);
			}
		}
	}

}
