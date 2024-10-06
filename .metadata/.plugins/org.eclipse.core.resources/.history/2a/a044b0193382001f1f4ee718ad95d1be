package RestAssuredApiTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class CartApiTesting {

	
	// Get all the carts.

    @Test
    public void getAllCarts() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/carts")
        .then()
            .statusCode(200)
            .log().all();
    }
    
    //get single cart
    
    @Test
    public void getSingleCart() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/carts/5") // Change "1" to the ID of the cart you want to fetch
        .then()
            .statusCode(200)
            .log().all();
    }
    
    
    @Test
    public void getCartsByUser() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/carts/user/5")  // Change "5" to the ID of the user whose carts you want to fetch
        .then()
            .statusCode(200)  // Assert that the status code is 200
            .log().all();     // Log the response
    }
    
  
    @Test 
	public void addCart() {
		RestAssured.baseURI = "https://api.trello.com";

		given()
		    .contentType(ContentType.JSON)
			.queryParam("name", "Rajjjjjjdeleyte")
			.queryParam("key", "252d4999f592d5efbcb89ba6c8d9916a")  // Ensure the key is valid
			.queryParam("token", "ATTAb77110a3a2729174bb24ab8e2e9fa3dffbb046e5a52151d0441f0e44de8eccf6F83E2644")  // Ensure the token is valid
		.when()
			.post("/1/boards/")
		.then()
			.statusCode(200)  // Expecting status code 201 for resource creation
			.log().all();
	}
    
    
    
    
    
    
    
}