package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReviewInfo {

	private String image;
	private String description;
	private String username;
	private LocalDateTime time;
	private int rating;
	
	
	
	public ReviewInfo(){
		
	}





	public ReviewInfo(String image, String description, String username, LocalDateTime time, int rating) {
		super();
		this.image = image;
		this.description = description;
		this.username = username;
		this.time = time;
		this.rating = rating;
	}





	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public LocalDateTime getTime() {
		return time;
	}



	public void setTime(LocalDateTime time) {
		this.time = time;
	}





	@Override
	public String toString() {
		return "ReviewInfo [image=" + image + ", description=" + description + ", username=" + username + ", time="
				+ time + ", rating=" + rating + "]";
	}





	public int getRating() {
		return rating;
	}





	public void setRating(int rating) {
		this.rating = rating;
	}


	
	
	
}
