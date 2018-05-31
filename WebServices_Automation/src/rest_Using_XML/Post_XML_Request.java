package rest_Using_XML;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Post_XML_Request {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	String placeID;
	
	public static String generateString(String fileName) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Payloads\\" + fileName;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	@Test
	public void Add_Place() throws IOException {
		
		RestAssured.baseURI = baseURI;
		
		String requestBody = generateString("POST_XML_Payload.xml");
		
					Response res =		
					given().
					queryParam("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
					body(requestBody).
			
					when().
						post("/maps/api/place/add/xml").
			
					then().
						assertThat().statusCode(200).
							and().contentType(ContentType.XML).
//								log().body().
				
					extract().response();
					String response = res.asString();
					System.out.println(response);
					
					XmlPath xmlResponse = new XmlPath(response);
					placeID = xmlResponse.get("PlaceAddResponse.place_id");
					System.out.println("********************");
					System.out.println("Place id: " + placeID);
	}
}