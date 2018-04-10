package linearProgramming;

public class Equation {
	
	private int MAX=10;
	
	private String [] unknowns;
	private double [] coefficients;
	private String operand;
	private int position;
	 
	
	public Equation(String operand){
		this.operand=operand;
		unknowns= new String[MAX];
		coefficients = new double[MAX];
		position=0;
	}
	
	public void addVariable(String unknown,Double coeff){
		unknowns[position]=unknown;
		coefficients[position]=coeff;
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
	
	

}
