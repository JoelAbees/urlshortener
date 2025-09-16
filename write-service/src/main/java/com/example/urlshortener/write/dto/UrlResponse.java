package com.example.urlshortener.write.dto;

import java.time.LocalDateTime;

public class UrlResponse {
    private Long id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime createdAt;
    


    public UrlResponse() {
    }

    public UrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UrlResponse(Long id, String shortUrl, String longUrl, LocalDateTime createdAt) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    
}