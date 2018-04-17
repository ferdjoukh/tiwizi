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
	private ArrayList<Integer> candidates;
	private String message;
	
	public MetProblem(Inequality inequality, ArrayList<Integer> candidates){
		this.inequality=inequality;
		this.candidates=candidates;
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
		
		this.message="";
	}

}
