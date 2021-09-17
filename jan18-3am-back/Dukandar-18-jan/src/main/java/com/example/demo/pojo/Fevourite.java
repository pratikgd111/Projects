package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Fevourite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fev_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopdetails_id")
	@JsonIgnore
	private ShopDetails shopdetails;

	public Integer getFev_id() {
		return fev_id;
	}

	public void setFev_id(Integer fev_id) {
		this.fev_id = fev_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShopDetails getShopdetails() {
		return shopdetails;
	}

	public void setShopdetails(ShopDetails shopdetails) {
		this.shopdetails = shopdetails;
	}


	
	
}
