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
}
