package get_Request_Sample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Get_Request {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	@Test
	public void verifyResponse() {
		
		RestAssured.baseURI = baseURI;
		
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
				and().body("results[0].id", 
						equalTo("aafc9463e0c2b6810ad0160a89db7e859d93d7bc")).
				and().body("results[0].name", 
						equalTo("Toscanini Restaurant")).
				and().body("results[0].rating", 
						equalTo(4)).
				log().all();
		
		System.out.println("Request is executed successfully.");
	}

}