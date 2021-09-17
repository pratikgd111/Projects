package com.example.demo.Dao;

import com.example.demo.pojo.ShopDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopInfoRepository extends JpaRepository<ShopDetails, Integer> {

}
