package com.example.server.controller;

import com.example.server.dto.Product;
import com.example.server.dto.Req;
import com.example.server.dto.User;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //클라이언트가 되는 서버에서 요청이 여기로 들어온다
    @GetMapping("/hello")
    public User hello(User user){
        //Client에서 Response Entity 로 받고 있기 떄문에
        log.info("user:{}",user.toString());
        return user;
    }
    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                    //클라이언트가 나한테 뭘보냈는지 모르겠다 싶으면 아래 타입으로 받아 본다.
                     @RequestBody User user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header")  String customHeader
    ){

        log.info("userId:{},userName:{}",userId,userName);
        log.info("client request:{}",user);
        log.info("x-authorization:{},custom-header:{}",authorization,customHeader);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResBody(user);

        return response;
    }
    @PostMapping("/generic-exchange/{userId}/name/{userName}")
    public Req<Product> exchangePost(
            @RequestBody Req<Product> product,
            @PathVariable int userId,
            @PathVariable String userName,
            @RequestHeader("x-authorization") String authorization,
            @RequestHeader("custom-header")  String customHeader
    ){

        log.info("req body:{}",product.toString());


        return product;
    }

    @PostMapping("/user-test/{userId}/name/{userName}")
    public User post(
            //클라이언트가 나한테 뭘보냈는지 모르겠다 싶으면 아래 타입으로 받아 본다.
            @RequestBody User user,
            @PathVariable int userId,
            @PathVariable String userName
    ){

        log.info("userId:{},userName:{}",userId,userName);
        log.info("client request:{}",user);

        return user;
    }

}
