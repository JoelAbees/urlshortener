package com.example.urlshortener.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlWriteService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlRedirectControllerTest {

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
    void givenValidShortURL_whenFetchLongURL_thenRedirect302ToLongURL() throws Exception{
        Url url = urlWriteService.createShortUrl("https://www.formula1.com/");
        String shortUrl = url.getShortCode();

        mockMvc.perform(get("/" + shortUrl))
            .andExpect(status().isFound())
            .andExpect(header().string("Location", "https://www.formula1.com/"));

    }

    @Test
    void givenInvalidShortCode_whenRedirect_then404() throws Exception {
        mockMvc.perform(get("/doesnotexist"))
                .andExpect(status().isNotFound());
    }
    
}
