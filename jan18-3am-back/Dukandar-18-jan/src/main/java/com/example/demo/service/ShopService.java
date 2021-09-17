package com.example.demo.service;

import com.example.demo.Dao.IShopInfoRepository;
import com.example.demo.pojo.ShopDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService implements IShopService{

    @Autowired
    private IShopInfoRepository shopInfoRepository;

    public ShopDetails saveShopDetails(ShopDetails shopDetails) {
        ShopDetails shopDetails1 = shopInfoRepository.save(shopDetails);
        return shopDetails1;
    }


}
