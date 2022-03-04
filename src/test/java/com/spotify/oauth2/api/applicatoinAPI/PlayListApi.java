package com.spotify.oauth2.api.applicatoinAPI;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.GetAPlayList;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PlayListApi {
	@Step
	public static Response post(GetAPlayList requestPlayList) {
		return RestResource.post(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + Route.PLAYLISTS,
				requestPlayList);
	}

	public static Response get(String playListId) {
		return (Response) RestAssured.given().spec(SpecBuilder.getRequestSpecification()).when()
				.get(Route.PLAYLISTS + "/" + playListId).then().spec(SpecBuilder.getResponseSpecification()).extract()
				.response();
	}

	public static Response put(String playListId, GetAPlayList requestPlayList) {
		return (Response) RestAssured.given().spec(SpecBuilder.getRequestSpecification()).body(requestPlayList).when()
				.put(Route.PLAYLISTS + "/" + playListId).then().spec(SpecBuilder.getResponseSpecification()).extract()
				.response();
	}

}
