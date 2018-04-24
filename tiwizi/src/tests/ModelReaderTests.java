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
	public void countNumberOfBiDirectionRefs() throws UnknownConfigFile, UnknownMetamodel, UnknownClassName{
		ConfigFileReader cfr= new ConfigFileReader("confFiles/House1.grimm");
		ModelReader reader = new ModelReader("model/simpleHouse.ecore", "House", cfr);
		
		EClass houseClass= reader.getClassByName("House");
		
		assertEquals(2,reader.getAllReferencesFromClass(houseClass));
	}
}
