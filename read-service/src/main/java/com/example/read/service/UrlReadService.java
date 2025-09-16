package com.example.read.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.read.model.Url;
import com.example.read.repository.UrlRepository;

@Service
public class UrlReadService {

    @Autowired
    private UrlRepository urlRepository;


    public Optional<Url> getUrlByShortCode(String shortCode){
        return urlRepository.findByShortCode(shortCode);
    }
}
