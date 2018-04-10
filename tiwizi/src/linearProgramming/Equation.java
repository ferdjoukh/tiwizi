package linearProgramming;

public class Equation {
	
	private int MAX=10;
	
	private String [] unknowns;
	private int [] coefficients;
	private String operand;
	private int position;
	 
	
	public Equation(String operand){
		this.operand=operand;
		unknowns= new String[MAX];
		coefficients = new int[MAX];
		position=0;
	}
	
	public void addVariable(String unknown,int coeff){
		unknowns[position]=unknown;
		coefficients[position]=coeff;
		position++;
	}

	public String[] getUnknowns() {
		return unknowns;
	}

	public int[] getCoefficients() {
		return coefficients;
	}

	public String getOperand() {
		return operand;
	}
	
	public String toString(){
		
		String result="";
		
		for(int i=0;i<position;i++){
			if(i==0){
				result= result+ coefficients[i]+"*"+unknowns[i];
			}else{
				result= result+ "+"+ coefficients[i]+"*"+unknowns[i];
			}
		}
		
		result= result +" "+ operand+ " 0";
		
		return result;
	}

}
