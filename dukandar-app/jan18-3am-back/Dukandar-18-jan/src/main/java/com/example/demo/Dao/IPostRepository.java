package com.example.demo.Dao;

import com.example.demo.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p, Vendor v where v.vendorId=:vendorId")
    public List<Post> getAllPostsOfVendor(int vendorId);
}
