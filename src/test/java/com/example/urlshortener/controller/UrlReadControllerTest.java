package com.example.urlshortener.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlWriteService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlWriteService urlWriteService;


    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        urlRepository.deleteAll();
    }

    @Test
    void givenGetShortUrl_whenFetchingUrl_thenReturnDetailedResponse() throws Exception{
        Url url = urlWriteService.createShortUrl("https://www.formula1.com/");
        String shortUrl = url.getShortCode();

        mockMvc.perform(get("/urls/" + shortUrl).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(url.getId()))
            .andExpect(jsonPath("$.longUrl").value("https://www.formula1.com/"))
            .andExpect(jsonPath("$.shortUrl").value("http://localhost:8080/" + shortUrl))
            .andExpect(jsonPath("$.createdAt").exists());


    }

    @Test
    void givenInvalidShortCode_whenGetUrlDetails_then404() throws Exception {
        mockMvc.perform(get("/urls/doesnotexist"))
                .andExpect(status().isNotFound());
    }

}
