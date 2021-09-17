package com.example.demo.service;

import com.example.demo.Dao.IPostRepository;
import com.example.demo.Dao.IVendorInfoRepository;
import com.example.demo.Dao.IVendorRepository;
import com.example.demo.pojo.Post;
import com.example.demo.pojo.Vendor;
import com.example.demo.pojo.VendorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendorService implements IVendorService{
    @Autowired
    private IVendorRepository vendorDao;

    @Autowired
    private IPostRepository postRepository;


    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorDao.save(vendor);
    }

    @Override
    public Vendor getVendor(int vendorId) {
        Optional<Vendor> vendor = vendorDao.findById(vendorId);
        if (vendor.isPresent())
            return vendor.get();
        else
            return null;
    }

    @Override
    public List<Post> getAllPosts(int vendorId) {
        List<Post> allPostsOfVendor = postRepository.getAllPostsOfVendor(vendorId);
        return allPostsOfVendor;
    }
}
