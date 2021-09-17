package com.example.demo.controller;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.Dao.IPostRepository;
import com.example.demo.pojo.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.*;
import com.example.demo.util.ReviewInfo;
import com.example.demo.util.SessionRelated;
import com.example.demo.util.ShopEnquiryInfo;
import com.example.demo.util.StringObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendor")
@CrossOrigin
public class VendorController {

    @Autowired
    private IAccountRepository accountRepo;

    @Autowired
    private JwtTokenProvider jwtService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private IVendorInfoService vendorInfoService;

    @Autowired
    private IShopAddressService shopAddressService;

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IShopEnquiryService shopEnquiryService;

    @GetMapping("/personal-info")
    public ResponseEntity<?> getPersonalInfo(@RequestHeader String token){


        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }
        VendorInfo vendorInfo = v.getVendorInfo();


        return new ResponseEntity<>(vendorInfo, HttpStatus.OK);
    }

    @GetMapping("/get-shop-address")
    public ResponseEntity<?> getShopAddress(@RequestHeader String token){
        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }

        return new ResponseEntity<>(v.getAddress(), HttpStatus.OK);
    }


    @GetMapping("/shop-info")
    public ResponseEntity<?> getShopInfo(@RequestHeader String token){
        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }
        ShopDetails shopDetails = v.getShopDetails();

        return new ResponseEntity<>(shopDetails,HttpStatus.OK);
    }
















    @GetMapping("get-domain-info")
    public ResponseEntity<?> getDomainInfo(@RequestHeader String token){
        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }

        Domain domain = v.getDomain();

        return new ResponseEntity<>(new StringObject(domain.getType().toString()), HttpStatus.OK);
    }








    @PostMapping("/personal-info")
    public ResponseEntity<?> saveVendorInfo(@RequestBody VendorInfo vendorInfo,@RequestHeader String token){

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);
        Integer account_id = Integer.valueOf(accId);
        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }

        Vendor vendor = vendorService.getVendor(v.getVendorId());
        vendorInfo.setVendorInfoId(vendor.getVendorInfo().getVendorInfoId());
        vendorInfo.setPhoto(vendor.getVendorInfo().getPhoto());
        vendor.addVendorInfo(vendorInfo);
        Vendor vendor1 = vendorService.saveVendor(vendor);
        return new ResponseEntity<>(vendor1.getVendorInfo(), HttpStatus.OK);
    }



    @PostMapping("/save-post")
    public ResponseEntity<?> saveVendorPost(@RequestBody Post post,@RequestHeader int postId){
        Post post1 = postRepository.findById(postId).get();
        post1.setOfferName(post.getOfferName());
        post1.setOfferDescription(post.getOfferDescription());
        post1.setValid_till(post.getValid_till());
        Post post2 = postRepository.save(post1);
        return new ResponseEntity<>(post2, HttpStatus.OK);
    }

    @GetMapping("get-all-posts")
    public ResponseEntity<?> getAllPosts(@RequestHeader String token){

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);
        Integer account_id = Integer.valueOf(accId);
        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }

        List<Post> allPosts =v.getPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadVendorPhoto(@RequestPart(value = "file") MultipartFile file, @RequestPart(value="token") String token) {

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }


      /*
        Vendor v =null;
        Role role = SessionRelated.getRoleOfAccount(token);
        if(role.equals(Role.VENDOR)){
            int id = SessionRelated.getUserOrVendorId(token);
            v = vendorService.getVendor(id);
        }*/

         
    	String fileUrl = this.amazonClient.uploadFile(file);
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfo.setPhoto(fileUrl);
        v.addVendorInfo(vendorInfo);
        Vendor vendor1 = vendorService.saveVendor(v);
        
        return new ResponseEntity<>(vendor1.getVendorInfo(), HttpStatus.OK);
    }

    @PostMapping("/upload-shop-photo")
    public ResponseEntity<?> uploadShopPhoto(@RequestPart(value = "file") MultipartFile file, @RequestPart(value="token") String token) {

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }
        String fileUrl = this.amazonClient.uploadFile(file);
        ShopPhotos shopPhotos = new ShopPhotos();
        shopPhotos.setPhoto(fileUrl);

        v.addShopPhoto(shopPhotos);

        Vendor vendor1 = vendorService.saveVendor(v);

        return new ResponseEntity<>(vendor1.getShopPhotos(), HttpStatus.OK);
    }



    @PostMapping("/upload-post-image")
    public ResponseEntity<?> uploadPostImage(@RequestPart(value = "file") MultipartFile file, @RequestPart String token) {

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }

        String fileUrl = this.amazonClient.uploadFile(file);
        Post post = new Post();
        post.setPhoto(fileUrl);
        v.addPost(post);
        Post post1 = postRepository.save(post);
        return new ResponseEntity<>(post1, HttpStatus.OK);
    }



    @DeleteMapping("/delete-file")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        String message = this.amazonClient.deleteFileFromS3Bucket(fileUrl);
        return message;
    }

    @GetMapping("/get-shop-photos")
    public ResponseEntity<?> getAllShopPhotos(@RequestHeader String token){
        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
        }
        return new ResponseEntity<>(v.getShopPhotos(), HttpStatus.OK);
    }

    @GetMapping("/shop-reviews")
    public ResponseEntity<?> getAllShopReviews(@RequestHeader String token){


        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();

            List<ReviewInfo> allShopReviews = reviewService.getAllShopReviews(v);
            return new ResponseEntity<>( allShopReviews, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









    @GetMapping("/shop-enquiries")
    public ResponseEntity<?> getShopEnquries(@RequestHeader String token){

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);

        Integer account_id = Integer.valueOf(accId);
        //String role = jwtService.getValidatedAccountRole(token);

        Account account=accountRepo.getByAccountId(account_id);
        Vendor v=null;
        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();

            List<ShopEnquiryInfo> allShopEnquries = shopEnquiryService.getEnquiry(v);
            return new ResponseEntity<>( allShopEnquries, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
