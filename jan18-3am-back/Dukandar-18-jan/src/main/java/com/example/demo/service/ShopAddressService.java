package com.example.demo.service;

import com.example.demo.Dao.IShopAddressRepository;
import com.example.demo.pojo.ShopAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAddressService implements IShopAddressService{
    @Autowired
    private IShopAddressRepository shopAddressRepository;

    @Override
    public ShopAddress getShopAddress(int vendorId) {
        return shopAddressRepository.getShopAddress(vendorId);
    }
}
