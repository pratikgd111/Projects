package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.Dao.IShopInfoRepository;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.BusinessType;
import com.example.demo.pojo.Domain;
import com.example.demo.pojo.Fevourite;
import com.example.demo.pojo.Review;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.ShopAddress;
import com.example.demo.pojo.ShopDetails;
import com.example.demo.pojo.ShopEnquiry;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Vendor;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.IDomainService;
import com.example.demo.service.IFevRepository;
import com.example.demo.service.IReviewService;
import com.example.demo.service.IShopEnquiryService;
import com.example.demo.service.IShopService;
import com.example.demo.service.IUserService;
import com.example.demo.service.IVendorService;

@RestController
@RequestMapping("/shop")
@CrossOrigin
public class ShopController {
    @Autowired
    private IShopService shopService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private IDomainService domainService;

    @Autowired
    private JwtTokenProvider jwtService;

    @Autowired
    private IAccountRepository accountRepo;


    @Autowired
    private IFevRepository fevrepo;

    @Autowired
    private IUserService userservice;

    @Autowired
    private IShopEnquiryService shopenquiryservice;

    @Autowired
    private IShopInfoRepository shopdetailsrepo;

    @Autowired
    private IReviewService reviewservice;





    @PostMapping("/save-shop-details")
    public ResponseEntity<?> saveShopDetails(@RequestBody ShopDetails shopDetails, @RequestParam String businesscategory, @RequestHeader String token){
        System.out.println(shopDetails);

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);
        Integer account_id = Integer.valueOf(accId);
        Account account=accountRepo.getByAccountId(account_id);
        Vendor vendor=null;
        if(account.getRole().equals(Role.VENDOR)) {
            vendor=account.getV();
        }

        vendor.addShopDetails(shopDetails);
        Domain domain1;
        List<Domain> domains = domainService.getAllDomains();
        for (Domain domain : domains){
            if (domain.getType().equals(BusinessType.valueOf(businesscategory))){
                domain1=domain;
                domain1.addVendor(vendor);
                System.out.println("in for loop");
                break;
            }
        }
        Vendor vendor1 = vendorService.saveVendor(vendor);
        return new ResponseEntity<>(vendor1, HttpStatus.OK);
    }

    @PostMapping("/save-shop-address")
    public  ResponseEntity<?> saveShopAddress(@RequestBody ShopAddress shopAddress, @RequestHeader String token){
        System.out.println(shopAddress);

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);
        Integer account_id = Integer.valueOf(accId);
        Account account=accountRepo.getByAccountId(account_id);
        Vendor vendor=null;
        if(account.getRole().equals(Role.VENDOR)) {
            vendor=account.getV();
        }

        vendor.addShopAddress(shopAddress);
        Vendor vendor1 = vendorService.saveVendor(vendor);
        return new ResponseEntity<>(vendor1.getAddress(), HttpStatus.OK);
    }







    @PostMapping("/add-enquiry-toshop")
    public ResponseEntity<?> addEnquiryToShop(@RequestBody ShopEnquiry shopenquiry,@RequestHeader String token,@RequestParam int shopId){

        shopenquiry.setCreatedOn(LocalDateTime.now());

        String jwt = token.substring(7);
        int accId = jwtService.getValidatedAccountId(jwt);
        User u = null;
        Account account = accountRepo.getByAccountId(accId);

        if (account.getRole().equals(Role.USER)) {
            u = account.getU();
        }



        ShopDetails shopDetails = shopdetailsrepo.findById(shopId).get();
        Vendor vendor = shopDetails.getVendor();



        shopenquiry.setUser(u);
        shopenquiry.setVendor(vendor);

        u.getShopEnquiries().add(shopenquiry);
        vendor.getShopEnquiries().add(shopenquiry);

        ShopEnquiry shopEnquiry = shopenquiryservice.addEnquiryToShop(shopenquiry);

        if(shopEnquiry != null)
            return new ResponseEntity<>(shopEnquiry, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-review-toshop")
    public ResponseEntity<?> addReviewToShop(@RequestBody Review shopreview,@RequestHeader String token,@RequestParam int shopId){


        shopreview.setCreatedOn(LocalDateTime.now());

        String jwt = token.substring(7);
        int accId = jwtService.getValidatedAccountId(jwt);
        User u = null;
        Account account = accountRepo.getByAccountId(accId);

        if (account.getRole().equals(Role.USER)) {
            u = account.getU();
        }



        ShopDetails shopDetails = shopdetailsrepo.findById(shopId).get();
        Vendor vendor = shopDetails.getVendor();



        shopreview.setUser(u);
        shopreview.setVendor(vendor);

        u.getReviews().add(shopreview);
        vendor.getReviews().add(shopreview);

        Review addReviewToShop = reviewservice.addReviewToShop(shopreview);

        if(addReviewToShop != null)
            return new ResponseEntity<>(addReviewToShop, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/like-increment")
    public ResponseEntity<?> likeIncrement(@RequestParam int shopId){

        ShopDetails shopdetails = shopdetailsrepo.findById(shopId).get();

        shopdetails.setLikes(shopdetails.getLikes()+1);

        return new ResponseEntity<>(shopdetailsrepo.save(shopdetails), HttpStatus.OK);
    }

    @PostMapping("/add-tofev")
    public ResponseEntity<?> addToFevourite(@RequestHeader String token , @RequestParam int shopDetailsId){

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        User u=null;
        if(account.getRole().equals(Role.USER)) {
            u=account.getU();

            ShopDetails shopDetails = shopdetailsrepo.findById(shopDetailsId).get();

            Fevourite fev = new Fevourite();

            fev.setUser(u);
            fev.setShopdetails(shopDetails);

            return new ResponseEntity<>(fevrepo.save(fev), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }








}
