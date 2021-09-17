package com.example.demo.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.pojo.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

  /**
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  
  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000; // 1h

  @Autowired
  private MyAuthDetails myAuthDetails;
  
  @Autowired
  private IAccountRepository accountRepo;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // generate token
  
  public String createToken(Authentication authentication) {
	 
	  Account account =(Account) authentication.getPrincipal(); 
	  
	  //get account id from account
	  // store this id in JWT token payload
	  String accountId = Integer.toString( account.getAccountId());
	  
	  
	  Date now = new Date();
      Date validity = new Date(now.getTime() + validityInMilliseconds);
      
      Map<String, Object> claims = new HashMap<String, Object>();
      claims.put("id", accountId);
      claims.put("role", account.getRole());
      claims.put("mobile", account.getMobile());
      
	  return Jwts.builder()
			  .setSubject(accountId)
			  .setClaims(claims)
			  .setIssuedAt(now)
			  .setExpiration(validity)
			  .signWith(SignatureAlgorithm.HS512, secretKey)
			  .compact();
			  
  }
  

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myAuthDetails.loadUserByUsername(getValidatedAccountMobile(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }
  
  // we parse the mobile no from jwt token

  public int getValidatedAccountId(String jwt) {
	  System.out.println("in getValidatedAccountId"+jwt);
	
		  System.out.println("in getValidatedAccountId"+jwt);

	  Claims claims= Jwts.parser()
    		.setSigningKey(secretKey)
    		.parseClaimsJws(jwt)
    		.getBody();

	  String Id =(String)claims.get("id");

	  System.out.println("getValidatedAccountId :"+ Id);
	  
	  int id = Integer.parseInt(Id);
	  
//	   getValidatedAccountMobile(jwt);
//	   getValidatedAccountRole(jwt);
	  return id;
  }
  
  public String getValidatedAccountMobile(String jwt) {
	   String token= jwt.substring(7);

	  Claims claims = Jwts.parser()
    		.setSigningKey(secretKey)
    		.parseClaimsJws(token).getBody();
    		
	  String mobile =(String)claims.get("mobile");
	  System.out.println("Mobile  :"+mobile );

	  return mobile;
  }
  
  public String getValidatedAccountRole(String jwt) {
      String token= jwt.substring(7);
	  Claims claims = Jwts.parser()
    		.setSigningKey(secretKey)
    		.parseClaimsJws(token).getBody();
    		
	  String role =(String)claims.get("role");
	  System.out.println("Role  :"+role );

	  return role;
  }
  

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    System.out.println("bearerToken "+bearerToken);
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
