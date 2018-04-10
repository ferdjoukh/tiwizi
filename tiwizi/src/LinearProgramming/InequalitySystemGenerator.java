package LinearProgramming;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import GrimmUtils.ConfigFileReader;
import GrimmUtils.ModelReader;

public class InequalitySystemGenerator {

	private String metamodel;
	private String rootClass;
	private String configFile;
	private ConfigFileReader configreader;
	private ModelReader modelreader;
	ArrayList<String> inequalities= new ArrayList<String>();
	
	public InequalitySystemGenerator(String metamodel, String rootClass, String configFile) throws IOException {
		super();
		this.metamodel = metamodel;
		this.rootClass = rootClass;
		this.configFile = configFile;
		
		configreader= new ConfigFileReader(configFile);
		configreader.read();
		modelreader = new ModelReader(metamodel, rootClass, configreader);
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
