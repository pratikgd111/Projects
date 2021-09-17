package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

/*

firstName:firstName,
lastName:lastName,
mobile:mobileNo,
email:email,
gender:gender,
age:age

*/


@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VendorInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorInfoId;

    @JsonProperty("firstName")
    @Column(length = 20)
	private String firstName;

	@JsonProperty("lastName")
	@Column(length = 20)
	private String lastName;

	@JsonProperty("mobile")
	@Column(length = 20)
	private  String mobile;

	@JsonProperty("email")
	@Column(unique = true)
	private String email;

	@JsonProperty("gender")
	@Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Gender gender;

	@JsonProperty("age")
	private int age;

	@Column(length = 150)
	private String photo;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
//	@JsonIgnoreProperties("vendorInfo")
	@JsonIgnore
//	@JsonBackReference
	private Vendor vendor;

	public Integer getVendorInfoId() {
		return vendorInfoId;
	}

	public void setVendorInfoId(Integer vendorInfoId) {
		this.vendorInfoId = vendorInfoId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "VendorInfo{" +
				"vendorInfoId=" + vendorInfoId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", gender=" + gender +
				", age=" + age +
				", photo='" + photo + '\'' +
				'}';
	}
}
