package com.example.demo.Dao;

import com.example.demo.pojo.ShopAddress;
import com.example.demo.pojo.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVendorRepository extends JpaRepository<Vendor,Integer> {


}
