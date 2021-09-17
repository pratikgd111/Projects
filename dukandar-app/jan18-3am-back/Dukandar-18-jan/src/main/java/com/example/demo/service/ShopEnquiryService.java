package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.IShopEnquiryRepository;
import com.example.demo.pojo.ShopEnquiry;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Vendor;
import com.example.demo.util.ShopEnquiryInfo;

@Service
public class ShopEnquiryService implements IShopEnquiryService {

	@Autowired
	private IShopEnquiryRepository shopenquiryrepo;
	
	
	
	List<ShopEnquiryInfo> shopenquriesinfo = new ArrayList<ShopEnquiryInfo>();
	
	@Override
	public List<ShopEnquiryInfo> getEnquiry(Vendor vendor) {
//
//		List<ShopEnquiry> enquries = shopenquiryrepo.getShopEnquiry(Vendor vendor);

		List<ShopEnquiry> enquiries = vendor.getShopEnquiries();

		for (ShopEnquiry shopEnquiry : enquiries) {
			
			ShopEnquiryInfo enquiryInfo = new ShopEnquiryInfo();
			
			User user = shopEnquiry.getUser();
			
			enquiryInfo.setUsername(user.getFirst_name()+ " " + user.getLast_name());
			enquiryInfo.setUsermobile(user.getMobile());
			enquiryInfo.setQuestion(shopEnquiry.getQuestion());
			enquiryInfo.setImage(user.getPhoto());
			
			shopenquriesinfo.add(enquiryInfo);
		}
		
		return shopenquriesinfo;
	}

	
	
	@Override
	public ShopEnquiry addEnquiryToShop(ShopEnquiry shopenquiry) {
		
		return shopenquiryrepo.save(shopenquiry);
		 
	}
	
	
	
	
	
	

}
