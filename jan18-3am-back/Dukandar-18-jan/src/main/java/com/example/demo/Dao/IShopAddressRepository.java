package com.example.demo.Dao;

import com.example.demo.pojo.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import java.util.List;

public interface IShopAddressRepository extends JpaRepository<ShopAddress, Integer> {
    @Query("SELECT s FROM ShopAddress s, Vendor v WHERE v.vendorId=:vendorId")
    public ShopAddress getShopAddress(int vendorId);

    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.lat)) *" +
            " cos(radians(s.lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.lat))))";


    @Query("SELECT s FROM ShopAddress s WHERE " + HAVERSINE_FORMULA + " < :distance ORDER BY "+ HAVERSINE_FORMULA + " ASC")
    List<ShopAddress> findShopsWithInDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distanceWithInKM);

}
