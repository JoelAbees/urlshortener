package com.example.redirect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redirect.model.Url;
import com.example.redirect.repository.UrlRepository;

@Service
public class UrlRedirectService {

    @Autowired
    private UrlRepository urlRepository;

    public String getlongUrl(String shortUrl){
        Optional<Url> optionalUrl = urlRepository.findByShortCode(shortUrl);
        if(optionalUrl.isPresent()) return optionalUrl.get().getOriginalUrl();
        else return "";
        
    }
}
