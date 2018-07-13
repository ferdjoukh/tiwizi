import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.UnknownClassName;
import Exceptions.UnknownConfigFile;
import Exceptions.UnknownMetamodel;
import Exceptions.UnknownOperandForInequality;
import linearProgramming.InequalitySystemChecker;
import linearProgramming.InequalitySystemGenerator;
import utils.*;

public class luncher {

	public static void main(String[] args) throws UnknownConfigFile, UnknownMetamodel, UnknownClassName, UnknownOperandForInequality {
		// TODO Auto-generated method stub
		
		//Give meta-model file path
		//Give rootClass
		//Give configFile path
		String metamodel = "",rootClass="",configFile="";
		
		//if args are given use them, otherwise use default ones
		if(args.length==0){
			metamodel = "model/simpleHouse.ecore";
			rootClass = "House";
			configFile = "confFiles/House1.grimm";
		}else if(args.length==3){
			metamodel=args[0];
			rootClass=args[1];
			configFile=args[2];
			
		}else{
			System.out.println("Use the appropriate number of arguments (3)");
		}
		
		//Check the existance of given files
		Boolean metamodelExists= new File(metamodel).isFile();
		Boolean configFileExists= new File(configFile).isFile();
		
		if(!metamodelExists || !configFileExists){
			if(!metamodelExists)
				throw new UnknownMetamodel(metamodel);
			
			if(!configFileExists)
				throw new UnknownConfigFile(configFile);
		}else{					
			InequalitySystemGenerator isg= new InequalitySystemGenerator(metamodel, rootClass, configFile);
			isg.createInequalitySystem();
			ArrayList<MetProblem> problems= InequalitySystemChecker.checkAllSystem(isg.getSystem());
			System.out.println(InequalitySystemChecker.printMetProblems(problems));			
		}
		
	}

}
