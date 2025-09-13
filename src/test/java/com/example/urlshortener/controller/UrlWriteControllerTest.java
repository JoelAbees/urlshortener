package com.example.urlshortener.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.repository.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlWriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UrlRepository urlRepository;
    
    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        urlRepository.deleteAll();
    }

    @Test
    void givenValidUrl_whenCreateShortUrl_thenReturnDetailedResponse() throws Exception {
        UrlRequest urlRequest = new UrlRequest("https://www.formula1.com/");
        

        mockMvc.perform(post("/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(urlRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.longUrl").value("https://www.formula1.com/"))
            .andExpect(jsonPath("$.shortUrl").exists())
            .andExpect(jsonPath("$.createdAt").exists());
    }



    
}
