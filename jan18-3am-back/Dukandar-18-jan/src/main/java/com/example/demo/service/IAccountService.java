package com.example.demo.service;


import com.example.demo.pojo.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();
    Account getValidatedUser(String mobile, String password);
    Account registerUser(Account acc);
    Account getAccount(Account account);
}
