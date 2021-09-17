package com.example.demo.service;

import com.example.demo.Dao.IVendorInfoRepository;
import com.example.demo.pojo.VendorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorInfoService implements IVendorInfoService{
    @Autowired
    private IVendorInfoRepository vendorInfoRepository;
    @Override
    public VendorInfo saveVendorInfo(VendorInfo vendorInfo) {
        VendorInfo ownerDetails = vendorInfoRepository.save(vendorInfo);
        return ownerDetails;
    }

    @Override
    public VendorInfo getVendorInfo(int vendorId) {
        VendorInfo vendorInfo = vendorInfoRepository.getVendorInfo(vendorId);
        return vendorInfo;
    }
}
