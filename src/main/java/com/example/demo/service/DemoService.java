package com.example.demo.service;

import com.example.demo.model.ThingsboardToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Service
public class DemoService {
    @Value("${demo.url}")
    String demoUrl;
    @Autowired
    RestTemplate restTemplate;

    public DemoService(@Value("${demo.url}") String demoUrl, RestTemplate restTemplate) {
        this.demoUrl = demoUrl;
        this.restTemplate = restTemplate;
    }

    public String getInfo() throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(this.demoUrl + "/getDemoInformation4", HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
