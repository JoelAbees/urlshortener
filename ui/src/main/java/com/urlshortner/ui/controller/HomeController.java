package com.urlshortner.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.urlshortner.ui.dto.UrlRequest;
import com.urlshortner.ui.dto.UrlResponse;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayURL;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @PostMapping("/shorten")
    public String shorten(@RequestParam("longUrl") String longUrl, Model model){

        UrlRequest urlRequest = new UrlRequest(longUrl);

        UrlResponse urlResponse = restTemplate.postForObject(gatewayURL, urlRequest, UrlResponse.class);
        model.addAttribute("longUrl", urlResponse.getLongUrl());
        model.addAttribute("shortUrl", urlResponse.getShortUrl());

        return "index";
    }
}
