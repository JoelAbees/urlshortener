package com.example.urlshortener.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlReadService;


@RestController
@RequestMapping("/urls")
public class UrlReadController {

    @Autowired
    private UrlReadService urlReadService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getUrlResponse(@PathVariable String shortCode){
        Optional<Url> optionalUrl = urlReadService.getUrlByShortCode(shortCode);

        if(optionalUrl.isPresent()){
            Url url = optionalUrl.get();
            String shortUrl = "http://localhost:8080/" + url.getShortCode();

            UrlResponse urlResponse = new UrlResponse(url.getId(), shortUrl, url.getOriginalUrl(), url.getCreatedAt());

            return ResponseEntity.ok(urlResponse);
        }else{
            return ResponseEntity.notFound().build();
        }


        
    }
}
