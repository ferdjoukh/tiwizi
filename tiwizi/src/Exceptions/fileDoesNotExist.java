package Exceptions;

import java.io.IOException;

public class fileDoesNotExist extends IOException{
	
	public fileDoesNotExist(String givenPath){ super(givenPath+" is not a valid file path");}

}
