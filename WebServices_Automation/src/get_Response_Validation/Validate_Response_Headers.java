package get_Response_Validation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class Validate_Response_Headers {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	@Test
	public void Validate() {
		
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
				and().header("Cache-Control", "public, max-age=300").
				and().header("Content-Encoding", "gzip").
				and().header("Server", "scaffolding on HTTPServer2").
				log().headers();
		
		System.out.println("Request is executed successfully.");
	}

}