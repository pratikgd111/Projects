package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
			
	@Column(length = 20)
	@JsonProperty("firstName")
	private String first_name;
	
	@Column(length = 20)
	@JsonProperty("lastName")
	private String last_name;
	
	@Column(unique = true)
	@JsonProperty("email")
	private String email;

	@Column(length = 20)
	@JsonProperty("mobile")
	private String mobile;



	@JsonProperty("age")
	private int age;

	@JsonAlias(value = "gender")
	private String gender;




	@Column(length = 150)
	@JsonProperty("photo")
	private String photo ;
	
	private boolean isActive;
	
	@Column(name = "address1")
	@JsonProperty("streetadd1")
	private String street_address1 ;
	
	@Column(name = "address2")
	@JsonProperty("streetadd2")
	private String street_address2 ;
	
	@Column(length = 20)
	@JsonProperty("city")
	private String city;
	
	@Column(length = 20)
	@JsonProperty("state")
	private String state ;
	
	@Column(length = 10)
	@JsonProperty("pincode")
	private String zipcode ;

	@JsonProperty("latitude")
	private double latitude ;

	@JsonProperty("longitude")
	private double longitude ;


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
//	@JsonIgnoreProperties("u")
	@JsonIgnore
//	@JsonBackReference
	private Account acc;

	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties("user")
	@JsonIgnore
	private List<Review> reviews;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties("user")
	@JsonIgnore
	private List<ShopEnquiry> shopEnquiries;









	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<ShopEnquiry> getShopEnquiries() {
		return shopEnquiries;
	}

	public void setShopEnquiries(List<ShopEnquiry> shopEnquiries) {
		this.shopEnquiries = shopEnquiries;
	}


	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getStreet_address1() {
		return street_address1;
	}

	public void setStreet_address1(String street_address1) {
		this.street_address1 = street_address1;
	}

	public String getStreet_address2() {
		return street_address2;
	}

	public void setStreet_address2(String street_address2) {
		this.street_address2 = street_address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Account getAcc() {
		return acc;
	}

	public void setAcc(Account acc) {
		this.acc = acc;
	}
	


	
	
}
