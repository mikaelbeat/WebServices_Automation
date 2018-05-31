package post_Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class Parsing_JSON {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	String response = null;
	int arrSize = 0;
	
	@Test
	public void Validate() {
		
		RestAssured.baseURI = baseURI;
		
		Response res = 
		given().
			param("location", "60.1664482,24.9423437").
			param("radius", "100").
			param("type", "restaurant").
			param("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
			
			when().
			get("/maps/api/place/nearbysearch/json").
			
			then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				
			extract().response();
		
		response = res.asString();
		JsonPath jsonRes = new JsonPath(response);
		arrSize = jsonRes.getInt("results.size()");
		System.out.println(arrSize);
		
		for (int i = 0; i < arrSize; i++) {
			String name = jsonRes.getString("results["+i+"].name");
			System.out.println(name);
		}
		
				
			
		
	}

}