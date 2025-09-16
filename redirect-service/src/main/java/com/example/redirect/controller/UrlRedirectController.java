package com.example.redirect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redirect.service.UrlRedirectService;


@RestController
@RequestMapping()
public class UrlRedirectController {
    
    @Autowired
    private UrlRedirectService urlRedirectService;


    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode){
        String originalUrl = urlRedirectService.getlongUrl(shortCode);

        if(originalUrl.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
        
    }


}
