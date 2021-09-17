package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;


@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AboutUs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	private String about_us;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
//	@JsonIgnoreProperties("aboutUs")
	@JsonIgnore
//	@JsonBackReference
	private Vendor vendor;
}
