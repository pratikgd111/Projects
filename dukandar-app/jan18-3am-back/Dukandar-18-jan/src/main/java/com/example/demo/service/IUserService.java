package com.example.demo.service;

import com.example.demo.pojo.User;

public interface IUserService {
	public User saveUser(User user);
	User getUserById(int userId);
}
