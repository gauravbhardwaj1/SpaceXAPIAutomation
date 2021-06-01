package SpaceXAutomation.SpaceXAPIAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SpaceXAutomation.SpaceXAPIAutomation.resourses.Base;
import SpaceXAutomation.SpaceXAPIAutomation.resourses.Utilities;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyLatestAPI extends Base{
	
	private static final Boolean True = null;

	String propFile = "src/main/java/SpaceXAutomation/SpaceXAPIAutomation/resourses/data.properties" ;

	@Test
	public void verifyStatus() throws IOException
	{
		int expectedStatusCode = 200 ;
		
		RestAssured.baseURI =setURI();
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res = httpRequest.request(Method.GET, "/latest");
	
		
		//Status code
		int statusCode = res.getStatusCode();
		
		System.out.println("Status Code is  - " + statusCode);
		Assert.assertEquals(statusCode, expectedStatusCode);
		
		System.out.println(res.asString());
		
	}
	
	@Test(dependsOnMethods= {"verifyStatus"})
	public void verifyHeaders() throws IOException
	{
		//String expectedContentType = "application/json; charset=utf-8" ;
		//String expectedServer = "Caddy";
		
		String expectedContentType = Utilities.findPropAttrVal(propFile,"contentType" );
		
		String expectedServer = Utilities.findPropAttrVal(propFile,"server" );
		
		RestAssured.baseURI =setURI();
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res = httpRequest.request(Method.GET, "/latest");
		
		System.out.println(res.getHeaders());
		//Verify content type
		String content = res.getHeader("Content-Type") ;
		
		Assert.assertTrue(content.contains(expectedContentType), "Failed in verifying Content-type");
		
	
		// Verify Server
		String servr = res.getHeader("Server");
		
		System.out.println(servr);
		Assert.assertEquals(servr, expectedServer, "Failed in verifying server");
		
		
		System.out.println(res.getHeaders().toString());
		
	}
	
	
	@Test(dependsOnMethods= {"verifyStatus"})
	public void verifyBody() throws IOException
	{
		
		String expectedReused = "true";
		
		String expectedSuccess = "true";
		
		int expectedFlight = 2 ;
		
		RestAssured.baseURI =setURI();
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res = httpRequest.request(Method.GET, "/latest");
		
		JsonPath js= Utilities.rawToJson(res.asString());
		
		
		// Verify fairings-reused attribute
		
		String isReused =js.getString("fairings.reused") ;
		
		Assert.assertEquals(isReused, expectedReused);  
		
		// Verify success attribute
		String isSuccess = js.getString("success") ;
		AssertJUnit.assertEquals(isSuccess, expectedSuccess);
		
		
		int actualFlight = js.getInt("cores[0].flight");
		
		AssertJUnit.assertEquals(actualFlight, expectedFlight);
		
		
	}

}

