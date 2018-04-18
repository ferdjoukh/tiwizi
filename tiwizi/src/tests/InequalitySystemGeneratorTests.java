package tests;

import org.junit.Test;

import Exceptions.UnknownConfigFile;
import Exceptions.UnknownMetamodel;
import Exceptions.UnknownClassName;
import linearProgramming.InequalitySystemGenerator;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;

public class InequalitySystemGeneratorTests {
	
	@Test(expected = UnknownConfigFile.class)
	public void configFileDoesNotExist() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("metamodel","rootClass","ConfigFile");
	}
	
	@Test(expected = UnknownMetamodel.class)
	public void metamodelDoesNotExist() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("izan", "ddd", "confFiles/House1.grimm");
	}
	
	@Test(expected = UnknownClassName.class)
	public void RootClassDoesNotExist() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("model/simpleHouse.ecore", "ddd", "confFiles/House1.grimm");
	}
	
	@Test
	public void classSizesFirstExampleVerfied() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("model/simpleHouse.ecore", "House", "confFiles/House1.grimm");
		
		assertEquals(3,isg.getClassSizes().size());
		
	}
	
	@Test
	public void HouseInequalitySystem1() throws UnknownMetamodel, UnknownConfigFile, UnknownClassName{
		
		InequalitySystemGenerator isg= new InequalitySystemGenerator("model/simpleHouse.ecore", "House", "confFiles/House1.grimm");
		isg.createInequalitySystem();
		
		System.out.println(isg.getSystem().printInequalitySystem(1));
	}

}
