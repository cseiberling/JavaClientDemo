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
public class AuthService {
    @Value("${thingsboard.url}")
    String thingsboardUrl;
    @Value("${thingsboard.username1}")
    String thingsboardUser;
    //this is not good
    @Value("${thingsboard.password1}")
    String thingsboardPass;
    @Autowired
    RestTemplate restTemplate;

    public AuthService(@Value("${thingsboard.url}") String thingsboardUrl, @Value("${thingsboard.username1}") String thingsboardUser, @Value("${thingsboard.password1}") String thingsboardPass, RestTemplate restTemplate) {
        this.thingsboardUrl = thingsboardUrl;
        this.thingsboardUser = thingsboardUser;
        this.thingsboardPass = thingsboardPass;
        this.restTemplate = restTemplate;
    }
    public String getUser() throws Exception {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + generateToken());
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(this.thingsboardUrl + "/api/auth/login4", HttpMethod.GET, entity, String.class);
//            Mono<?> result = webClient.get()
//                    .uri(this.thingsboardUrl + "/api/auth/user")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .header("X-Authorization", "Bearer " + generateToken())
//                    .exchange()
//                    .flatMap(response -> response.toEntity(String.class));
//            String payload = result.block().toString();
            return response.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    private String generateToken() throws Exception {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Accept", "application/json");
            HashMap<String, String> body = new HashMap<>();
            body.put("username", this.thingsboardUser);
            body.put("password", this.thingsboardPass);
            HttpEntity entity = new HttpEntity(body,headers);

            ResponseEntity<ThingsboardToken> response = restTemplate.exchange(this.thingsboardUrl+ "/api/auth/login2", HttpMethod.POST, entity, ThingsboardToken.class);
            return response.getBody().getToken();
//            HashMap<String, String> body = new HashMap<>();
//            body.put("username", this.thingsboardUser);
//            body.put("password", this.thingsboardPass);
//            Mono<ResponseEntity<ThingsboardToken>>  result = webClient.post()
//                    .uri(this.thingsboardUrl + "/api/auth/login")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromObject(body))
//                    .exchange()
//                    .flatMap(response -> response.toEntity(ThingsboardToken.class));
//            String bearerToken = result.block().getBody().getToken();
//            return bearerToken;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
