package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.IUserRepository;
import com.example.demo.pojo.User;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userrepo;
	
	@Override
	public User getUserById(int userId) {

		 Optional<User> user = userrepo.findById(userId);
		 
		 if(user.isPresent())
			 return user.get();
		 else 
			return null;  
	}



	@Autowired
	private IUserRepository userRepo;
	@Override
	public User saveUser(User user) {

		return userRepo.save(user);
	}

}
