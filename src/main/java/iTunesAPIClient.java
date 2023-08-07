package main.java;


import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class iTunesAPIClient {

    private String responseBody;

    public String iTunesGetRequest(String apiUrl) {
        RestAssured.baseURI = apiUrl;

        // Perform the GET request with query parameters - Only for Unit test purpose with hardcoded data
        Response response = RestAssured.given()
                .queryParam("term", "maroon")
                .queryParam("country", "US")
                .queryParam("media", "music")
                .queryParam("limit", "10")
                .queryParam("entity", "allArtist")
                .queryParam("lang", "ja_jp")
                .queryParam("version", "2")
                .queryParam("explicit", "Yes")
                .get();


        responseBody = response.toString();
        return responseBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public Response iTunesResponse(Map<String, Object> queryParams) {
        String apiUrl = "https://itunes.apple.com/search";
        RestAssured.baseURI = apiUrl;
        Response response = null;

        try {
        			response = RestAssured.given()
            		.contentType("application/json")
                    .queryParams(queryParams)
                    .when()
                    .get();
        }
        catch(Exception e) {
        	System.out.println("Error parsing the API response: " + e.getMessage());
        }
        return response;



        
    }
}