package com.example.urlshortener.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;

@Service
public class UrlReadService {

    @Autowired
    private UrlRepository urlRepository;


    public Optional<Url> getUrlByShortCode(String shortCode){
        return urlRepository.findByShortCode(shortCode);
    }
}
