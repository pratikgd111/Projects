package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.ShopEnquiry;
import com.example.demo.pojo.Vendor;
import com.example.demo.util.ShopEnquiryInfo;

public interface IShopEnquiryService {

	List<ShopEnquiryInfo> getEnquiry(Vendor vendor);
	
	ShopEnquiry addEnquiryToShop( ShopEnquiry shopenquiry);
}
