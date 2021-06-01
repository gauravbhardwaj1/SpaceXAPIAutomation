package SpaceXAutomation.SpaceXAPIAutomation.resourses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;

public class Utilities {

	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}
	
	public static String findPropAttrVal(String filePath,String attribut) throws IOException
	{
		String val = "";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		
		prop.load(fis);
		
		val = prop.getProperty(attribut);
		
		return val;
	}
}
