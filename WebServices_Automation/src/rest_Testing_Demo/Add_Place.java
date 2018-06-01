package rest_Testing_Demo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Add_Place {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	String placeID;
	
	String response = null;
	
	public static String getRequest(String fileName) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Payloads\\" + fileName;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	public static String getResponse(String fileName) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Responses\\" + fileName;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	public static String getExpected(String fileName) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Expected\\" + fileName;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	// Define file where response is saved
	String response_file = "C:\\Users\\petri.ryynanen\\git\\WebServices_Automation\\WebServices_Automation\\Responses\\Add_Place_Response.json";
	File file = new File(response_file);
	
	@Test
	public void Add_Place_Test() throws IOException {
		
		RestAssured.baseURI = baseURI;
		FileWriter fw = new FileWriter(file);
		
		String requestBody = getRequest("Add_Place.json");
		String expectedBody = getExpected("Add_Place_Expected.json");
		
					Response res =		
					given().
					queryParam("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
					body(requestBody).
			
					when().
						post("/maps/api/place/add/json").
			
					then().
						assertThat().statusCode(200).
							and().contentType(ContentType.JSON).
//								log().body().
				
					extract().response();
						response = res.asString();
						fw.write(response);
						fw.close();
						String responseBody = getResponse("Add_Place_Response.json");
						assertEquals(responseBody, expectedBody);


	}
}