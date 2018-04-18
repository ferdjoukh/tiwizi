package Exceptions;

public class UnknownClassName extends Exception {

	public UnknownClassName(String className, String metamodel){
		super(className+ " is not a valid Class of metamodel "+ metamodel);
	}
	
}
