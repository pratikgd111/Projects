package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("domainId")
    private  Integer domainId;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @JsonProperty("domain")
    private BusinessType type;

    @Column(length = 1024)
    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy = "domain",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("domain")
    @JsonIgnore
    private List<Vendor> vendors;

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public BusinessType getType() {
        return type;
    }

    public void setType(BusinessType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public void addVendor(Vendor v){
        vendors.add(v);
        v.setDomain(this);
    }

    public void removeVendor(Vendor v){
        vendors.remove(v);
        v.setDomain(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain = (Domain) o;
        return domainId.equals(domain.domainId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainId);
    }
}

/*

domain_id int PK auto_increment,
business_type varchar(10),
description tinytext

*/
