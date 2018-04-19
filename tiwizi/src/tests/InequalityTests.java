package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import linearProgramming.Inequality;

public class InequalityTests {

	@Test
	public void createEmptyEquation() {
		Inequality equation= new Inequality("<=");
		assertEquals("Empty equation", " <= 0", equation.toString());
	}
	
	@Test
	public void CreateOneUnknownEquation(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("Room", 2);
		assertEquals("2.0*Room <= 0", equation.toString());
	}
	
	@Test 
	public void CreateTwoVariablePositiveNegative(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("House", 0);
		equation.addVariable("Room", -1);
		assertEquals("0.0*House -1.0*Room <= 0", equation.toString());
	}
	
	@Test 
	public void CreateTwoVariablePositivePositive(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("House", 1);
		equation.addVariable("Room", 1);
		assertEquals("1.0*House +1.0*Room <= 0", equation.toString());
	}
	
	@Test 
	public void CreateTwoVariableNegativeNegative(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("House", -1);
		equation.addVariable("Room", -1);
		assertEquals("-1.0*House -1.0*Room <= 0", equation.toString());
	}
	
	@Test 
	public void CreateThreeVariables(){
		Inequality equation = new Inequality("<=");
		equation.addVariable("Room1", 1);
		equation.addVariable("Room2", 1);
		equation.addVariable("House", -2);
		assertEquals("1.0*Room1 +1.0*Room2 -2.0*House <= 0", equation.toString());
	}

}
