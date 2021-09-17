package com.example.demo.controller;

import com.example.demo.Dao.IDomainRepository;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Domain;
import com.example.demo.pojo.Vendor;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IDomainRepository domainRepository;

   /* @PostMapping("/admin-register")
    public Account adminAccountRegistration( @RequestBody Account acc){
        Vendor v = new Vendor();
        v.setActive(true);
        acc.addVendor(v);
        Account account = accountService.registerUser(acc);
        return acc;
    }*/


    @GetMapping("get-all-domains")
    public ResponseEntity<?> getAllDomains(){
        List<Domain> domains = domainRepository.findAll();
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }



    @GetMapping("/getall")
    public ResponseEntity<?> getUrl(){
        List<Account> all = accountService.getAll();
        for (Account acc : all) {
            Vendor v = acc.getV();
            v.setActive(true);
        }
        return new  ResponseEntity<>(all, HttpStatus.OK);
    }


}
