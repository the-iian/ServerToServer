package com.servertoserver.client.controller;

import com.servertoserver.client.dto.UserResponse;
import com.servertoserver.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    // @Autowired 구버전 (요즘은 생성자 주입방식으로 바뀌었음 ↓)
    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello") // http://localhost:8080/api/client/hello
    public UserResponse getHello(){
        return restTemplateService.hello();
    }
}
