package com.example.demo.service;

import com.example.demo.pojo.Domain;

import java.util.List;

public interface IDomainService {

    public List<Domain> getAllDomains();
    public Domain saveDomain(Domain domain);
}
