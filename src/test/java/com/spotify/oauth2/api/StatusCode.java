package com.spotify.oauth2.api;

public enum StatusCode {
	CODE_200(200, ""), CODE_201(201, ""), CODE_400(400, "Missing required field: name");

	public final int code;
	public final String message;

	StatusCode(int code, String message) {
		this.message = message;
		this.code = code;
	}

}
