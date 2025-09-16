package com.example.urlshortener.write.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.write.dto.UrlRequest;
import com.example.urlshortener.write.dto.UrlResponse;
import com.example.urlshortener.write.model.Url;
import com.example.urlshortener.write.service.UrlWriteService;


@RestController
@RequestMapping("/urls")
public class UrlWriteController {

    @Autowired
    private UrlWriteService urlWriteService;

    @PostMapping
    public UrlResponse createShortUrl(@RequestBody UrlRequest urlRequest){
        Url url = urlWriteService.createShortUrl(urlRequest.getOriginalUrl());

        String shortUrl = "http://localhost:8080/" + url.getShortCode();

        return new UrlResponse(url.getId(), shortUrl, url.getOriginalUrl(), url.getCreatedAt());
    }
}
