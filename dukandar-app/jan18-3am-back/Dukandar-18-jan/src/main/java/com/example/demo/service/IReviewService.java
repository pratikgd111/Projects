package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Review;
import com.example.demo.pojo.Vendor;
import com.example.demo.util.ReviewInfo;


public interface IReviewService {

	List<ReviewInfo> getAllShopReviews(Vendor vendor);
	
	Review addReviewToShop(Review review);


	
}
