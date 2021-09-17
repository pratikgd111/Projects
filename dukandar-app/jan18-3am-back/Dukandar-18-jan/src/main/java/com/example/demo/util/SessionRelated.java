package com.example.demo.util;

import com.example.demo.Dao.IAccountRepository;
import com.example.demo.pojo.*;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionRelated {


    private static JwtTokenProvider jwtService = new JwtTokenProvider();

    @Autowired
    private static IAccountRepository accountRepo;

    public static int getUserOrVendorId(String token){
        Vendor v=null;
        User u=null;

        String jwt= token.substring(7);

        int accId=jwtService.getValidatedAccountId(jwt);

        System.out.println("account id = "+ accId);


        Account account=accountRepo.getByAccountId(accId);

        if(account.getRole().equals(Role.VENDOR)) {
            v=account.getV();
            System.out.println("vendor id = "+ v.getVendorId());
            return v.getVendorId();
        }else if(account.getRole().equals(Role.USER)){
            u=account.getU();
            System.out.println("user id = "+ u.getUserId());
            return u.getUserId();
        }

        return -1;
    }

    public static Role getRoleOfAccount(String token){

        String jwt= token.substring(7);
        int accId=jwtService.getValidatedAccountId(jwt);
        System.out.println("account id = "+ accId);
        Account account=accountRepo.getByAccountId(accId);

        return account.getRole();
    }



}
