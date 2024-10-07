package RestAssuredApiTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class UserApiTesting {


	@Test
    public void getAllUsers() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        // Send a GET request to the /users endpoint to fetch all users
        given()
            .contentType(ContentType.JSON)   // Set the request content type to JSON
        .when()
            .get("/users")                   // Fetch all users
        .then()
            .statusCode(200)                 // Validate that the status code is 200 (OK)
            .log().all();                  
    }	
	
	

    @Test
    public void loginUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        // Login user using POST request
        given()
            .contentType(ContentType.JSON)   // Set the content type to JSON
            .body("{\n" +                       // Correct the body to be a valid JSON string
                "    \"username\": \"emilys\",\n" +
                "    \"password\": \"emilyspass\",\n" +
                "    \"expiresInMins\": 30\n" +
                "}")                             
        .when()
            .post("/auth/login")             // Send a POST request to the /auth/login endpoint
        .then()
            .statusCode(200)                 // Validate the status code is 200 (OK)
            .log().all();                    // Log the entire response
    }
    
    
    
    @Test
    public void getAuthUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        // Step 1: Login user and capture the accessToken
        Response loginResponse = given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                  "    \"username\": \"emilys\",\n" +
                  "    \"password\": \"emilyspass\",\n" +
                  "    \"expiresInMins\": 30\n" +
                  "}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)   // Ensure successful login
            .log().all()       // Log the response for debugging
            .extract().response();

        // Extract the accessToken from the login response
        String accessToken = loginResponse.path("accessToken");

        // Step 2: Get the authenticated user using the accessToken
        given()
            .header("Authorization", "Bearer " + accessToken)  // Add Bearer token to the request header
            .contentType(ContentType.JSON)
        .when()
            .get("/auth/user")   // Send GET request to the /auth/user endpoint
        .then()
            .statusCode(200)     // Ensure the request was successful
            .log().all()   ;      // Log the response
       
    }

    @Test
    public void getSingleUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        int userId = 1;  // Replace with the user ID you want to fetch

        // Send a GET request to fetch a single user by their ID
        given()
            .contentType(ContentType.JSON)   // Set the content type to JSON
        .when()
            .get("/users/" + userId)         // Fetch user with the specified ID
        .then()
            .statusCode(200)                 // Validate that the status code is 200 (OK)
            .log().all()  ;                   // Log the entire response
            
    
    }
    
    @Test
    public void searchUsers() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        String searchQuery = "Emily";  // Replace with the desired search term

        // Send a GET request to search users by the query term
        given()
            .contentType(ContentType.JSON)   // Set the content type to JSON
        .when()
            .get("/users/search?q=" + searchQuery)  // Send a GET request to search users
        .then()
            .statusCode(200)                 // Validate that the status code is 200 (OK)
            .log().all();                // Log the entire response
         
        
        
    }
    
    @Test
    public void filterUsersByKeyValue() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        // Define the key and value for filtering
        String key = "hair.color"; 
        String value = "Brown";

        // Send a GET request to filter users based on the key and value
        given()
            .contentType(ContentType.JSON)
            .queryParam("key", key)    // Add the nested key to the query parameter
            .queryParam("value", value) // Add the value to the query parameter
        .when()
            .get("/users/filter")       // Send GET request to /users/filter
        .then()
            .statusCode(200)            // Validate that the status code is 200 (OK)
            .log().all() ;               
            
    }
    @Test
    public void limitAndSkipUsers() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        // Define limit and skip values
        int limit = 5; // Limit the number of results
        int skip = 10; // Skip the first 10 results

        // Send a GET request to retrieve users with pagination
        given()
            .contentType(ContentType.JSON)
            .queryParam("limit", limit)    // Add limit to the query parameter
            .queryParam("skip", skip)      // Add skip to the query parameter
        .when()
            .get("/users")                  // Send GET request to /users endpoint
        .then()
            .statusCode(200)                // Validate that the status code is 200 (OK)
            .log().all();                 
    }
    
    @Test
    public void updateUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        int userId = 2; // ID of the user to be updated

        // Prepare the JSON body for the update
        String requestBody = "{\n" +
                             "    \"lastName\": \"Owais\"\n" +
                             "}";

        // Send a PUT request to update the user's last name
        given()
            .contentType(ContentType.JSON)   // Set the content type to JSON
            .body(requestBody)                // Include the request body
        .when()
            .put("/users/" + userId)          // Send a PUT request to the specified user ID
        .then()
            .statusCode(200)                  // Validate that the status code is 200 (OK)
            .log().all();                  
    }
    @Test
    public void deleteUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://dummyjson.com";

        int userId = 1; // ID of the user to be deleted

        // Send a DELETE request to delete the specified user
        given()
        .when()
            .delete("/users/" + userId)      // Send a DELETE request to the specified user ID
        .then()
            .statusCode(200)                  // Validate that the status code is 200 (OK)
            .log().all();                     // Log the full response
    }
}
