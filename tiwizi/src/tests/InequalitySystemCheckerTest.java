package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import linearProgramming.Inequality;
import linearProgramming.InequalitySystem;
import linearProgramming.InequalitySystemChecker;

public class InequalitySystemCheckerTest {
	
	//Method: solveInequality
	
	@Test
	public void createFirstChecker(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("Wall", 4);
		equation.addVariable("Room", -1);
		InequalitySystem ineqSystem= new InequalitySystem();
		ineqSystem.addInequality(equation);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(1);
		candidate.add(2);
				
		int a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("4*1 -1*2 = 2",2,a);	
	}
	
	public void OneMoreExample(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("H", 0);
		equation.addVariable("R", -1);
		InequalitySystem ineqSystem= new InequalitySystem();
		ineqSystem.addInequality(equation);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(1);
		candidate.add(2);
				
		int a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("0*H - 1*R <= 0 [1,2]",-2,a);		
	}
	
	public void OneMoreExample2(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("R", 1);
		equation.addVariable("H", -4);
		InequalitySystem ineqSystem= new InequalitySystem();
		ineqSystem.addInequality(equation);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(1);
		candidate.add(2);
				
		int a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("1*R - 4*H <= 0 [1,2]",-7,a);
	}
}
