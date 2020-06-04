Feature: Validationg place APIs

Scenario: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload
	When user calls "AddPlaceAPI" with post http request
	Then the API call got success with Status code 200
	And "Status" in response body is "OK"
	And "scope" in response body is "APP"