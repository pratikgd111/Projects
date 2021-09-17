package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
/*

streetadd1:streetadd1,
streetadd2:streetadd2,
city:city,
state:state,
pincode:pincode,
longitude:longitude,
latitude:latitude

*/


@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShopAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(length = 250)
    @JsonProperty("streetadd1")
    private  String street1;

    @Column(length = 250)
    @JsonProperty("streetadd2")
    private String street2;

    @Column(length = 30)
    @JsonProperty("city")
    private  String city;

    @Column(length = 30)
    @JsonProperty("state")
    private String state;

    @Column(length = 6)
    @JsonProperty("pincode")
    private  String zip;

    @JsonProperty("latitude")
    private double lat;

    @JsonProperty("longitude")
    private double lng;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
//    @JsonIgnoreProperties("address")
    @JsonIgnore
//    @JsonBackReference
    private Vendor vendor;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


    @Override
    public String toString() {
        return "ShopAddress{" +
                "addressId=" + addressId +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

/*

address_id int PK auto_increment,
vendor_id int FK,
street_address1 varchar(100),
street_address2 varchar(30),
city varchar(30),
state varchar(30),
zipcode char(6),
latitude decimal(10,8),
longitude decimal(11,8)

*/
