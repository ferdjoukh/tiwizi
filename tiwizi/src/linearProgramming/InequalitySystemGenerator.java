package linearProgramming;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import Exceptions.UnknownClassName;
import Exceptions.UnknownConfigFile;
import Exceptions.UnknownMetamodel;
import utils.ConfigFileReader;
import utils.ModelReader;

public class InequalitySystemGenerator {

	private String metamodel;
	private String rootClass;
	private String configFile;
	private ConfigFileReader configreader;
	private ModelReader modelreader;
	private ArrayList<Integer> classSizes;
	private InequalitySystem system;
	
	//private ArrayList<String> inequalities= new ArrayList<String>();
	
	/**
	 * It creates an object of type InequalitySystemGenerator.
	 * This requires an existing metamodel file, a valid root class and an existing config file 
	 *
	 * 
	 * @param metamodel file path for an Ecore Metamodel
	 * @param rootClass the root class of previous metamodel
	 * @param configFile a .grimm configuration file
	 * @throws UnknownMetamodel
	 * @throws UnknownConfigFile
	 * @throws UnknownClassName
	 */
	public InequalitySystemGenerator(String metamodel, String rootClass, String configFile) throws UnknownMetamodel, UnknownConfigFile, UnknownClassName {
		
		this.configFile = configFile;
		configreader= new ConfigFileReader(configFile);
		configreader.read();
				
		this.metamodel = metamodel;
		this.rootClass = rootClass;
		modelreader = new ModelReader(metamodel, rootClass, configreader);
		
		system=new InequalitySystem();
		classSizes= modelreader.getClassSize();
	}
		
	/**
	 * The main method of this class. It creates the inequality system
	 * and the candidate values for each inequality
	 * 
	 */
	public void createInequalitySystem(){
		
		for(EClass c: modelreader.getClasses()){
						
			System.out.println(c.getName());
			
			//Create Inequalities for unidirectional references
			for (EReference r: modelreader.getUniDirectionRefsFromClass(c)){
				System.out.println("    "+r.getName());
				
				int ub= configreader.getRefsBound();
				if(r.getUpperBound()!=-1){
					ub=r.getUpperBound();
				}
				
				Inequality inequality=  createInequality("<=",c.getName(), (double) r.getLowerBound(),r.getEType().getName(), (double) -1);
				ArrayList<Double> candidates= createCandidates((double) classSizes.get(modelreader.getClassIndex(c.getName())-1), 
									(double) classSizes.get(modelreader.getClassIndex(r.getEType().getName())-1));
				
				Inequality inequality1= createInequality("<=",r.getEType().getName(), (double) 1,c.getName(), (double) -ub);
				
				ArrayList<Double> candidates1= createCandidates(
						(double) classSizes.get(modelreader.getClassIndex(r.getEType().getName())-1),
						(double) classSizes.get(modelreader.getClassIndex(c.getName())-1));
				
				system.addInequalityCandidate(inequality, candidates);
				system.addInequalityCandidate(inequality1, candidates1);
				
				System.out.println("          "+inequality.toString());
				System.out.println("          "+inequality1.toString());	
			}
			
			//Create Inequalities for BiDirectional references
			for (EReference r: modelreader.getBiDirectionRefsFromClass(c)){
				System.out.println("    "+r.getName());
			
			}
			
		}
	}
	
	private Inequality createInequality(String operand, String var1, Double coeff1, String var2, Double coeff2){
		
		Inequality inequality = new Inequality(operand);
		inequality.addVariable(var1, coeff1);
		inequality.addVariable(var1, coeff1);
				
		return inequality;
	}
	
	private ArrayList<Double> createCandidates(Double candidate1, Double candidate2){
		
		ArrayList<Double> candidates= new ArrayList<Double>();
		candidates.add(candidate1);
		candidates.add(candidate2);
		
		return candidates;
	}
				
	public String getMetamodel() {
		return metamodel;
	}

	public String getRootClass() {
		return rootClass;
	}

	public String getConfigFile() {
		return configFile;
	}

	public ConfigFileReader getConfigreader() {
		return configreader;
	}

	public ModelReader getModelreader() {
		return modelreader;
	}

	public ArrayList<Integer> getClassSizes() {
		return classSizes;
	}

	public InequalitySystem getSystem() {
		return system;
	}	
}
