package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalTime;

/*

shopName:shopName,
//businesscategory:businesscategory,
//category:category,
speciality:speciality,
opening_time:openingtime,
closingt_ime:closingtime,
resonetoVisit:resonetoVisit

*/

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShopDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;

	@Column(length = 30)
	@JsonProperty("shopName")
	private String shopName;

	@Column(columnDefinition = "TIME")
	@JsonProperty("opening_time")
	private LocalTime openingTime;

	@Column(columnDefinition = "TIME")
	@JsonProperty("closing_time")
	private LocalTime closingTime;

	@Column(length = 255)
	@JsonProperty("reasontoVisit")
	private String reasonsToVisit;

	@Column(length = 255)
	@JsonProperty("speciality")
	private String speciality;

	@Column(columnDefinition = "int default 0")
	private int likes;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
//	@JsonIgnoreProperties("shopDetails")
	@JsonIgnore
//	@JsonBackReference
	private Vendor vendor;


	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public String getReasonsToVisit() {
		return reasonsToVisit;
	}

	public void setReasonsToVisit(String reasonsToVisit) {
		this.reasonsToVisit = reasonsToVisit;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


}
