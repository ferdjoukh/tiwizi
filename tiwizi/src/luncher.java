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

	public static void main(String[] args) throws UnknownConfigFile, UnknownMetamodel, UnknownClassName, UnknownOperandForInequality, IOException {
		// TODO Auto-generated method stub
		
		
		//Args:
		// 0 >> options string -h, -g, -c, -cv
		// 1 >> metamodelFilePath
		// 2 >> rootClass
		// 3 >> configFilePath
		//
		// if option = "-h", print help
		// if no parameters are given, use default meta-model and quick check
		String metamodel = "",rootClass="",configFile="";
		boolean verbose=false;
		
		if(args.length==0){
			
			//if no parameters, lunch quick mode
			System.out.println("Quick test mode (requires: simpleHouse.ecore and House.grimm files)");
			System.out.println("");
			System.out.println("\ttip: type tiwizi -h for help");
			System.out.println("");
			
			metamodel = "simpleHouse.ecore";
			rootClass = "House";
			configFile = "House.grimm";
		
		}else if(args.length==1){
			
			//Help mode or problem
			if(args[0].equals("-h")){
				System.out.println("TIWIZI");
				System.out.println("\tis a Fault Localizer during model generation"
						+ "\n\tThe goal is to give you fixing suggestions to get a succesful generation.");
				
				System.out.println("");
				System.out.println("USE");
				System.out.println("\ttiwizi -[options string] meta-model rootClass configFile");
				
				System.out.println("");				
				System.out.println("PARAMETERS");
				
				System.out.println("\tall 4 parameters are mandatory");
				System.out.println("");				
				
				System.out.println("\t[-options]");
				System.out.println("\t\t-g generate a configuration file (filepath=configFile)");				
				System.out.println("\t\t-c check consistency with given meta-model, rootClass and configFile");
				System.out.println("\t\t-v Verbose mode. Create .tiwizi file containing all log information"
						+ "\n\t\t    and a pdf file gathering all fixing suggestions with syntax highlighting");				
				
				System.out.println("\tmeta-model");
				System.out.println("\t\tEcore meta-model filepath");
				
				System.out.println("\trootClass");
				System.out.println("\t\trootClass of given meta-model");
				
				System.out.println("\tconfigFile");
				System.out.println("\t\t.grimm configuration File");
				
				System.out.println("");				
								
				System.out.println("EXAMPLES of possible combinations");
				System.out.println("\ttiwizi");
				System.out.println("\ttiwizi -h");
				System.out.println("\ttiwizi -g cooking.ecore Kitchen cooking.grimm");
				System.out.println("\ttiwizi -c cooking.ecore Kitchen cooking.grimm");
				System.out.println("\ttiwizi -cv cooking.ecore Kitchen cooking.grimm");				
			
			}else{
				System.out.println("Unknown option");
				System.out.println("\ttip: type tiwizi -h for help");
			}
			return;
			
		}else if(args.length==4){
			//Decode option string contained in args[0]
			metamodel=args[1];
			rootClass=args[2];
			configFile=args[3];
			
			if(args[0].equals("-g")) {
				System.out.println("TIWIZI is generating a pre-filled configuration file...");
				
				//Call configFileGenerator
				ConfigFileGenerator cfg= new ConfigFileGenerator(metamodel, rootClass, configFile);
				cfg.generate();
				
				System.out.println("");
				System.out.println("["+configFile+"] was generated");
				
				return;
			}else if(args[0].equals("-c")){
				verbose = false;
				
			}else if(args[0].equals("-cv")){
				verbose=true;
				
			}else{
				System.out.println("Unknown option ("+args[0]+")");
				return;
			}
		}else{
			System.out.println("Inappropriate number of arguments (0, 1 or 4 parameters only)");
			return;
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
			System.out.println("TIWIZI is checking the consistency \n\t\tof ["+ metamodel+ "] metamodel\n\t\tand ["+ configFile +"] config file...");
			System.out.println("");
			InequalitySystemGenerator isg= new InequalitySystemGenerator(metamodel, rootClass, configFile);
			isg.createInequalitySystem();
			System.out.println("System of Linear Inequalities ("+ isg.getSystem().getNumberofLI() +" L.I.) has been generated...");			
			System.out.println("");
			ArrayList<MetProblem> problems= InequalitySystemChecker.checkAllSystem(isg.getSystem());
			
			if(problems.size()==0) {
				System.out.println("Success :) No anomaly found :)");
			}
			else {
				System.out.println("Failure :( Anomalies were detected :(");
				System.out.println("Fixing suggestions:");
				System.out.println("");
				System.out.println(InequalitySystemChecker.printMetProblems(problems));	
			}
			
			//For verbose mode
			if(verbose) {
				System.out.println("Verbose mode activated");
				System.out.println("");
				
				
				//Generate the log.tiwizi log file
				String logFilePath=InequalitySystemChecker.generateLogFile(problems);
				
				System.out.println("["+logFilePath+".tiwizi] has been generated");
				
				//Create .pdf file
				InequalitySystemChecker.createRecapPDF(logFilePath);
				
				System.out.println("["+logFilePath+".pdf] has been generated");
				
			}
		}
		
	}

}
