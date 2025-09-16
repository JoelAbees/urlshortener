package com.example.read.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.read.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long>{
    Optional<Url> findByShortCode(String shortCode);
}
