package com.example.demo.Dao;

import com.example.demo.pojo.ShopDetails;
import com.example.demo.pojo.VendorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVendorInfoRepository extends JpaRepository<VendorInfo, Integer> {
    @Query("SELECT i FROM VendorInfo i, Vendor v WHERE v.vendorId=:vendorId")
    public VendorInfo getVendorInfo(int vendorId);
}
