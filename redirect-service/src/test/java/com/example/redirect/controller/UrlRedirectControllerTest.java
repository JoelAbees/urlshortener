package com.example.redirect.controller;

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

import com.example.redirect.model.Url;
import com.example.redirect.repository.UrlRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlRedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    private Url testUrl;

    @Autowired
    private UrlRepository urlRepository;
    
    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        urlRepository.deleteAll();
        testUrl = new Url("abc123", "https://www.formula1.com/");
        urlRepository.save(testUrl);
    }

    @Test
    void givenValidShortURL_whenFetchLongURL_thenRedirect302ToLongURL() throws Exception{
        String shortUrl = testUrl.getShortCode();

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
