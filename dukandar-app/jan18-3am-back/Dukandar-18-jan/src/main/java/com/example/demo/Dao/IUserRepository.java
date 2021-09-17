package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.User;

public interface IUserRepository extends JpaRepository<User , Integer> {
    public User findByUserId(int id);
    public User findByEmail(String email);
}
