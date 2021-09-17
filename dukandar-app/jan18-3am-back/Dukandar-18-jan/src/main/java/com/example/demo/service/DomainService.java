package com.example.demo.service;

import com.example.demo.Dao.IDomainRepository;
import com.example.demo.pojo.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService implements IDomainService{

    @Autowired
    private IDomainRepository domainRepository;

    @Override
    public List<Domain> getAllDomains() {
        List<Domain> domainList = domainRepository.findAll();
        return domainList;
    }

    @Override
    public Domain saveDomain(Domain domain) {
        Domain domain1 = domainRepository.save(domain);
        return domain1;
    }
}
