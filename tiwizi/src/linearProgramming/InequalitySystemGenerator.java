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
	
	private ArrayList<String> inequalities= new ArrayList<String>();
	
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
			
			int n= modelreader.getAllReferencesFromClasswithOpposite(c).size();
			System.out.println(n);
			
			System.out.println(c.getName());
			for (EReference r: modelreader.getAllReferencesFromClass(c)){
				System.out.println("    "+r.getName());
				
				int ub= configreader.getRefsBound();
				if(r.getUpperBound()!=-1){
					ub=r.getUpperBound();
				}
				
				String inlower= r.getLowerBound()+"*"+c.getName()+" <= "+ r.getEType().getName();
				String inUpper= r.getEType().getName() + " <= "+ ub+"*"+c.getName();
				inequalities.add(inlower);
				inequalities.add(inUpper);
				
				Inequality inequality= new Inequality("<=");
				inequality.addVariable(c.getName(), r.getLowerBound());
				inequality.addVariable(r.getEType().getName(), -1);
				
				ArrayList<Double> candidates= new ArrayList<Double>();
				int candidateValue= classSizes.get(modelreader.getClassIndex(c.getName())-1);
				candidates.add((double) candidateValue);
				
				candidateValue= classSizes.get(modelreader.getClassIndex(r.getEType().getName())-1);
				candidates.add((double) candidateValue);
				
				Inequality inequality1= new Inequality("<=");
				inequality1.addVariable(r.getEType().getName(), 1);
				inequality1.addVariable(c.getName(), -ub);
				
				ArrayList<Double> candidates1= new ArrayList<Double>();
				candidateValue= classSizes.get(modelreader.getClassIndex(r.getEType().getName())-1);
				candidates1.add((double) candidateValue);
	
				candidateValue= classSizes.get(modelreader.getClassIndex(c.getName())-1);		
				candidates1.add((double) candidateValue);
				
				system.addInequalityCandidate(inequality, candidates);
				system.addInequalityCandidate(inequality1, candidates1);
				
				System.out.println("          "+inequality.toString());
				System.out.println("          "+inequality1.toString());
				
			}
		}
		
	}
				
	public String toString(){
		
		String res="Inequality System:";
		for( String s: inequalities){
			res=res+"\n"+s;
		}
		return res;
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
