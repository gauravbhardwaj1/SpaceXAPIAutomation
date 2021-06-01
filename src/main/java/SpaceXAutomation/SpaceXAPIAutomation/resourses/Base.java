package SpaceXAutomation.SpaceXAPIAutomation.resourses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {
	
	String propFile = "src/main/java/SpaceXAutomation/SpaceXAPIAutomation/resourses/data.properties" ;
	
	public String setURI() throws IOException
	{
		String baseURI ="";
		
		baseURI = Utilities.findPropAttrVal(propFile,"baseU" );
		
		System.out.println("Current URI - " + baseURI);
		
		return baseURI;
	}
	
	public String setResources() throws IOException
	{
		String baseResources ="";
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(propFile);
		
		prop.load(fis);
		
		baseResources = prop.getProperty("baseU");
		
		System.out.println("Current resource - " + baseResources);
		
		return baseResources;
	}

}
