package com.example.demo.controller;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.Dao.IDomainRepository;
import com.example.demo.pojo.*;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.security.JWTLoginSuccesResponse;
import com.example.demo.security.MyAuthDetails;
import com.example.demo.service.AuthService;
import com.example.demo.service.IAccountService;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IAccountRepository accountRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IDomainRepository domainRepository;

	@Autowired
	private IAccountRepository accountRepo;

	@Autowired
	private JwtTokenProvider jwtService;




	@PostMapping("/user-signup")
	public ResponseEntity<?> signupUser(@RequestBody Account acc){
		System.out.println(acc);

		String password = acc.getPassword();

		acc.setPassword(passwordEncoder.encode(password));
		User user = new User();
		user.setActive(true);
		acc.addUser(user);
		Account account1 = accountRepository.save(acc);
		return new ResponseEntity<>(account1, HttpStatus.OK);
	}

	//
	@PostMapping("/vendor-signup")
	public ResponseEntity<?> signupVendor(@RequestBody Account acc , @RequestParam String shopName, @RequestParam String domain){
		acc.setActive(true);

		String password = acc.getPassword();

		acc.setPassword(passwordEncoder.encode(password));

		Account account = accountRepository.save(acc);
		System.out.println(account);


		Vendor vendor = new Vendor();
		vendor.setActive(true);
		account.addVendor(vendor);
		Account account1 = accountRepository.save(account);

		ShopDetails shopDetails= new ShopDetails();
		shopDetails.setShopName(shopName);
		BusinessType type1 = BusinessType.valueOf(domain);
		Domain domain1;
		List<Domain> domains = domainRepository.findAll();
		for (Domain domain3 : domains){
			if (domain3.getType().equals(type1)){
				domain1=domain3;
				domain1.addVendor(account1.getV());
				domainRepository.save(domain1);
			}
		}

		account1.getV().addShopDetails(shopDetails);

		Account account2 = accountRepository.save(account1);


		return new ResponseEntity<>(account2, HttpStatus.OK);
	}


	/*@PostMapping("/login")
	  public ResponseEntity<?> login(@RequestBody Account account) {

		String token =authService.signin(account.getMobile(),account.getPassword());

	    return ResponseEntity.ok(new JWTLoginSuccesResponse(true, token, ));

	  }*/



	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Account account) {
		System.out.println("in login of mobile and pass "+account.getMobile()+"  "+account.getPassword()+" FROM "+getClass().getName());
		String token =authService.signin(account.getMobile(),account.getPassword());
		// add role of login user in response
		String role= jwtService.getValidatedAccountRole(token);
		System.out.println("in login user role is  "+role+" FROM "+getClass().getName());

		return ResponseEntity.ok(new JWTLoginSuccesResponse(true, token,role));

	}





	  @GetMapping("/get-account")
	public ResponseEntity<?> getAccount(@RequestHeader String token){
		  String jwt= token.substring(7);
		  int accId=jwtService.getValidatedAccountId(jwt);
		  Integer account_id = Integer.valueOf(accId);
		  Account account=accountRepo.getByAccountId(account_id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	  }


//	  @GetMapping("/whoami")
//	  public ResponseEntity<?> whoami(HttpServletRequest req){
//		  return ResponseEntity.ok(authService.whoami(req));
//		   
//	  }

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody Account acc) {
//
//		Account user = accountService.registerUser(acc);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
}
