package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.pojo.ShopEnquiry;

public interface IShopEnquiryRepository extends JpaRepository<ShopEnquiry,Integer> {

	@Query("select se from ShopEnquiry se,Vendor v where v.vendorId=:vendorId")
	public List<ShopEnquiry> getShopEnquiry(int vendorId); 
	
	
}
