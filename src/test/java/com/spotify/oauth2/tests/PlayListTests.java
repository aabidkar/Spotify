package com.spotify.oauth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicatoinAPI.PlayListApi;
import com.spotify.oauth2.pojo.GetAPlayList;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.Response;

@Epic("Spotify Oauth 2.0")
@Feature("PlayList API")
public class PlayListTests {
	@Step
	public GetAPlayList playListBuilder(String Name, String Description, Boolean Public) {
		GetAPlayList getAPlayList = new GetAPlayList();
		getAPlayList.setName(Name);
		getAPlayList.setDescription(Description);
		getAPlayList.set_public(Public);
		return getAPlayList;
	}

	@Step
	public void assertPlayListEqual(GetAPlayList responsePlayList, GetAPlayList requestPlayList) {
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
	}

	@Step
	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertThat(actualStatusCode, equalTo(statusCode.code));
	}

	@Story("Craete a PlayList.")
	@Test(description = "Should be able to create a Playlist.")
	@Description("The User Should be able to Create a Playlist.")
	public void shouldBeAbleToCreateAPlayList() {

		GetAPlayList getAPlayList = playListBuilder(FakerUtils.generatePlayListName(),
				FakerUtils.generatePlayListDescription(), false);
		Response response = PlayListApi.post(getAPlayList);
		assertStatusCode(response.statusCode(), StatusCode.CODE_201);
	}

	@Story("Get PlayList Information.")
	@Test(description = "Should be able to get Playlist Information.")
	@Description("The User Should be able to get the Playlist Information.")
	public void shouldBeAbleToGetAPlayList() {

		Response response = PlayListApi.get(DataLoader.getInstance().getPlayListId());
		assertStatusCode(response.statusCode(), StatusCode.CODE_200);
	}

	@Story("Update a PlayList.")
	@Test(description = "Should be able to Update Playlist Information.")
	@Description("The User Should be able to Update Playlist Information.")
	public void shouldBeAbleToUpdateAPlayList() {
		GetAPlayList getAPlayList = playListBuilder(FakerUtils.generateUpdatePlayListName(),
				FakerUtils.generatePlayListDescription(), false);

		Response response = PlayListApi.put(DataLoader.getInstance().getUpdatePlayListId(), getAPlayList);
		assertStatusCode(response.statusCode(), StatusCode.CODE_200);
	}

	@Story("Craete a PlayList with Negative Scenario.")
	@Test(description = "Should Not be able to Create a Playlist Without Information.")
	@Description("The User Should Not be able to Create a Playlist Without Information.")
	public void shouldNotBeAbleToCreateAPlayListWithOutName() {

		GetAPlayList getAPlayList = playListBuilder("", FakerUtils.generatePlayListDescription(), false);
		Response response = PlayListApi.post(getAPlayList);
		assertStatusCode(response.statusCode(), StatusCode.CODE_400);
	}

	/*
	 * @Test public void shouldNotBeAbleToCreateAPlayListWithExpiredToken() {
	 * GetAPlayList requestPlayList = new
	 * GetAPlayList().setName("New Playlist without Token")
	 * .setDescription("Description please check more in details.").setPublic(false)
	 * ; Response response = PlayListApi.post(requestPlayList);
	 * assertThat(response.statusCode(), equalTo(201));
	 * 
	 * RestAssured.given().baseUri("https://api.spotify.com").basePath("/v1").header
	 * ("Authorization", "123") .body(requestPlayList).when().post(
	 * "/users/31b6oy2fqe25gffjkhj2qau4faai/playlists").then()
	 * .spec(SpecBuilder.getResponseSpecification()).assertThat().statusCode(400); }
	 */
}
