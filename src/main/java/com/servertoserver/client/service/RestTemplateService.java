package com.servertoserver.client.service;

import com.servertoserver.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService { // RestTemplate으로 통신하기


    // http://localhost/api/server/hello 호출 후 response를 받아올 것
    public UserResponse hello(){

        URI uri = UriComponentsBuilder // UriComponentsBuilder : Uri 호출
                .fromUriString("http://localhost:9090")// 서버 포트
                .path("/api/server/hello") // path에 여러내용을 넣어줄 수 있다
                .queryParam("name","abc")
                .queryParam("age",99)
                .encode()
                .build()
                .toUri();
                // queryParam 메소드 사용시 http://localhost:9090/api/server/hello?name=steve&age=10 주소 변경됨

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        /* 1. getForObject 형태 - 우리가 사용하는 제네릭타입으로 받음
              getForObject의 get은 HTTP메소드의 GET = Object형태로 가져올거라는 뜻

              String result = restTemplate.getForObject(uri, String.class); 결과를 받을 문자열  */


        /* 2. getForEntity 형태 - result에는 여러가지 값들이 담겨있다
              ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);  */

        // json형태로 받기
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode()); // status code 확인
        System.out.println(result.getBody()); // 원하는 body의 내용

        return result.getBody();

    }
}
