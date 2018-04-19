package tests;

import java.util.ArrayList;

import org.junit.*;
import org.junit.Assert;

import Exceptions.UnknownClassName;
import Exceptions.UnknownConfigFile;
import Exceptions.UnknownMetamodel;
import Exceptions.UnknownOperandForInequality;
import linearProgramming.InequalitySystemChecker;
import linearProgramming.InequalitySystemGenerator;
import utils.MetProblem;

public class solverHouseMetamodelTests {

	@Test
	public void correctInstance() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName, UnknownOperandForInequality{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("model/simpleHouse.ecore", "House", "confFiles/House1.grimm");
		isg.createInequalitySystem();
		
		ArrayList<MetProblem> problems= InequalitySystemChecker.checkAllSystem(isg.getSystem());
		System.out.println(InequalitySystemChecker.printMetProblems(problems));
		
	}
}
