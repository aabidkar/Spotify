package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	
	public static String generatePlayListName() {
		Faker faker=new Faker();
		return "PlayList" + faker.regexify("[A-Za-z0-0 , _-]{10}");
	}
	
	public static String generatePlayListDescription() {
		Faker faker=new Faker();
		return "Description" + faker.regexify("[A-Za-z0-0 , _-]{50}");
	}
	
	public static String generateUpdatePlayListName() {
		Faker faker=new Faker();
		return "Update" + faker.regexify("[A-Za-z0-0 , _-]{10}");
	}

}
