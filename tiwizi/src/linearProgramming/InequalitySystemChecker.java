package linearProgramming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;

import Exceptions.UnknownOperandForInequality;
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
	
	public static double solveInequality(Inequality inequality, ArrayList<Double> candidateValues){
		
		assert inequality.getPosition()==candidateValues.size(): 
			" not enough candidate values: "+ inequality.getPosition()+ " != "+candidateValues.size();
		
		double result=0;
		
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
			ArrayList<Double> candidateValues) throws UnknownOperandForInequality{
		
		boolean result = false;
		
		double valueOfLeftSide = solveInequality(inequality, candidateValues);
		
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
	 * It browses the system.ArrayList<Inequality> and 
	 * solves them according to corresponding system.Candidates values.
	 * If an inconsistent inequality is met, it is added to ArrayList<MetProblem>.
	 * The latter is returned
	 *  
	 * @param system
	 * @return ArrayList<MetProblem> It contains all inconsistent inequalities with a help message
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
	
	public static String printMetProblems(ArrayList<MetProblem> problems){
		
		String res="";
		
		for(MetProblem problem: problems){
			res= res+ problem.toString() + "\n";
		}
		return res;
	}
	
	public static String generateLogFile(ArrayList<MetProblem> problems) throws IOException{
		
		new File("log").mkdir();
		long time=System.currentTimeMillis();
		String logFilePath="log/log-"+time;
		
		PrintWriter ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(logFilePath+".tiwizi")));
		
		for(MetProblem problem: problems){
			 ecrivain.write(problem.verbosePrint() + "\n");
		}
		
		ecrivain.close();
		
		return logFilePath;
		
	}
	
	public static void createRecapPDF(String logFilePath) throws IOException{
		Runtime runtime= Runtime.getRuntime();
		
		String command= "./pdf-creator.sh "+logFilePath+".tiwizi "+logFilePath; 
		
		System.out.println("");
		
		System.out.println("Exceute this command: "+ command);
		
		runtime.exec(command);
	}
}
