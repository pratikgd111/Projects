package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.pojo.Account;
import com.example.demo.security.JwtTokenProvider;
@Service
public class AuthService {

  @Autowired
  private IAccountRepository authRepository;
  
  // add dependecy for existsByMobileNo
  
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Value("${security.jwt.token.TOKEN-PREFIX:PREFIX}")
  private String prefix;//Bearer

 /* public String signin(String mobile, String password) {
    try {
    	System.out.println("mobile "+mobile+password);
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobile, password));
      
      // set authentication to security context
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      String token= prefix+" " + jwtTokenProvider.createToken(authentication);
      
      return token;
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }*/

//  public String signup(Account newAccount) {
//    if (!authRepository.existsByMobileNo(newAccount.getMobile())) {
//    	newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
//      authRepository.save(newAccount);
//      return jwtTokenProvider.createToken(newAccount.getMobile(), newAccount.getRoles());
//    } else {
//      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
//    }
//  }

//  public void delete(String username) {
//    authRepository.deleteByUsername(username);
//  }

//  public User search(String username) {
//    User user = authRepository.findByUsername(username);
//    if (user == null) {
//      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
//    }
//    return user;
//  }

//  // Current Account 
//  public Account whoami(HttpServletRequest req) {
//    return authRepository.finBy((jwtTokenProvider.resolveToken(req)));
//  }





  public String signin(String mobile, String password) {
    try {
      System.out.println("mobile "+mobile+password);
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobile, password));

      // set authentication to security context
      SecurityContextHolder.getContext().setAuthentication(authentication);
      System.out.println("in signin authentication :"+authentication);
      String token= prefix+" " + jwtTokenProvider.createToken(authentication);
      System.out.println("in signin token :"+token);

      return token;
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }






}
