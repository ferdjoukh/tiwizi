package linearProgramming;

/**
 *
 * @author Adel ferdjoukh
 * @email ferdjoukh[at]gmail[dot]com
 *
 * This class defines an Inequality for TIWIZI tool.
 *   The inequalities that are defined here have the following shape:
 *   	1*x +2*y -3*z <= 0
 *   
 *   	x,y,z are unknowns
 *      1,2,-3 are coefficient
 *      <= is the operand
 *      
 *      
 */
public class Inequality {
	
	private int MAX=10;
	
	private String [] unknowns;
	private double [] coefficients;
	private String operand;
	private int position;
	 
	/**
	 * It creates an empty inequality by giving only the operand 
	 * 
	 * @param operand <=
	 */
	public Inequality(String operand){
		this.operand=operand;
		unknowns= new String[MAX];
		coefficients = new double[MAX];
		position=0;
	}
	
	/**
	 * 
	 * It adds a new variable to the inequality
	 *  
	 * @param unknown x,y,...
	 * @param coeff1 0,1,2,-1,....
	 */
	public void addVariable(String unknown,Double coeff1){
		unknowns[position]=unknown;
		coefficients[position]=coeff1;
		position++;
	}

	public String[] getUnknowns() {
		return unknowns;
	}

	public double[] getCoefficients() {
		return coefficients;
	}

	public String getOperand() {
		return operand;
	}
			
	public int getPosition() {
		return position;
	}

	/**
	 * It prints an equation using the classical shape: a*x +b*y -c*z <= 0
	 */
	public String toString(){
		
		String result="";
		
		for(int i=0;i<position;i++){
			if(i==0){
					result= result+ coefficients[i]+"*"+unknowns[i];
			}else{
				if(coefficients[i]<0)
					result= result+ " "+ coefficients[i]+"*"+unknowns[i];
				else
					result= result+ " +"+ coefficients[i]+"*"+unknowns[i];
			}
		}
		String opFormat= String.format("%2s", operand);
		result= result +" "+ opFormat + " 0";
		
		return result;
	}

}
