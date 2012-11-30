
public class Probability {
	
	float bigram[];
	float length;
	float align;
	float dict[];
	float prob;
	
	public Probability(float bigram[], float length, float align, float dict[]){
		this.bigram = bigram.clone();
		this.length=length;
		this.align = align;
		this.dict = dict.clone();
		
		solve();
	}
	
	private void solve(){
		float s=bigram[0];
		for(int i=1; i<bigram.length; i++){
			s=s*bigram[i];
		}
		s=s*length*align;
		for(int i=0; i<dict.length; i++){
			s=s*dict[i];
		}
		prob=s;
	}
	
	public float getProb(){
		return prob;
	}
	

}