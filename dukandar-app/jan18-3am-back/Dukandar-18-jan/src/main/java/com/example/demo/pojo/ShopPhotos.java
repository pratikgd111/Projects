package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShopPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer photos_id;

    @Column(length = 150)
    private String photo;


    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
//    @JsonIgnoreProperties("shopPhotos")
    @JsonIgnore
//    @JsonBackReference
    private Vendor vendor;


    public Integer getPhotos_id() {
        return photos_id;
    }

    public void setPhotos_id(Integer photos_id) {
        this.photos_id = photos_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
