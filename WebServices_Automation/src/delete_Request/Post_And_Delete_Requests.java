package delete_Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class Post_And_Delete_Requests {
	
	 // BaseURI
	static String baseURI = "https://maps.googleapis.com";
	
	String placeID;
	
	@Test
	public void Add_Place() {
		
		RestAssured.baseURI = baseURI;
		
		String requestBody = "{"+
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
			"}";
		
Response res =	given().
					queryParam("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU").
					body(requestBody).
			
					when().
						post("/maps/api/place/add/json").
			
					then().
						assertThat().statusCode(200).
							and().contentType(ContentType.JSON).
//								log().body().
				
					extract().response();
					String response = res.asString();
					System.out.println(response);
					JsonPath jsonResponse = new JsonPath(response);
					placeID = jsonResponse.get("place_id");
					System.out.println("Place id:" + placeID);
	}
	
	@Test
	public void Delete_Place() {
		
		given().
			queryParam("key", "AIzaSyBTVghLd1ATeqSb0z-7zuWgi2rsPAgElIU")
			.body("{"+
					"\"place_id\": \""+placeID+"\"" +
					"}").
			
		when().post("/maps/api/place/delete/json").
		
		then().assertThat().statusCode(200).
			and().body("status", equalTo("OK"));
		
	}

}