package tests;

import static org.junit.Assert.*;

import org.eclipse.emf.ecore.EClass;
import org.junit.*;
import org.junit.Assert;

import Exceptions.UnknownClassName;
import Exceptions.UnknownConfigFile;
import Exceptions.UnknownMetamodel;
import utils.ConfigFileReader;
import utils.ModelReader;

public class ModelReaderTests {

	@Test
	public void countRefsHouseClass() throws UnknownConfigFile, UnknownMetamodel, UnknownClassName{
		ConfigFileReader cfr= new ConfigFileReader("confFiles/House1.grimm");
		cfr.read();
		ModelReader reader = new ModelReader("model/simpleHouse.ecore", "House", cfr);
		
		EClass houseClass= reader.getClassByName("House");
		
		assertEquals(2,reader.getUniDirectionRefsFromClass(houseClass).size());
		assertEquals("room",reader.getUniDirectionRefsFromClass(houseClass).get(0).getName());
		assertEquals("wall",reader.getUniDirectionRefsFromClass(houseClass).get(1).getName());
		assertEquals(0,reader.getBiDirectionRefsFromClass(houseClass).size());
	}
	
	@Test
	public void countRefsRoomClass() throws UnknownConfigFile, UnknownMetamodel, UnknownClassName{
		ConfigFileReader cfr= new ConfigFileReader("confFiles/House1.grimm");
		cfr.read();
		ModelReader reader = new ModelReader("model/simpleHouse.ecore", "House", cfr);
		
		EClass houseClass= reader.getClassByName("Room");
		
		assertEquals(0,reader.getUniDirectionRefsFromClass(houseClass).size());
		assertEquals(1,reader.getBiDirectionRefsFromClass(houseClass).size());
		assertEquals("wallinRoom",reader.getBiDirectionRefsFromClass(houseClass).get(0).getName());
		
	}
}
