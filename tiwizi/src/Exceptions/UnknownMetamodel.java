package Exceptions;

public class UnknownMetamodel extends Exception {
	
	public UnknownMetamodel(String metamodel){
	
		super(metamodel+" is not a valid meta-model file");

	}
}
