package post_Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class Post_Request {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	@Test
	public void Validate() {
		
		RestAssured.baseURI = baseURI;
		
		given().
			queryParam("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
			body("{"+
					"\"location\": { "+
					"\"lat\": -33.866971123445,"+
					"\"lng\": 151.1958750"+
				"},"+
					"\"accuracy\": 50,"+
					"\"name\": \"Google Shoes!\","+
					"\"phone_number\": \"(02) 9374 4000\","+
					"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
					"\"types\": [\"shoe_store\"],"+
					"\"website\": \"http://www.google.com.au/\","+
					"\"language\": \"en-AU\""+
				"}").
			
			when().
			post("/maps/api/place/add/json").
			
			then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				and().body("status", equalTo("OK")).
				log().body();
		
		System.out.println("Request is executed successfully.");
	}

}