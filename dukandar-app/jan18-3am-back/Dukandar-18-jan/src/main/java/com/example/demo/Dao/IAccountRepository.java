package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.pojo.Account;

import javax.transaction.Transactional;
import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Integer> {

    public List<Account> findAll();
    public Account findByMobileAndPassword(String mobile, String password);

    public Account getByAccountId(Integer id);
    public Account findByMobile(String mobile);

    // Further query method declarations
}
