package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.pojo.Review;

public interface IReviewRepository extends JpaRepository<Review,Integer>  {
	
	@Query("select r from Review r , Vendor v where v.vendorId=:vendorId")
	public List<Review> getAllShopReviews( int vendorId);

}
