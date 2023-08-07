package test.java;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import main.java.iTunesAPIClient;


public class Tests{
	
	
	@Test
	public void validateSearchResultsDisplayed() {
		
		int resultCount = 0;
		
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("term", "maroon");
        queryParams.put("country", "US");
        queryParams.put("media", "music");
        queryParams.put("limit", 20);
        queryParams.put("entity", "allArtist");
        queryParams.put("lang", "ja_jp");
        queryParams.put("version", 2);
        queryParams.put("explicit", "Yes");
		
		iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
		
		
		if (response.getStatusCode() == 200) {

            resultCount = response.jsonPath().getInt("resultCount");
//            String artistId = response.jsonPath().getString("results.artistId");
            System.out.println("Result Count: " + resultCount);
//            System.out.println("Artist Id: "+ artistId);
            
        } else {
            System.out.println("API call failed with status code: " + response.getStatusCode());
        }
		
		if(resultCount >=1) {
			Reporter.log("You have "+resultCount+" search results");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("No results found for the search criteria");
			Assert.assertTrue(false, "No results found for the search criteria");
		}
		
	}
	
	@Test
	public void mandatoryParamTermValidation() {
		int resultCount = 0;
		
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("country", "US");
        queryParams.put("media", "music");
        queryParams.put("limit", 10);
        queryParams.put("entity", "allArtist");
        queryParams.put("lang", "ja_jp");
        queryParams.put("version", 2);
        queryParams.put("explicit", "Yes");
        
        
        iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
        
		if (response.getStatusCode() == 200) {

            resultCount = response.jsonPath().getInt("resultCount");
            System.out.println("Result Count: " + resultCount);
            
        } else {
            System.out.println("API call failed with status code: " + response.getStatusCode());
        }
		
		if(resultCount == 0) {
			Reporter.log("You have 0 search results. Term parameter is mandatory");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("You have "+resultCount+" search results without providing term paramater");
			Assert.assertTrue(false);
		}
	}
	@Test
	public void mandatoryParamCountryValidation() {
		int resultCount = 0;
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("term", "maroon");
        queryParams.put("media", "music");
        queryParams.put("limit", 15);
        queryParams.put("entity", "allArtist");
        queryParams.put("lang", "ja_jp");
        queryParams.put("version", 2);
        queryParams.put("explicit", "Yes");
        
        
        iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
        
		if (response.getStatusCode() == 200) {

            resultCount = response.jsonPath().getInt("resultCount");
            System.out.println("Result Count: " + resultCount);
            
        } else {
            System.out.println("API call failed with status code: " + response.getStatusCode());
        }
		
		if(resultCount ==0) {
			Reporter.log("You have 0 search results. Country parameter is mandatory");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("You have "+resultCount+" search results without providing Country paramater");
			Assert.assertTrue(false);
		}
	}
	@Test
	public void validateLimitParameter() {
		
		int resultCount = 0;
		
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("term", "maroon");
        queryParams.put("country", "US");
        queryParams.put("media", "music");
        queryParams.put("limit", 5);
        queryParams.put("entity", "allArtist");
        queryParams.put("lang", "ja_jp");
        queryParams.put("version", 2);
        queryParams.put("explicit", "Yes");
		
		iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
		
		
		if (response.getStatusCode() == 200) {

            resultCount = response.jsonPath().getInt("resultCount");
            System.out.println("Result Count: " + resultCount);
            
        } else {
            System.out.println("API call failed with status code: " + response.getStatusCode());
        }
		
		if(resultCount ==5) {
			Reporter.log("You have "+resultCount+" search results. Limit parameter working as expected");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("You have "+resultCount+" search results not equals to the provided limit value");
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void validateResponseResultHasExpectedParameters() {
		
		int resultCount = 0;
		String wrapperType = "";
		String artistType = "";
		String artistName = "";
		String artistLinkUrl = "";
		String artistId = "";
		String amgArtistId = "";
		String primaryGenreName = "";
		String primaryGenreId = "";
		
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("term", "maroon");
        queryParams.put("country", "US");
        queryParams.put("media", "music");
        queryParams.put("limit", 1);
        queryParams.put("entity", "allArtist");
        queryParams.put("lang", "ja_jp");
        queryParams.put("version", 2);
        queryParams.put("explicit", "Yes");
		
		iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
		
		
		
		if (response.getStatusCode() == 200) {

            resultCount = response.jsonPath().getInt("resultCount");
            wrapperType = response.jsonPath().getString("results.wrapperType[0]");
            artistType = response.jsonPath().getString("results.artistType[0]");
            artistName = response.jsonPath().getString("results.artistName[0]");
            artistLinkUrl = response.jsonPath().getString("results.artistLinkUrl[0]");
            artistId = response.jsonPath().getString("results.artistId[0]");
            amgArtistId = response.jsonPath().getString("results.amgArtistId[0]");
            primaryGenreName = response.jsonPath().getString("results.primaryGenreName[0]");
            primaryGenreId = response.jsonPath().getString("results.primaryGenreId[0]");
            
            System.out.println("Result Count: " + resultCount);
            
        } else {
            System.out.println("API call failed with status code: " + response.getStatusCode());
        }
		
		if(resultCount > 0 && wrapperType!=null && artistType != null && artistName != null
				&& artistLinkUrl != null && artistId != "" && amgArtistId != "" && 
				primaryGenreName != null && primaryGenreId != "") {
			Reporter.log("All the required fields are displayed in the response for given sample test data");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("All the required fields are not displayed or empty or null in the response");
			Assert.assertTrue(false);
		}
		
	}
	@Test
	public void validateInvalidDataThrowsBadRequest() {
		
		
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("term", "maroon");
        queryParams.put("country", "US");
        queryParams.put("entity", "Test");
		
		iTunesAPIClient itunesAPIClient = new iTunesAPIClient();
		Response response = itunesAPIClient.iTunesResponse(queryParams);
		
		if(response.getStatusCode() == 400) {
			Reporter.log("Bad request validation successful for invalid test data");
			Assert.assertTrue(true);
		}
		else {
			Reporter.log("Expected Bad request for the given test data");
			Assert.assertTrue(false);
		}
		
	}
	
}