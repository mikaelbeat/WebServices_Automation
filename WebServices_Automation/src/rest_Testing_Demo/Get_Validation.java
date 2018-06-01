package rest_Testing_Demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.testng.annotations.Test;

public class Get_Validation {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	// Response from server
	String response = null;
	
	// Define file where response is saved
	String response_file = "C:\\Users\\petri.ryynanen\\git\\WebServices_Automation\\WebServices_Automation\\Responses\\Get_Validation_Response.json";
	File file = new File(response_file);
	
	// Define file where expected response is located
	String expected_file = "C:\\Users\\petri.ryynanen\\git\\WebServices_Automation\\WebServices_Automation\\Expected\\Expected.json";
	
	@Test
	public void Validate() throws IOException {
		
		FileWriter fw = new FileWriter(file);
		
				RestAssured.baseURI = baseURI;
		
		Response res = 
		given().
			param("location", "60.1664482,24.9423437").
			param("radius", "50").
			param("type", "restaurant").
			param("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
			
			when().
			get("/maps/api/place/nearbysearch/json").
			
			then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				
			extract().response();
				response = res.asString();
				JsonPath jsonResponse = new JsonPath(response);
				fw.write(response);
				fw.close();
//				Compare_Results();
				assertEquals(response_file, expected_file);

	}
	
	public void Compare_Results() throws IOException {
		
		boolean areFilesIdentical = true;
	    File response = new File("C:\\Users\\petri.ryynanen\\git\\WebServices_Automation\\WebServices_Automation\\Responses\\Response.json");
	    File expected = new File("C:\\Users\\petri.ryynanen\\git\\WebServices_Automation\\WebServices_Automation\\Expected\\Expected.json");
	    FileInputStream fis1 = new FileInputStream(response);
	    FileInputStream fis2 = new FileInputStream(expected);
	    int i1 = fis1.read();
	    int i2 = fis2.read();
	    while (i1 != -1) {
	      if (i1 != i2) {
	        areFilesIdentical = false;
	        break;
	      }
	      i1 = fis1.read();
	      i2 = fis2.read();
	    }
	    fis1.close();
	    fis2.close();
	    System.out.println(areFilesIdentical);
	  }
		
	}