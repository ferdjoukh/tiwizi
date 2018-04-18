package linearProgramming;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InequalitySystem {
	
	private ArrayList<Inequality> inequalities;
	private ArrayList<ArrayList<Integer>> candidates;
	
	public InequalitySystem(ArrayList<Inequality> inequalities, ArrayList<ArrayList<Integer>> candidates) {
		this.inequalities = inequalities;
		this.candidates = candidates;
	}

	public InequalitySystem(){
		inequalities= new ArrayList<Inequality>();
		candidates= new ArrayList<ArrayList<Integer>>();
	}
		
	public void addInequalityCandidate(Inequality i, ArrayList<Integer> c){
		inequalities.add(i);
		candidates.add(c);
	}
	
	public ArrayList<Inequality> getInequalities(){
		return inequalities;
	}

	public ArrayList<ArrayList<Integer>> getCandidates() {
		return candidates;
	}
	
	/**
	 * It prints the InequlaitySystem
	 * 
	 * @param mode: 0: only inequalities
	 * 				1  inequalities + candidates
	 * @return printed version of InequalitySystem
	 */
	public String printInequalitySystem(Integer mode){
		
		String res="";
		for(int i=0;i<inequalities.size();i++){
				
				if(mode == 0){
					res= res+ inequalities.get(i).toString() + "\n";
				}
				
				if(mode==1){
					res= res+ inequalities.get(i).toString() + "  " + candidates.get(i).toString() +"\n";
				}
		}
		
		return res;
	}
}
