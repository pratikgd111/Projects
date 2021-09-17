package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Vendor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer vendorId;

   private  boolean isActive;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "account_id")

//   @JsonIgnoreProperties("v")
   @JsonIgnore
//   @JsonBackReference
   private Account acc;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "domain_id")
//   @JsonIgnoreProperties("vendors")
   @JsonIgnore
//   @JsonBackReference
   private Domain domain;
   
   @OneToOne( mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
//   @JsonIgnoreProperties("vendor")
   @JsonIgnore
   private ShopDetails shopDetails;
   
   @OneToOne(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
//   @JsonIgnoreProperties("vendor")
   @JsonIgnore
   private AboutUs aboutUs;

   @OneToOne(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//   @JsonIgnoreProperties("vendor")
   @JsonIgnore
   private ShopAddress address;

   @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//   @JsonIgnoreProperties("vendor")
   @JsonIgnore
   private List<Review> reviews;

   @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//   @JsonIgnoreProperties("vendor")
   @JsonIgnore
   private List<ShopPhotos> shopPhotos;

    @OneToOne(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("vendor")
    @JsonIgnore
    private VendorInfo vendorInfo;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("vendor")
    @JsonIgnore
    private List<ShopEnquiry> shopEnquiries;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts;

    public List<ShopEnquiry> getShopEnquiries() {
        return shopEnquiries;
    }

    public void setShopEnquiries(List<ShopEnquiry> shopEnquiries) {
        this.shopEnquiries = shopEnquiries;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public ShopDetails getShopDetails() {
        return shopDetails;
    }

    public void setShopDetails(ShopDetails shopDetails) {
        this.shopDetails = shopDetails;
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
    }

    public ShopAddress getAddress() {
        return address;
    }

    public void setAddress(ShopAddress address) {
        this.address = address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<ShopPhotos> getShopPhotos() {
        return shopPhotos;
    }

    public void setShopPhotos(List<ShopPhotos> shopPhotos) {
        this.shopPhotos = shopPhotos;
    }

    public VendorInfo getVendorInfo() {
        return vendorInfo;
    }

    public void setVendorInfo(VendorInfo vendorInfo) {
        this.vendorInfo = vendorInfo;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addVendorInfo(VendorInfo vendorInfo){
        this.setVendorInfo(vendorInfo);
        vendorInfo.setVendor(this);
    }

    public void addShopDetails(ShopDetails shopDetails){
        this.setShopDetails(shopDetails);
        shopDetails.setVendor(this);
    }

    public void addShopAddress(ShopAddress shopAddress){
        this.setAddress(shopAddress);
        shopAddress.setVendor(this);
    }

    public void addPost(Post post){
        this.posts.add(post);
        post.setVendor(this);
    }

    public void addShopPhoto(ShopPhotos shopPhoto){
        this.shopPhotos.add(shopPhoto);
        shopPhoto.setVendor(this);
    }




    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                ", isActive=" + isActive +
                '}';
    }
}

 /*
    vendor_id int PK auto_increment,
    domain_id int FK,
    account_id int,
    is_active bit

 */