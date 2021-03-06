package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import linearProgramming.*;
import utils.MetProblem;

public class MetProblemTests {

	@Test
	public void createSingleTest(){
				
		Inequality equation = new Inequality("<=");
		equation.addVariable("Wall", 1.0);
		equation.addVariable("Room", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double) 5);
		candidate.add((double) 1);
		
		MetProblem problem= new MetProblem(equation,candidate);
		
		//System.out.println(problem.toString());
		
		assertEquals("More [Room] or Less [Wall]",problem.getMessage());	
	}
	
	@Test
	public void createSingleTest2(){
				
		Inequality equation = new Inequality("<");
		equation.addVariable("Wall", 1.0);
		equation.addVariable("Room", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double) 5);
		candidate.add((double) 1);
		
		MetProblem problem= new MetProblem(equation,candidate);
		
		//System.out.println(problem.toString());
		
		assertEquals("More [Room] or Less [Wall]",problem.getMessage());	
	}
	
	@Test
	public void createSingleTest3(){
				
		Inequality equation = new Inequality(">=");
		equation.addVariable("Wall", 1.0);
		equation.addVariable("Room", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double) 5);
		candidate.add((double) 1);
		
		MetProblem problem= new MetProblem(equation,candidate);
		
		//System.out.println(problem.toString());
		
		assertEquals("More [Wall] or Less [Room]",problem.getMessage());	
	}

	@Test
	public void createSingleTest4(){
				
		Inequality equation = new Inequality(">");
		equation.addVariable("Wall", 1.0);
		equation.addVariable("Room", -4.0);
		
		ArrayList<Double> candidate= new ArrayList<Double>();
		candidate.add((double) 5);
		candidate.add((double) 1);
		
		MetProblem problem= new MetProblem(equation,candidate);
		
		//System.out.println(problem.toString());
		
		assertEquals("More [Wall] or Less [Room]",problem.getMessage());	
	}
	
}
