package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShopEnquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;

	@Column(columnDefinition = "text")
	@JsonProperty("query")
	private String question;

	@Column(columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
//	@JsonIgnoreProperties("shopEnquiries")
	@JsonIgnore
//	@JsonBackReference
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
//	@JsonIgnoreProperties("shopEnquiries")
	@JsonIgnore
//	@JsonBackReference
	private Vendor vendor;

	public Integer getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
}
