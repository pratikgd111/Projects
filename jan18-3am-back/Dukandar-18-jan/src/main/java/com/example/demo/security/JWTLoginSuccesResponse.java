package com.example.demo.security;

public class JWTLoginSuccesResponse {
	private boolean success;
	private String token;
	private String role;
		
	public JWTLoginSuccesResponse(boolean success, String token, String role) {
		super();
		this.success = success;
		this.token = token;
		this.role = role;

	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JWTLoginSuccesResponse [success=" + success + ", token=" + token + "]";
	}
	
	
}
