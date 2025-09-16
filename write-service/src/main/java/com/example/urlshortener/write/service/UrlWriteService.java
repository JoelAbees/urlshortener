package com.example.urlshortener.write.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.write.model.Url;
import com.example.urlshortener.write.repository.UrlRepository;

@Service
public class UrlWriteService {
    
    @Autowired
    private UrlRepository urlRepository;

    public Url createShortUrl(String originalUrl){
        String shortCode = generateShortCode();

        Url url = new Url(shortCode, originalUrl);
        return urlRepository.save(url);
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0,8);
    }

    

}
