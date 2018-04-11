package linearProgramming;

import java.util.ArrayList;

public class InequalitySystem {
	
	private ArrayList<Inequality> system;
	
	public InequalitySystem(){
		system= new ArrayList<Inequality>();
	}
	
	public void addInequality(Inequality e){
		system.add(e);
	}
	
	public ArrayList<Inequality> getSystem(){
		return system;
	}

}
