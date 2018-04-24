package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Exceptions.UnknownOperandForInequality;
import linearProgramming.Inequality;
import linearProgramming.InequalitySystem;
import linearProgramming.InequalitySystemChecker;
import utils.MetProblem;

public class InequalitySystemCheckerTest {
	
	//Method: solveInequality
	
	@Test
	public void createFirstChecker(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("Wall", 4.0);
		equation.addVariable("Room", -1.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)1);
		candidate.add((double)2);
				
		double a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("4*1 -1*2 = 2",2,a,0.000001);	
	}
	
	@Test
	public void OneMoreExample(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("H", 0.0);
		equation.addVariable("R", -1.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)1);
		candidate.add((double)2);
				
		double a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("0*H - 1*R <= 0 [1,2]",-2,a,0.00001);		
	}
	
	@Test
	public void OneMoreExample2(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("R", 1.0);
		equation.addVariable("H", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)1);
		candidate.add((double)2);
				
		double a=InequalitySystemChecker.solveInequality(equation,candidate);
		assertEquals("1*R - 4*H <= 0 [1,2]",-7.0,a,0.00001);
	}
	
	//Method: isInequalityConsistent
	
	@Test
	public void lessOrEqualTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("<=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)1);
		candidate.add((double)2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		assertEquals(true, res);
	}
	
	@Test(expected = UnknownOperandForInequality.class)
	public void unknownOperand() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("ww");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)1);
		candidate.add((double)2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
	}
	
	@Test
	public void lessOrEqualFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("<=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)3);
		candidate.add((double)1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		
		assertEquals(false, res);
	}
	
	@Test
	public void greaterOrEqualTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality(">=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)2);
		candidate.add((double)1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
				
		assertEquals(true, res);
	}
	
	@Test
	public void greaterOrEqualFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality(">=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)2);
		candidate.add((double)2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
				
		assertEquals(false, res);
	}
	
	@Test
	public void equalTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)2);
		candidate.add((double)1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		//System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
				
		assertEquals(true, res);
	}
	
	@Test
	public void equalFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("=");
		equation.addVariable("x", 2.0);
		equation.addVariable("y", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)2);
		candidate.add((double)2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
				
		assertEquals(false, res);
	}
	
	@Test 
	public void checkWholeSystem() throws UnknownOperandForInequality{
		Inequality equation = new Inequality("<=");
		equation.addVariable("Wall", 1.0);
		equation.addVariable("Door", 2.0);
		equation.addVariable("Room", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double)5);
		candidate.add((double)2);
		candidate.add((double)3);
		
		Inequality equation2 = new Inequality("<=");
		equation2.addVariable("Room", 1.0);
		equation2.addVariable("House", -4.0);
		
		ArrayList<Double> candidate2= new ArrayList<Double>();
		candidate2.add((double)5);
		candidate2.add((double)1);
		
		Inequality equation3 = new Inequality("<=");
		equation3.addVariable("House", 4.0);
		equation3.addVariable("Wall", -1.0);
				
		ArrayList<Double> candidate3= new ArrayList<Double>();
		candidate3.add((double)2);
		candidate3.add((double)8);
		
		InequalitySystem system= new InequalitySystem();
		system.addInequalityCandidate(equation, candidate);
		system.addInequalityCandidate(equation2, candidate2);
		system.addInequalityCandidate(equation3, candidate3);
		
		ArrayList<MetProblem> problems=InequalitySystemChecker.checkAllSystem(system);
		/*
		for(MetProblem problem: problems){
			System.out.println(problem.toString());
		}
		*/
				
		assertEquals(1, problems.size());	
	}
	
}
