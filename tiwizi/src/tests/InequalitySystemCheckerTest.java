package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import linearProgramming.Inequality;
import linearProgramming.InequalitySystem;
import linearProgramming.InequalitySystemChecker;
import linearProgramming.UnknownOperandForInequality;

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
	
	@Test
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
	
	@Test
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
	
	//Method: isInequalityConsistent
	
	@Test
	public void lessOrEqualTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("<=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(1);
		candidate.add(2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		assertEquals(true, res);
	}
	
	@Test(expected = UnknownOperandForInequality.class)
	public void unknownOperand() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("ww");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(1);
		candidate.add(2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
	}
	
	@Test
	public void lessOrEqualFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("<=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(3);
		candidate.add(1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
		
		assertEquals(false, res);
	}
	
	@Test
	public void greaterOrEqualTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality(">=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(2);
		candidate.add(1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
				
		assertEquals(true, res);
	}
	
	@Test
	public void greaterOrEqualFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality(">=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(2);
		candidate.add(2);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
				
		assertEquals(false, res);
	}
	
	@Test
	public void equalTrue() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(2);
		candidate.add(1);
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
				
		assertEquals(true, res);
	}
	
	@Test
	public void equalFalse() throws UnknownOperandForInequality{
		
		Inequality equation = new Inequality("=");
		equation.addVariable("x", 2);
		equation.addVariable("y", -4);
		
		ArrayList<Integer> candidate= new ArrayList<Integer>();
		candidate.add(2);
		candidate.add(2);
		
		
		
		boolean res= InequalitySystemChecker.isInequalityConsistent(equation, candidate);
		
		System.out.println(equation.toString()+" "+ candidate.toString() +" >> "+ res);
		
		assertEquals(false, res);
	}
	
	@Test 
	public void checkWholeSystem(){
		
		
		
	}
	
}
