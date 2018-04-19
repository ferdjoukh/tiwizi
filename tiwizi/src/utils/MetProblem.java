package utils;

import java.util.ArrayList;

import linearProgramming.*;

/**
 * This class is used for writing feedback when a problem is met with an inquality. 
 * 
 * @author ferdjoukh
 *
 */
public class MetProblem {
	
	private Inequality inequality;
	private ArrayList<Double> candidates;
	private String message;
	
	public MetProblem(Inequality inequality, ArrayList<Double> candidates){
		this.inequality=inequality;
		this.candidates=candidates;
		setMessage();
	}
	
	/**
	 * It sets the message of this problem regarding to inequality
	 * variables
	 * 
	 * eg : "more Walls or less Rooms"
	 * @return 
	 * 
	 */
	public void setMessage(){
		
		ArrayList<String> plus= new ArrayList<String>();
		ArrayList<String> moins= new ArrayList<String>();
		
		for(int i=0;i<inequality.getPosition();i++){
			if(inequality.getCoefficients()[i]>0){
				plus.add(inequality.getUnknowns()[i]);
			}
			else if(inequality.getCoefficients()[i]<0){
				moins.add(inequality.getUnknowns()[i]);
			}
		}
		
		String more="More ";
		String less="Less ";
		
		switch(inequality.getOperand()){
			case "<=": case "<":
				more=more+ moins.toString();
				less=less+ plus.toString();
				break;
			
			case ">=": case ">":
				more=more+ plus.toString();
				less=less+ moins.toString();
				break;
		}
		
		this.message= more+ " or "+ less;
	}
	
	public Inequality getInequality() {
		return inequality;
	}

	public ArrayList<Double> getCandidates() {
		return candidates;
	}

	public String getMessage() {
		return message;
	}
	
	public String toString(){
		
		String res= inequality.toString()+ " " +candidates.toString()+ " >> " + message;
		return res;
	}
}
