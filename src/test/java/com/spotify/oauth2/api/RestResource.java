package com.spotify.oauth2.api;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestResource {

	public static Response post(String path, Object requestPlayList) {

		return (Response) RestAssured.given().spec(SpecBuilder.getRequestSpecification()).body(requestPlayList).when()
				.post(path).then().spec(SpecBuilder.getResponseSpecification()).extract().response();

	}

	public static Response get(String path, Object requestPlayList) {
		return (Response) RestAssured.given().spec(SpecBuilder.getRequestSpecification()).when().get(path).then()
				.spec(SpecBuilder.getResponseSpecification()).extract().response();
	}

	public static Response put(String path, Object requestPlayList) {
		return (Response) RestAssured.given().spec(SpecBuilder.getRequestSpecification()).body(requestPlayList).when()
				.put(path).then().spec(SpecBuilder.getResponseSpecification()).extract().response();
	}

	public static Response postAccount(HashMap<String, String> formParams) {
		return RestAssured.given(SpecBuilder.getAccountRequestSpecification()).formParams(formParams).when()
				.post(Route.API + Route.TOKEN).then().spec(SpecBuilder.getResponseSpecification()).extract().response();
	}

}
