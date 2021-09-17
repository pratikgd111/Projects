package com.example.demo.util;

public class ShopEnquiryInfo {

	private String username;
	private String usermobile;
	private String question;
	private String image;
	
	public ShopEnquiryInfo() {
		// TODO Auto-generated constructor stub
	}


	public ShopEnquiryInfo(String username, String usermobile, String question, String image) {
		super();
		this.username = username;
		this.usermobile = usermobile;
		this.question = question;
		this.image = image;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "ShopEnquiryInfo [username=" + username + ", usermobile=" + usermobile + ", question=" + question + "]";
	}
	
	
}
