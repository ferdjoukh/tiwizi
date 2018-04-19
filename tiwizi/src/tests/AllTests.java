package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InequalitySystemCheckerTest.class, InequalityTests.class, 
	MetProblemTests.class, InequalitySystemGeneratorTests.class, solverHouseMetamodelTests.class })
public class AllTests {

}
