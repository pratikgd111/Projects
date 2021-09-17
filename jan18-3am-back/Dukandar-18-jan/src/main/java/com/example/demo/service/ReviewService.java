package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.pojo.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.IReviewRepository;
import com.example.demo.pojo.Review;
import com.example.demo.pojo.User;
import com.example.demo.util.ReviewInfo;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	private IReviewRepository reviewDao;
	
	@Override
	public List<ReviewInfo> getAllShopReviews(Vendor vendor) {
		
		
		List<ReviewInfo> shopreviewsinfo = new ArrayList<ReviewInfo>();
		
		List<Review> shopreviews = vendor.getReviews();

		for(Review review : shopreviews) {
			
			ReviewInfo reviewinfo = new ReviewInfo();

			User user = review.getUser();

			
			reviewinfo.setUsername(user.getFirst_name() +" " + user.getLast_name() );
			reviewinfo.setImage(user.getPhoto());
			reviewinfo.setDescription(review.getComment());
			reviewinfo.setRating(review.getRating());
			reviewinfo.setTime(review.getCreatedOn());
			
			
			shopreviewsinfo.add(reviewinfo);
		}
		return shopreviewsinfo;
	}

	@Override
	public Review addReviewToShop(Review review) {
		
		return reviewDao.save(review);
	}
	
	
	
	

}
