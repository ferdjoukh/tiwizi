package Exceptions;

public class UnknownOperandForInequality extends Exception {
	
	public UnknownOperandForInequality(String value){
		
		super(value+" is not a valid operand");
	}

}
