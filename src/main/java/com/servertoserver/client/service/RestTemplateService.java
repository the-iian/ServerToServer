package com.servertoserver.client.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello 호출 후 response를 받아올 것

    public String hello(){

        URI uri = UriComponentsBuilder // UriComponentsBuilder : Uri 호출
                .fromUriString("http://localhost:9090")// 서버 포트
                .path("/api/server/hello")
                .encode()
                .build()
                .toUri();

        // RestTemplate으로 통신하기
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        /* getForObject 형태 - 우리가 사용하는 제네릭타입으로 받음
           getForObject의 get은 HTTP메소드의 GET = Object형태로 가져올거라는 뜻

           String result = restTemplate.getForObject(uri, String.class); 결과를 받을 문자열  */

        // getForEntity 형태 - result에는 여러가지 값들이 담겨있다
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println(result.getStatusCode()); // status code 확인
        System.out.println(result.getBody()); // 원하는 body의 내용

        return result.getBody();

    }
}
