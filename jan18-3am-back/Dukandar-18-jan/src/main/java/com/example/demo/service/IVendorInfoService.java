package com.example.demo.service;

import com.example.demo.pojo.VendorInfo;

public interface IVendorInfoService {
    public VendorInfo saveVendorInfo(VendorInfo owner);
    public VendorInfo getVendorInfo(int vendorId);
}
