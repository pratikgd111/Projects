package com.example.demo.service;

import com.example.demo.Dao.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.pojo.Account;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getValidatedUser(String mobile, String password) {
        return accountRepository.findByMobileAndPassword(mobile, password);
    }

    @Override
    public Account registerUser(Account acc) {
        Account user = accountRepository.save(acc);
        return user;
    }

    @Override
    public Account getAccount(Account account) {
        Account account1 = accountRepository.findById(account.getAccountId()).get();
        return account1;
    }


}
