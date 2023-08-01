package com.example.demo.controller;
import com.example.demo.service.AuthService;
import com.example.demo.service.DemoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
@RestController
public class DemoController {

    @Autowired
    private AuthService authService;
    @Autowired
    private DemoService demoService;
    @GetMapping(value = "/getUser",
    produces = { "application/json"},consumes = { "application/json"})
    String getUser() throws Exception {
        return authService.getUser();
    }
    @RequestMapping(value = "/getDemoInfo", method = RequestMethod.GET)
    String getDemoInfo() throws Exception {
        return demoService.getInfo();
    }
}
