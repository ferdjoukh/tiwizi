package Exceptions;

public class UnknownConfigFile extends Exception {
	
	public UnknownConfigFile(String configFile){
		
		super(configFile + " is not a valid ConfigurationFile");
	}

}
