package com.example.demo.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Vendor;
import com.sun.el.stream.Optional;

@Service
public class MyAuthDetails implements UserDetailsService {

	@Autowired
	private IAccountRepository authRepository;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		final Account account = authRepository.findByMobile(mobile);

		System.out.println("auth details by mobile: " + account.toString()+" "+getClass().getName());

		if (account == null)
			new UsernameNotFoundException("User name not found");

		return account;
	}

	@Transactional
	public Account loadUserById(Integer id) throws UsernameNotFoundException {
		System.out.println("auth  id: " + id +" from "+getClass().getName());

		final Account account = authRepository.getByAccountId(id);
		System.out.println("auth details by id: " + account.toString()+getClass().getName());

		if(account == null) {
			new UsernameNotFoundException("User ID not found");
		}
		return account;
	}
}
