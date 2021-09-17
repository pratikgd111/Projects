package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
/*

offername:offername,
description:description,
valid_till:offertill

*/

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(length = 30)
    @JsonProperty("offername")
    private String offerName;

    @Column(columnDefinition = "text")
    @JsonProperty("description")
    private String offerDescription;

    @JsonProperty("valid_till")
    private LocalDate valid_till;

    @Column(length = 150)
    @JsonProperty()
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    @JsonIgnore
    private Vendor vendor;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public LocalDate getValid_till() {
        return valid_till;
    }

    public void setValid_till(LocalDate valid_till) {
        this.valid_till = valid_till;
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
