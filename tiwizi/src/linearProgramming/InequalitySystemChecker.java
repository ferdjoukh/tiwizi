package linearProgramming;

import java.util.ArrayList;

import utils.MetProblem;

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
	
	/**
	 * 
	 * Given an inequality and the candidates values, it says if the inequality is consistent
	 * 
	 * eg: 2*x -4*y <= 0 values=[1,2]
	 *    result: 2 - 8 <= 0 ? true
	 * 
	 * eg: 2*x -4*y <= 0 values=[3,1]
	 *     result: 6 - 4 <=0 ? false
	 * 
	 * @param inequality
	 * @param candidateValues
	 * @return
	 * @throws UnknownOperandForInequality 
	 */
	public static boolean isInequalityConsistent(Inequality inequality, 
			ArrayList<Integer> candidateValues) throws UnknownOperandForInequality{
		
		boolean result = false;
		
		int valueOfLeftSide = solveInequality(inequality, candidateValues);
		
		switch (inequality.getOperand()){
			case "<=": if(valueOfLeftSide<=0) return true; else return false;
		
			case ">=": if(valueOfLeftSide>=0) return true; else return false;
				
			case "<":  if(valueOfLeftSide<0) return true; else return false;
				
			case ">":  if(valueOfLeftSide>0) return true; else return false;
				
			case "=":  if(valueOfLeftSide==0) return true; else return false;
				
			default: throw new UnknownOperandForInequality(inequality.getOperand()); 			
		}	
	}
	
	/**
	 * It browses the ArrayList of inequalities and solve them according to corresponding candidate values.
	 * If an inconsistent inequality is met, it is added to ArrayList of problems
	 * 
	 * @param inequalities
	 * @param candidates
	 * @return
	 * @throws UnknownOperandForInequality
	 */
	public static ArrayList<MetProblem> checkAllSystem(InequalitySystem system) throws UnknownOperandForInequality{
		
		assert system.getInequalities().size()==system.getCandidates().size(): 
			" not enough candidate values: "+ system.getInequalities().size() 
			+ " != "+system.getCandidates().size();
		
		ArrayList<MetProblem> problems= new ArrayList<MetProblem>();
		
		for(int i=0; i<system.getInequalities().size();i++){
			boolean res=isInequalityConsistent(system.getInequalities().get(i), system.getCandidates().get(i));
			
			if(!res){
				MetProblem problem= new MetProblem(system.getInequalities().get(i), system.getCandidates().get(i));
				problems.add(problem);
			}
		}
		
		return problems;
	}	
}
