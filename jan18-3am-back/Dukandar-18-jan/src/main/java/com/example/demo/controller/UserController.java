package com.example.demo.controller;

import com.example.demo.Dao.*;
import com.example.demo.pojo.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AmazonClient;
import com.example.demo.service.IReviewService;
import com.example.demo.service.IUserService;
import com.example.demo.util.ReviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IShopAddressRepository shopAddressRepository;

    @Autowired
    private IReviewService reviewService;


    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private JwtTokenProvider jwtService;

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private JwtTokenProvider jwtProvider;

    @Autowired
    private IAccountRepository accountRepo;


    @Autowired
    private IShopInfoRepository shopdetailsrepo;

    @Autowired
    private IPostRepository postRepository;




    @GetMapping("/shops-in-radius")
    public ResponseEntity<?> getAllShopsWithinRadius(@RequestParam int radius, @RequestParam double latitude, @RequestParam double longitude){
        List<ShopAddress> shopsWithInDistance = shopAddressRepository.findShopsWithInDistance(latitude, longitude, radius);

        List<Object> shops = new ArrayList<>();
        for (ShopAddress shopAddress : shopsWithInDistance ) {
            Vendor vendor = shopAddress.getVendor();
            VendorInfo vendorInfo = vendor.getVendorInfo();
            ShopDetails shopDetails = vendor.getShopDetails();
            List<ShopPhotos> shopPhotos = vendor.getShopPhotos();
            List<Review> reviewList = vendor.getReviews();

            double sum=0;
            double average=0;
            if (reviewList.size() != 0 ){

                for (Review review : reviewList){
                    sum=sum+review.getRating();
                }
                average = Math.floor(sum/reviewList.size());
            }


            List<Object> shop = new ArrayList<>();

            double distance = distance(latitude, longitude, shopAddress.getLat(), shopAddress.getLng());

            shop.add(shopAddress);
            shop.add(vendorInfo);
            shop.add(shopDetails);

            if (shopPhotos == null){
                shopPhotos = new ArrayList<>();
                shopPhotos.add(new ShopPhotos());
            }
            shop.add(shopPhotos);

            Map<String, Double> map = new HashMap<>();
            map.put("distance", distance );
            map.put("average_rating", average);

            shop.add(map);


            shops.add(shop);
        }

        return new ResponseEntity<>(shops, HttpStatus.OK);
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        char unit = 'K';

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);

        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;

        if (unit == 'K') {

            dist = dist * 1.609344;

        }
        dist= Math.floor(dist);
        return (dist);

    }

    private double deg2rad(double deg) {

        return (deg * Math.PI / 180.0);

    }

    private double rad2deg(double rad) {

        return (rad * 180.0 / Math.PI);

    }



    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader String token) {
        System.out.println("inside user info token " + token + " " + getClass().getName());

        String jwt = token.substring(7);
        System.out.println("token " + jwt);
        int account_id = jwtProvider.getValidatedAccountId(jwt);

        Account account = accountRepo.getByAccountId(account_id);

        User user = account.getU();
        int userId = user.getUserId();

        System.out.println("newUser 1 Info :" + user.toString());

        System.out.println("User 2 Info :"+userRepo.findByUserId(userId));
        return new ResponseEntity<>(userRepo.findByUserId(userId), HttpStatus.OK);
    }

    // add user photo
    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadUserPhoto(@RequestPart(value = "file") MultipartFile file,
                                               @RequestPart(value = "token") String token) {

        String jwt = token.substring(7);
        int accId = jwtService.getValidatedAccountId(jwt);

        //Integer account_id = Integer.valueOf(accId);

        Account account = accountRepo.getByAccountId(accId);
        User user = null;
        if (account.getRole().equals(Role.USER)) {
            user = account.getU();
        }

        String fileUrl = this.amazonClient.uploadFile(file);
        // save aws file url in user table

        user.setPhoto(fileUrl);
        System.out.println("User info :"+user.toString());
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);// work bcoz transient
    }

    // save user info
    @PostMapping("/save-user")
    public ResponseEntity<?> saveUserInfo(@RequestBody User user,@RequestHeader String token){
        String jwt = token.substring(7);
        int accId = jwtService.getValidatedAccountId(jwt);
        User u = null;
        Account account = accountRepo.getByAccountId(accId);

        if (account.getRole().equals(Role.USER)) {
            u = account.getU();
        }

        u.setFirst_name(user.getFirst_name());
        u.setLast_name(user.getLast_name());
        u.setEmail(user.getEmail());
        u.setAge(user.getAge());
        u.setGender(user.getGender());
        u.setMobile(user.getMobile());
        u.setCity(user.getCity());
        u.setState(user.getState());
        u.setStreet_address1(user.getStreet_address1());
        u.setStreet_address2(user.getStreet_address2());
        u.setZipcode(user.getZipcode());
        u.setLatitude(user.getLatitude());
        u.setLongitude(user.getLongitude());


        //account.setU(user);

        System.out.println(user +" from "+getClass().getName());
        System.out.println(u +" from "+getClass().getName());

        return ResponseEntity.ok(userService.saveUser(u));

    }


    @GetMapping("/get-shop-details")
    public ResponseEntity<?> getShopDetails(@RequestParam int shopId, @RequestParam double latitude, @RequestParam double longitude){
        ShopDetails shopDetails = shopdetailsrepo.findById(shopId).get();
        Vendor vendor = shopDetails.getVendor();

        VendorInfo vendorInfo = vendor.getVendorInfo();
        List<ShopPhotos> shopPhotos = vendor.getShopPhotos();
        ShopAddress address = vendor.getAddress();

        List<Object> object = new ArrayList<>();

        object.add(shopDetails);
        object.add(vendorInfo);
        object.add(address);
        object.add(shopPhotos);


        List<Review> reviewList = vendor.getReviews();

        double sum=0;
        double average=0;
        if (reviewList.size() != 0 ){

            for (Review review : reviewList){
                sum=sum+review.getRating();
            }
            average = Math.floor(sum/reviewList.size());
        }


        double distance = distance(latitude, longitude, address.getLat(), address.getLng());

        Map<String, Double> map = new HashMap<>();
        map.put("distance", distance );
        map.put("average_rating", average);


        object.add(map);


//        List<Post> postList = vendor.getPosts();
//        List<ShopEnquiry> enquiryList = vendor.getShopEnquiries();
//
//        object.add(postList);
//        object.add(reviewList);

        return new ResponseEntity<>(object, HttpStatus.OK);

    }




    @GetMapping("/get-all-posts")
    public ResponseEntity<?> getAllPosts(@RequestParam int shopId){
        ShopDetails shopDetails = shopdetailsrepo.findById(shopId).get();
        Vendor vendor = shopDetails.getVendor();
        List<Post> postList = vendor.getPosts();

        return new ResponseEntity<>(postList, HttpStatus.OK);

    }


    @GetMapping("/get-all-reviews")
    public ResponseEntity<?> getAllReviews(@RequestParam int shopId){
        ShopDetails shopDetails = shopdetailsrepo.findById(shopId).get();
        Vendor vendor = shopDetails.getVendor();



        List<ReviewInfo> allShopReviews = reviewService.getAllShopReviews(vendor);
        return new ResponseEntity<>( allShopReviews, HttpStatus.OK);


    }



    @GetMapping("/getAllShopsPosts")
    public ResponseEntity<?> getAllShopsPosts(){
        List<Post> postList = postRepository.findAll();
        List<Object> objects = new ArrayList<>();
        for (Post post: postList) {
            Vendor vendor = post.getVendor();
            ShopDetails shopDetails = vendor.getShopDetails();
            List<ShopPhotos> shopPhotos = vendor.getShopPhotos();
            List<Object> shop = new ArrayList<>();
            shop.add(post);
            shop.add(shopDetails);
            shop.add(shopPhotos);


            objects.add(shop);

        }

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }



}
