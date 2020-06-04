package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Location;
import pojo.Maps;

public class StepDef {
	RequestSpecification response;
	RequestSpecification res;
	Response resp;

	@Given("Add Place Payload")
	public void add_Place_Payload() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Maps place = new Maps();
		Location loc = new Location();

		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setName("Frontline house");
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("c");

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		place.setTypes(myList);

		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);

		 res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		response = given().spec(res).body(place);

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		resp = response.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).extract()
				.response();
	}

	@Then("the API call got success with Status code {int}")
	public void the_API_call_got_success_with_Status_code(Integer int1) {
		assertEquals(resp.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String respStr = resp.asString();
		JsonPath jp = new JsonPath(respStr);
	    assertEquals(jp.get(keyValue).toString(), expectedValue);
	}

}
