package com.example.demo.service;

import com.example.demo.pojo.Post;
import com.example.demo.pojo.ShopDetails;
import com.example.demo.pojo.Vendor;
import com.example.demo.pojo.VendorInfo;

import java.util.List;

public interface IVendorService {
    public Vendor saveVendor(Vendor vendor);
    public Vendor getVendor(int id);
    public List<Post> getAllPosts(int vendorId);
}
