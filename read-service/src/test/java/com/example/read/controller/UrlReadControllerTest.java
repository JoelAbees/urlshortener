package com.example.read.controller;

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

import com.example.read.model.Url;
import com.example.read.repository.UrlRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlReadControllerTest {

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
    void givenGetShortUrl_whenFetchingUrl_thenReturnDetailedResponse() throws Exception{
        String shortUrl = testUrl.getShortCode();

        mockMvc.perform(get("/urls/" + shortUrl).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(testUrl.getId()))
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
