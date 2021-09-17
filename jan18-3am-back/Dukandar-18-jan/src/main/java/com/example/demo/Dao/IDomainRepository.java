package com.example.demo.Dao;

import com.example.demo.pojo.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDomainRepository extends JpaRepository<Domain, Integer> {

}
