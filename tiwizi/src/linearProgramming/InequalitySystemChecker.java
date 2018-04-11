package linearProgramming;

import java.util.ArrayList;

public class InequalitySystemChecker {
	
	/**
	 * Given an inquality and the candidate values, it returns the value of left side
	 * 
	 * eg: 2*x -4*y values=[1,2]
	 *    result= 2*1 -4*2 = -6
	 *  
	 * 
	 * @param inequality
	 * @param candidateValues
	 * @return
	 */
	
	public static int solveInequality(Inequality inequality, ArrayList<Integer> candidateValues){
		
		assert inequality.getPosition()==candidateValues.size(): 
			" not enough candidate values: "+ inequality.getPosition()+ " != "+candidateValues.size();
		
		int result=0;
		
		for (int i=0; i<candidateValues.size();i++){
			result= result + candidateValues.get(i)*inequality.getCoefficients()[i];
		}
		
		return result;
		
	}
	
	public static boolean isInequalityConsistant(Inequality inequality, 
			ArrayList<Integer> candidateValues){
		
		boolean result= false;
		
		
		
		return result;
	}

}
