package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Fevourite;

public interface IFevRepository extends JpaRepository<Fevourite , Integer> {
	
//	public void addToFevourite(int userId , int vendorId);

}
