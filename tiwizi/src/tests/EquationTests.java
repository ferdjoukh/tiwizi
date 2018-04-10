package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import linearProgramming.Equation;

public class EquationTests {

	@Test
	public void createEmptyEquation() {
		Equation equation= new Equation("<=");
		assertEquals("Empty equation", " <= 0", equation.toString());
	}
	
	@Test
	public void CreateOneUnknownEquation(){
		Equation equation = new Equation("<=");
		equation.addVariable("Room", 2);
		assertEquals("2*Room <= 0", equation.toString());
	}

}
