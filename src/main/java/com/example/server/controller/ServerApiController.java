package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //클라이언트가 되는 서버에서 요청이 여기로 들어온다
    @GetMapping("/hello")
    public User hello(User user){
        //Client에서 Response Entity 로 받고 있기 떄문에
        return user;
    }
    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                    //클라이언트가 나한테 뭘보냈는지 모르겠다 싶으면 아래 타입으로 받아 본다.
                    HttpEntity<String> entity,
                    @RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header")  String customHeader
    ){

        log.info("entity:{}",entity.getBody());
        log.info("userId:{},userName:{}",userId,userName);
        log.info("client request:{}",user);
        log.info("auth:{},custom:{}",authorization,customHeader);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResBody(user.getResBody());

        return response;
    }

}
