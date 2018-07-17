package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

public class ConfigFileGenerator {

	
	private String mm;
	private String rootClass;
	private String configFile;
	private ModelReader modelReader;
	
	public ConfigFileGenerator(String mm, String rootClass)
	{
		this.mm = mm;
		this.rootClass = rootClass;	
		this.configFile= rootClass+"/"+rootClass+".grimm";
	}
	
	public ConfigFileGenerator(String mm, String rootClass, String configFile)
	{
		this.mm = mm;
		this.rootClass = rootClass;
		this.configFile= configFile;
	}
	
	public void generate() throws IOException
	{
		modelReader = new ModelReader(mm, rootClass, 2, 2);
		new File(rootClass).mkdir();
		PrintWriter ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(this.configFile)));
		
		ecrivain.write("%This is a configuration file for TIWIZI Tool \n");
		ecrivain.write("%Please do not change the ordering or the name of any element !\n");
		ecrivain.write("%Replace all 0 by positive integer values\n");
		ecrivain.write("%-------------------------------------------------------------\n");
		ecrivain.write("%-------------------------------------------------------------\n");
		ecrivain.write("%Number of instances for Classes \n");
		ecrivain.write("%-------------------------------------------------------------\n");
				
		ArrayList<EClass> cls= new ArrayList<EClass>();
		cls= (ArrayList<EClass>) modelReader.getClasses();
		for(EClass c: cls)
		{
			String name= c.getName();
			if (name.compareTo(rootClass)!=0)
			ecrivain.write(name+"=0\n");
		}
		
		ecrivain.write("%-------------------------------------------------------------\n");
		ecrivain.write("%-------------------------------------------------------------\n");
		ecrivain.write("%Unbounded References\n");
		ecrivain.write("%-------------------------------------------------------------\n");
		ecrivain.write("RefsBound=0\n");
				
		ecrivain.close();
		
	}
}
