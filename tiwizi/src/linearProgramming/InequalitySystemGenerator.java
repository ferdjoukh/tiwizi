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
	
	public InequalitySystemGenerator(String metamodel, String rootClass, String configFile) throws UnknownMetamodel, UnknownConfigFile, UnknownClassName {
		
		this.configFile = configFile;
		configreader= new ConfigFileReader(configFile);
		configreader.read();
				
		this.metamodel = metamodel;
		this.rootClass = rootClass;
		modelreader = new ModelReader(metamodel, rootClass, configreader);
		
		system=new InequalitySystem();
		classSizes= modelreader.getClassSize();
		
		System.out.println(classSizes.toString());
	}
		
	public void fillinequalities(){
		
		for(EClass c: modelreader.getClasses()){
			
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
}
