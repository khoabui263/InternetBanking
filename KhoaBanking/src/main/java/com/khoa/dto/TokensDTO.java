package com.khoa.dto;

public class TokensDTO {
	private String accessToken;
	private String refreshToken;
	private String userName;
	
	public TokensDTO() {
		
	}

	public TokensDTO(String accessToken, String refreshToken, String userName) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userName = userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
