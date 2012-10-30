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
	}
	
	public void erstelleTabelle(){
		for(int i=0; i<w�rterE.size(); i++){
			for(int a=0; a<w�rterF.size(); a++){
				tabelle.get(i).add(f);
			}
		}
	}

}
